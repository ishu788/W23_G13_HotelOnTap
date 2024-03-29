package com.example.tryingapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {


    public Button datePicker;



    OkHttpClient client;
    TextView responseTextView;


    //recycler view
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;

    private List<ListItem> listItem;
    private List<String> images_url;

    private Long startDate;
    private Long endDate;
    public Long daysDiff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Initiating and assigning bottom app bar navigation

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        //set home selected
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        return true;
                    case R.id.booking:
                        startActivity(new Intent(getApplicationContext(),Bookings.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(),Profile.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }

        });





        //Date Picker
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.clear();
        Long today = MaterialDatePicker.todayInUtcMilliseconds();
        datePicker=findViewById(R.id.bt_Pick_Date);
        datePicker.setOnClickListener(v -> DatePick());





        //creating a new object to capture properties of results
        //defining new http client
        client = new OkHttpClient();




        // reference to design elements
        Button btn_get = findViewById(R.id.button_get);
        recyclerView = findViewById(R.id.recycler_view1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItem = new ArrayList<>();


        //setting on click listener
        btn_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                get();

            }

        });

    }

    //Date Picker Function
    public void DatePick(){

        System.out.println("DatePicked");
        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
        builder.setTitleText("Pick a Date");

        final MaterialDatePicker materialDatePicker = builder.build();

        materialDatePicker.show(getSupportFragmentManager(), materialDatePicker.toString());

        materialDatePicker.addOnPositiveButtonClickListener((MaterialPickerOnPositiveButtonClickListener<Pair<Long, Long>>) selection -> {
           startDate = selection.first;
           endDate = selection.second;

            long msDiff = endDate - startDate;
            daysDiff = TimeUnit.MILLISECONDS.toDays(msDiff);

            Toast.makeText(this, "Days Select" + daysDiff, Toast.LENGTH_SHORT).show();

        });
    }






    public void get()
    {


        if(daysDiff==null){
            daysDiff=1L;
        }
        TextInputEditText txt_input  = findViewById(R.id.input_city);
        String city_search = txt_input.getText().toString();

        if(city_search.isEmpty())
        {
            Toast.makeText(this, "Please enter a valid city/place", Toast.LENGTH_SHORT).show();
        }
        else
        {
            //creating api request with preferences
            Request request = new Request.Builder()
                    .url("https://airbnb13.p.rapidapi.com/search-location?location="+ city_search + "&checkin=2023-05-16&checkout=2023-05-17&adults=1&children=0&infants=0&page=1")
                    .get()
                    .addHeader("X-RapidAPI-Key", "93b3a66a9dmsh5ae2d82728f08bcp10ba41jsncdaba24863ed")
                    .addHeader("X-RapidAPI-Host", "airbnb13.p.rapidapi.com")
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                //handling json data creating json data to string then passing it to rescap class to
                                //to convert json data into readable format.
                                String jsonString = response.body().string();
                                Gson gson = new Gson();
                                JsonElement jsonElement = gson.fromJson(jsonString, JsonElement.class);
                                JsonArray jsonArray = jsonElement.getAsJsonObject().get("results").getAsJsonArray();

                                for(int i = 0; i < jsonArray.size()/2;i++)
                                {
                                    JsonObject o = jsonArray.get(i).getAsJsonObject();
                                    JsonArray imagesArray = o.getAsJsonObject().get("images").getAsJsonArray();
                                    System.out.println(imagesArray.size());
                                   images_url = gson.fromJson(imagesArray,List.class);
                                    ListItem item = new ListItem(
                                            o.get("type").getAsString(),
                                            o.get("name").getAsString(),
                                            o.get("url").getAsString(),
                                            o.get("hostThumbnail").getAsString(),
                                            o.get("price").getAsJsonObject().get("total").getAsString(),
                                            o.get("lat").getAsString(),
                                            o.get("lng").getAsString(),
                                            images_url,
                                            daysDiff.toString()
                                    );
                                    listItem.add(item);
                                }
                                recyclerViewAdapter = new RecyclerViewAdapter(getApplicationContext(),listItem);
                                recyclerView.setAdapter(recyclerViewAdapter);
                               // rescap.store_Data(jsonArray);


                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }





    }
}