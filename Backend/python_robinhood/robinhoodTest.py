import pandas as pd
import pandas_datareader as reader
import datetime as dt
import numpy as np
import ssl
import robin_stocks as robin
import pyotp
from dateutil.relativedelta import relativedelta
from firebase import firebase
import time
import threading

ssl._create_default_https_context = ssl._create_unverified_context

firebase = firebase.FirebaseApplication("https://pythontrading-99511-default-rtdb.firebaseio.com/", None)

def read_link():
    table = pd.read_html("https://finance.yahoo.com/quote/%5EDJA/components?p=%5EDJA")[0]
    tickers = table.Symbol.tolist()

    print(type(tickers))

    start = dt.datetime(2018, 1, 31)
    end = dt.datetime(2020, 1, 31)

    df = reader.get_data_yahoo(tickers, start, end)['Adj Close']
    mtl_ret = df.pct_change().resample('M').agg(lambda x: (x+1).prod() - 1)
    past_11 = (mtl_ret+1).rolling(11).apply(np.prod, raw=True)-1
    formation = dt.datetime(2019, 12, 31)

    end_measurement = formation - relativedelta(months=1)
    ret12 = past_11.loc[end_measurement]
    ret12 = ret12.reset_index()
    ret12['quintile'] = pd.qcut(ret12.iloc[:, 1], 6, labels=False)

    winners = ret12[ret12.quintile == 4]
    winnerret = mtl_ret.loc[formation + relativedelta(months=1), df.columns.isin(winners.Symbols)]

    return winnerret


def sell_stocks():
    for i in range(len(winners)):
        robin.order_sell_market(winners[i], 1)
        time.sleep(2)


def countdown():
    t = 2678400
    while t > 0:
        # mins, secs = divmod(t, 60)
        # timer = '{:02d}:{:02d}'.format(mins, secs)

        # data = {"countdown": timer}
        firebase.put("/", "countdown", str(dt.timedelta(seconds=t)))
        # print(timer, end="\r")
        time.sleep(1)
        t -= 1

    # sell
    # sell_stocks()


def login():
    login = robin.login("email", "password")
    first_name = robin.load_user_profile()["first_name"]

    account_num = robin.load_account_profile()["account_number"]
    firebase.put("/", "firstName", first_name)
    firebase.put("/", "accountNum", str(account_num))


def place_orders():
    for i in range(len(win_tickers)):
        robin.order_buy_market(win_tickers[i], 1)
        time.sleep(2)
    countdown()


def update_tickers():
    while(True):
        firebase.put("/", "updatedTickers", robin.stocks.get_quotes(list(win_tickers)))
        time.sleep(3)


t1 = threading.Thread(target=update_tickers)
t2 = threading.Thread(target=countdown)
login()
winners = read_link()
win_tickers = winners.keys()
quotes = robin.stocks.get_quotes(list(win_tickers))
firebase.put("/", "tickers", quotes)
place_orders()
t1.start()
t2.start()
t1.join()
t2.join()
