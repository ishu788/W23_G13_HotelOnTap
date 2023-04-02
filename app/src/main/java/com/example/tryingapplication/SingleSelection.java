package com.example.tryingapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

        TextView hotelLat = findViewById(R.id.hotelLat);
        TextView hotelLong = findViewById(R.id.hotelLng);

        Button btnMap = findViewById(R.id.openMap);



//gg
        Intent intent1 = getIntent();
        String name = intent1.getStringExtra("name");
        String url = intent1.getStringExtra("location");
        String price = intent1.getStringExtra("price");
        String lat = intent1.getStringExtra("latitude");
        String lng = intent1.getStringExtra("longitude");


        hotelName.setText(name);
        hotelUrl.setText(url);
        hotelPrice.setText(price);
        hotelLat.setText(lat);
        hotelLong.setText(lng);



        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Its here");
                Uri location = Uri.parse("geo:" + lat + "," +lng );
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
                mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);

            }
        });

        btnToPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleSelection.this, PayActivity.class);
                startActivity(intent);
            }
        });


    }
}