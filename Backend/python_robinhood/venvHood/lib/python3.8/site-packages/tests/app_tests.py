from uuid import uuid4
import datetime
import sys
import pyotp
import os
from dotenv import load_dotenv
load_dotenv()
import requests

import robin_stocks as r



totp  = pyotp.TOTP(os.environ['robin_mfa']).now()
print("Current OTP:", totp)
login = r.login(os.environ['robin_username'], os.environ['robin_password'], store_session=True, mfa_code=totp)
# print("login data is ", login)

print("===")
print("running test at {}".format(datetime.datetime.now()))
print("===")

order = r.order_sell_crypto_by_quantity("BTC", 0.00007)
print(order)
