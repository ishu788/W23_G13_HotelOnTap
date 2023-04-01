package com.example.tryingapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SingleSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_selection);

        TextView hotelName =findViewById(R.id.hotelName);
        TextView hotelUrl = findViewById(R.id.hotelLocation);
        TextView hotelPrice =findViewById(R.id.hotelPrice);
        Button btnToPay = findViewById(R.id.btnToPay);



//gg
        Intent intent1 = getIntent();
        String name = intent1.getStringExtra("name");
        String url = intent1.getStringExtra("location");
        String price = intent1.getStringExtra("price");
        Toast.makeText(this, "name"+name, Toast.LENGTH_SHORT).show();


        hotelName.setText(name);
        hotelUrl.setText(url);
        hotelPrice.setText(price);



        btnToPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleSelection.this, PayActivity.class);
                startActivity(intent);
            }
        });
    }
}