package com.example.tryingapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class Bookings extends AppCompatActivity {


    RecyclerView recyclerView;
    MyDatabaseHelper myDB;
    BookingAdapter bookingAdapter;

    ArrayList<ArrayList<String>> allData = new ArrayList<>();

    ArrayList<String> ids;
    ArrayList<String> hotelNames;

    ArrayList<String> names;
    ArrayList<String> pics;
    ArrayList<String> prices;
    ArrayList<String> days;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings);


        ids = new ArrayList<>();
        hotelNames = new ArrayList<>();
        pics = new ArrayList<>();
        names = new ArrayList<>();
        prices = new ArrayList<>();
        days = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerBooking);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        //set home selected
        bottomNavigationView.setSelectedItemId(R.id.booking);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.booking:
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), Profile.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }





        });


        myDB = new MyDatabaseHelper(Bookings.this);

        System.out.println("Its here in booking");

        storeData();
        bookingAdapter = new BookingAdapter(Bookings.this,ids,hotelNames,pics,names,prices,days);
        recyclerView.setAdapter(bookingAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Bookings.this));


    }



    void storeData(){
        Cursor cursor = myDB.readAllData();

        if(cursor.getCount()== 0){
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                ids.add(cursor.getString(0));
                hotelNames.add(cursor.getString(1));
                pics.add(cursor.getString(2));
                names.add(cursor.getString(3));
                prices.add(cursor.getString(4));
                days.add(cursor.getString(5));


            }
        }
    }
}