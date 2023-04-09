package com.example.tryingapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PaymentDetailsActivity extends AppCompatActivity {

    Button btn,btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);
        btn = findViewById(R.id.btnPaymentDet);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // To retrieve object in second Activity
                //CardInfo cardInfo = (CardInfo) getIntent().getSerializableExtra("card_data");

               String card_name = getIntent().getStringExtra("card_name");
               String card_num = getIntent().getStringExtra("card_num");
               String card_date = getIntent().getStringExtra("card_date");
               String card_security = getIntent().getStringExtra("card_Security");
                AlertDialog alert = new AlertDialog.Builder(PaymentDetailsActivity.this).create();
                alert.setTitle("Card App");
                alert.setMessage("Name: " + card_name + "\n"+
                                "Number:" + card_num + "\n" +
                                "date:" + card_date + "\n" +
                                "security" + card_security + "\n"
                );
                alert.show();
            }
        });
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PayActivity.class));
            }
        });
    }
}