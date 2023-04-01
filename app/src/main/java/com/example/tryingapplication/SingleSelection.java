package com.example.tryingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class SingleSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_selection);


        Intent intent1 = getIntent();
        String name = intent1.getStringExtra("name");
        Toast.makeText(this, "name"+name, Toast.LENGTH_SHORT).show();
    }
}