package com.example.tryingapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SingleSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_selection);

        TextView hotelName =findViewById(R.id.hotelName);
        TextView hotelUrl = findViewById(R.id.hotelPrice);
        TextView hotelPrice =findViewById(R.id.hotelPrice);


        Intent intent1 = getIntent();
        String name = intent1.getStringExtra("name");
        String url = intent1.getStringExtra("location");
        String price = intent1.getStringExtra("price");
        Toast.makeText(this, "name"+name, Toast.LENGTH_SHORT).show();



        hotelName.setText(name);
        hotelUrl.setText(url);
        hotelPrice.setText(price);
    }
}