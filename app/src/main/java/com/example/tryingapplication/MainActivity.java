package com.example.tryingapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {


    OkHttpClient client;
    TextView responseTextView;


    //recycler view
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //creating a new object to capture properties of results
        rescap rescap = new rescap();

        //defining new http client
        client = new OkHttpClient();

        // reference to design elements

        responseTextView = findViewById(R.id.txt_view_first);
        Button btn_get = findViewById(R.id.button_get);

        recyclerView = findViewById(R.id.recycler_view1);



        //setting on click listener

        btn_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get();

            }

        });

    }

    public void setRecyclerView()
    {
        System.out.println(rescap.lat);
        recyclerViewAdapter = new RecyclerViewAdapter(this,rescap.names);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);

    }

    public void get()
    {
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
                                rescap.store_Data(jsonArray);
                                setRecyclerView();

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