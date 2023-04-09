package com.example.tryingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
                CardInfo cardInfo = (CardInfo) getIntent().getSerializableExtra("card_data");

                AlertDialog alert = new AlertDialog.Builder(PaymentDetailsActivity.this).create();
                alert.setTitle("Card App");
                alert.setMessage(cardInfo.toString());
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