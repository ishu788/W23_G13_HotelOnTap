package com.example.tryingapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;



public class SingleSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_selection);

        TextView hotelName =findViewById(R.id.hotelName);
        TextView hotelUrl = findViewById(R.id.hotelLocation);
        TextView hotelPrice =findViewById(R.id.hotelPrice);
        Button btnToPay = findViewById(R.id.btnToPay);

        ImageView img1 = findViewById(R.id.img1);
        ImageView img2 = findViewById(R.id.img2);
        ImageView img3 = findViewById(R.id.img3);


        Button btnMap = findViewById(R.id.openMap);

        TextView selectedDays = findViewById(R.id.selectedDays);





//gg
        Intent intent1 = getIntent();
        String name = intent1.getStringExtra("name");
        String url = intent1.getStringExtra("location");
        String price = intent1.getStringExtra("price");
        String lat = intent1.getStringExtra("latitude");
        String lng = intent1.getStringExtra("longitude");
        String image1= intent1.getStringExtra("img1");
        String image2= intent1.getStringExtra("img2");
        String image3= intent1.getStringExtra("img3");
        String daysSelected = intent1.getStringExtra("daysSelected");





        //loading images
        Picasso.get().load(image1)
                .into(img1);
        Picasso.get().load(image2)
                .into(img2);
        Picasso.get().load(image3)
                .into(img3);

        //setting everything else
        hotelName.setText(name);
        hotelUrl.setText(url);
        hotelPrice.setText(price);
        selectedDays.setText(daysSelected);



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
                Intent intent = new Intent(SingleSelection.this, UserInformation.class);
                startActivity(intent);
            }
        });


    }
}