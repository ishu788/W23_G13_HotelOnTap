package com.example.tryingapplication;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper  extends SQLiteOpenHelper {


    private Context context;
    private static final  String DATABASE_NAME= "Bookings2.db";
    private static final  int DATABASE_VERSION= 1;
    private static final  String TABLE_NAME = "booking_list";
    private static final  String COLUMN_ID = "_id";
    private static final  String COLUMN_NAME = "hotel_name";
    private static final  String COLUMN_LOCATION = "hotel_location";
    private static final  String USER_NAME = "user_name";
    private static final  String PRICE = "price";
    private static final  String DAYS = "days";



    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        this.context =context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query =
                "Create TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                               COLUMN_NAME + " TEXT, " +
                               COLUMN_LOCATION + " TEXT," +
                               USER_NAME + " TEXT, " +
                               PRICE + " TEXT, " +
                               DAYS + " TEXT);";
        db.execSQL(query);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);

    }


    void addBooking(String hotelName, String location, String userName,String price, String days){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, hotelName);
        cv.put(COLUMN_LOCATION, location);
        cv.put(USER_NAME,userName);
        cv.put(PRICE,price);
        cv.put(DAYS,days);



        long result = db.insert(TABLE_NAME, null, cv);

        if(result == -1){
            Toast.makeText(context, "application failed", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }
}
