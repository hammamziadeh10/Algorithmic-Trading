package com.zgapplications.robinhoodtrading;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    TextView nameField;
    TextView accountNumField;
    TextView countdownField;
    TextView ticker1, ticker2, ticker3, ticker4, ticker5;
    TextView boughtAt1, boughtAt2, boughtAt3, boughtAt4, boughtAt5;
    TextView currentPrice1, currentPrice2, currentPrice3, currentPrice4, currentPrice5;
    TextView totalField;
    View divider;
    String url = "https://pythontrading-99511-default-rtdb.firebaseio.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        firebaseDatabase = FirebaseDatabase.getInstance();

        nameField = findViewById(R.id.home_page_name_field);
        accountNumField = findViewById(R.id.home_page_account_num_field);
        countdownField = findViewById(R.id.countdown_field);
        totalField = findViewById(R.id.total_field);
        divider = findViewById(R.id.divider);
        declare();



        firebaseDatabase.getReferenceFromUrl(url).child("firstName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    String firstName = snapshot.getValue(String.class);
                    nameField.setText("Hello " + firstName);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        firebaseDatabase.getReferenceFromUrl(url).child("accountNum").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    String accountNum = snapshot.getValue(String.class);
                    accountNumField.setText("Account# : " + accountNum);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        firebaseDatabase.getReferenceFromUrl(url).child("countdown").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    String countdown = snapshot.getValue(String.class);
                    countdownField.setText("Time till sell:\n" + countdown);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        firebaseDatabase.getReferenceFromUrl(url).child("tickers").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    setTickers(snapshot);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

//    private void setUpdatedPrices(DataSnapshot snapshot) {
//        String currentPrice1String = snapshot.child("0").child("last_trade_price").getValue(String.class);
//        String currentPrice2String = snapshot.child("1").child("last_trade_price").getValue(String.class);
//        String currentPrice3String = snapshot.child("2").child("last_trade_price").getValue(String.class);
//        String currentPrice4String = snapshot.child("3").child("last_trade_price").getValue(String.class);
//        String currentPrice5String = snapshot.child("4").child("last_trade_price").getValue(String.class);
//
//        currentPrice1.setText(currentPrice1String.substring(0, currentPrice1String.length()-4));
//        currentPrice2.setText(currentPrice2String.substring(0, currentPrice2String.length()-4));
//        currentPrice3.setText(currentPrice3String.substring(0, currentPrice3String.length()-4));
//        currentPrice4.setText(currentPrice4String.substring(0, currentPrice4String.length()-4));
//        currentPrice5.setText(currentPrice5String.substring(0, currentPrice5String.length()-4));
//
//    }

    private void setTickers(DataSnapshot snapshot) {
        ticker1.setText(snapshot.child("0").child("symbol").getValue(String.class));
        ticker2.setText(snapshot.child("1").child("symbol").getValue(String.class));
        ticker3.setText(snapshot.child("2").child("symbol").getValue(String.class));
        ticker4.setText(snapshot.child("3").child("symbol").getValue(String.class));
        ticker5.setText(snapshot.child("4").child("symbol").getValue(String.class));
        divider.setVisibility(View.VISIBLE);

        String boughtAt1String = snapshot.child("0").child("last_trade_price").getValue(String.class);
        String boughtAt2String = snapshot.child("1").child("last_trade_price").getValue(String.class);
        String boughtAt3String = snapshot.child("2").child("last_trade_price").getValue(String.class);
        String boughtAt4String = snapshot.child("3").child("last_trade_price").getValue(String.class);
        String boughtAt5String = snapshot.child("4").child("last_trade_price").getValue(String.class);

        boughtAt1.setText(boughtAt1String.substring(0, boughtAt1String.length()-4));
        boughtAt2.setText(boughtAt2String.substring(0, boughtAt2String.length()-4));
        boughtAt3.setText(boughtAt3String.substring(0, boughtAt3String.length()-4));
        boughtAt4.setText(boughtAt4String.substring(0, boughtAt4String.length()-4));
        boughtAt5.setText(boughtAt5String.substring(0, boughtAt5String.length()-4));

        bridge(Float.valueOf(boughtAt1String) , Float.valueOf(boughtAt2String), Float.valueOf(boughtAt3String), Float.valueOf(boughtAt4String), Float.valueOf(boughtAt5String));

    }

    private void bridge(final Float first, final Float second, final Float third, final Float fourth, final Float fifth) {
        firebaseDatabase.getReferenceFromUrl(url).child("updatedTickers").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {

                    String currentPrice1String = snapshot.child("0").child("last_trade_price").getValue(String.class);
                    String currentPrice2String = snapshot.child("1").child("last_trade_price").getValue(String.class);
                    String currentPrice3String = snapshot.child("2").child("last_trade_price").getValue(String.class);
                    String currentPrice4String = snapshot.child("3").child("last_trade_price").getValue(String.class);
                    String currentPrice5String = snapshot.child("4").child("last_trade_price").getValue(String.class);

                    Float currentPrice1Float = Float.parseFloat(currentPrice1String);
                    Float currentPrice2Float = Float.parseFloat(currentPrice2String);
                    Float currentPrice3Float = Float.parseFloat(currentPrice3String);
                    Float currentPrice4Float = Float.parseFloat(currentPrice4String);
                    Float currentPrice5Float = Float.parseFloat(currentPrice5String);

                    currentPrice1.setText(currentPrice1String.substring(0, currentPrice1String.length()-4));
                    if(currentPrice1Float > first){
                        currentPrice1.setTextColor(Color.parseColor("#35C073"));
                    }else if(currentPrice1Float < first){
                        currentPrice1.setTextColor(Color.parseColor("#EE6055"));
                    }else{
                        currentPrice1.setTextColor(Color.parseColor("#BEC5AD"));
                    }

                    currentPrice2.setText(currentPrice2String.substring(0, currentPrice2String.length()-4));
                    if(currentPrice2Float > second){
                        currentPrice2.setTextColor(Color.parseColor("#35C073"));
                    }else if(currentPrice2Float < second){
                        currentPrice2.setTextColor(Color.parseColor("#EE6055"));
                    }else {
                        currentPrice2.setTextColor(Color.parseColor("#BEC5AD"));
                    }

                    currentPrice3.setText(currentPrice3String.substring(0, currentPrice3String.length()-4));
                    if(currentPrice3Float > third){
                        currentPrice3.setTextColor(Color.parseColor("#35C073"));
                    }else if(currentPrice3Float < third){
                        currentPrice3.setTextColor(Color.parseColor("#EE6055"));
                    }else{
                        currentPrice3.setTextColor(Color.parseColor("#BEC5AD"));
                    }

                    currentPrice4.setText(currentPrice4String.substring(0, currentPrice4String.length()-4));
                    if(currentPrice4Float > fourth){
                        currentPrice4.setTextColor(Color.parseColor("#35C073"));
                    }else if(currentPrice4Float < fourth){
                        currentPrice4.setTextColor(Color.parseColor("#EE6055"));
                    }else{
                        currentPrice4.setTextColor(Color.parseColor("#BEC5AD"));
                    }

                    currentPrice5.setText(currentPrice5String.substring(0, currentPrice5String.length()-4));
                    if(currentPrice5Float > fifth){
                        currentPrice5.setTextColor(Color.parseColor("#35C073"));
                    }else if(currentPrice5Float < fifth){
                        currentPrice5.setTextColor(Color.parseColor("#EE6055"));
                    }else{
                        currentPrice5.setTextColor(Color.parseColor("#BEC5AD"));
                    }

                    float boughtAtTotal = first + second + third + fourth + fifth;
                    float currentPriceTotal = currentPrice1Float + currentPrice2Float  + currentPrice3Float  + currentPrice4Float  + currentPrice5Float;
                    float total = currentPriceTotal - boughtAtTotal;
                    totalField.setText(String.valueOf(String.format("%.2f", total) + " (" + String.format("%.2f", (total/boughtAtTotal)*100) + "%" +")"));

                    if(currentPriceTotal > boughtAtTotal){
                        totalField.setTextColor(Color.parseColor("#35C073"));
                    }else if(currentPriceTotal < boughtAtTotal){
                        totalField.setTextColor(Color.parseColor("#EE6055"));
                    }else{
                        totalField.setTextColor(Color.parseColor("#BEC5AD"));
                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void declare() {
        ticker1 = findViewById(R.id.ticker_1);
        ticker2 = findViewById(R.id.ticker_2);
        ticker3 = findViewById(R.id.ticker_3);
        ticker4 = findViewById(R.id.ticker_4);
        ticker5 = findViewById(R.id.ticker_5);

        boughtAt1 = findViewById(R.id.bought_at_1);
        boughtAt2 = findViewById(R.id.bought_at_2);
        boughtAt3 = findViewById(R.id.bought_at_3);
        boughtAt4 = findViewById(R.id.bought_at_4);
        boughtAt5 = findViewById(R.id.bought_at_5);

        currentPrice1 = findViewById(R.id.current_price_1);
        currentPrice2 = findViewById(R.id.current_price_2);
        currentPrice3 = findViewById(R.id.current_price_3);
        currentPrice4 = findViewById(R.id.current_price_4);
        currentPrice5 = findViewById(R.id.current_price_5);

    }
}
