package com.example.tryingapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PaymentDetailsActivity extends AppCompatActivity {
    Button btn,btnBack;
    TextView txtName, txtNum, txtEDt, txtSC;
    CardInfo cardInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);

        txtName = findViewById(R.id.txtNameOnCard);
        txtNum = findViewById(R.id.txtCardNumber);
        txtEDt = findViewById(R.id.txtExpDt);
        txtSC = findViewById(R.id.txtSecuityCd);
        btn = findViewById(R.id.btnPaymentDet);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog alert = new AlertDialog.Builder(PaymentDetailsActivity.this).create();
                alert.setTitle("Card App");
                alert.setMessage("Payment done successfully");
                alert.show();
            }
        });
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("card_data", (CharSequence) cardInfo);
                startActivity(intent);
            }
        });
    }
}