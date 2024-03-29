package com.example.tryingapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class UserInformation extends AppCompatActivity {






    int selectedPerson=1;
    String choice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);


        EditText firstName = findViewById(R.id.userFirstName);
        EditText lastName = findViewById(R.id.userLastName);
        EditText phone = findViewById(R.id.userPhone);

        Intent intent = getIntent();
        String price = intent.getStringExtra("price");
        String hotelName = intent.getStringExtra("hotelName");
        String hotelPic = intent.getStringExtra("pic");
        String selectedDays = intent.getStringExtra("selectedDays");

        Spinner spinner = findViewById(R.id.spinner_people);
        ArrayList<String>arrayList  = new ArrayList<>();
        for(int i = 0; i< 4;i++){
            arrayList.add(String.valueOf(i));

        }

       ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.numberOfPeople, android.R.layout.simple_spinner_dropdown_item);
       adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       spinner.setAdapter(adapter);
       spinner.setPrompt("please Select No. of people");

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choice = parent.getItemAtPosition(position).toString();
                Toast.makeText(UserInformation.this, choice, Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button btn_Payment = findViewById(R.id.btn_Payment);
        btn_Payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!firstName.getText().toString().isEmpty()){
                    if(!lastName.getText().toString().isEmpty()){
                        if(!phone.getText().toString().isEmpty()){
                            int noOfPerson =  selectedChoice();


                            MyDatabaseHelper myDB = new MyDatabaseHelper(UserInformation.this);
                            myDB.addBooking(hotelName,hotelPic,firstName.getText().toString(),price,selectedDays);

                            String personCount =String.valueOf(noOfPerson);

                            Intent intent3 = new Intent(getApplicationContext(), PayActivity.class);
                            intent3.putExtra("persons" , personCount);
                            intent3.putExtra("price",price);


                            intent3.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            getApplicationContext().startActivity(intent3);
                        }
                        else {
                            phone.setError("First name can not be empty");
                        }
                    }
                    else {
                        lastName.setError("Last name can not be empty");
                    }
                }
                else{ firstName.setError("Phone number can not be empty");
                }

            }
        });
    }






    public int selectedChoice(){



        switch (choice){
            case "1":
                selectedPerson=1;
                break;
            case "2":
                selectedPerson=2;
                break;
            case "3":
                selectedPerson=3;
                break;
            case "4":
                selectedPerson=4;
                break;
            default:
                break;

        }
        return selectedPerson;
    }
}