package com.example.tryingapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class PayActivity extends AppCompatActivity {

    TextView txtCardType;
    RadioGroup CardType;
    TextView txtNameOnCard;
    EditText editNameOnCard;
    TextView txtCardNumber;
    EditText editCardNumber;
    TextView txtExpiryDate;
    EditText editExpiryDate;
    TextView txtSecurityCode;
    EditText editSecurityCode;
    Button   btnPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        txtCardType = findViewById(R.id.txtCardType);
        CardType = findViewById(R.id.cardtype);
        txtNameOnCard = findViewById(R.id.txtNameOnCard);
        editNameOnCard = findViewById(R.id.editNameOnCard);
        txtCardNumber = findViewById(R.id.txtCardNumber);
        editCardNumber = findViewById(R.id.editCardNumber);
        txtExpiryDate = findViewById(R.id.txtExpiryCode);
        editExpiryDate = findViewById(R.id.editExpiryCode);
        txtSecurityCode = findViewById(R.id.txtSecurityCode);
        editSecurityCode = findViewById(R.id.editSecurityCode);
        btnPay = findViewById(R.id.btnPay);

        CardType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radVisaCard:

                        break;

                    case R.id.radMasterCard:

                        break;

                    case R.id.radAmExCard:

                        break;

                    case R.id.radGooglePay:

                        break;
                }
            }
        });

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nameoncard = txtNameOnCard.getText().toString();

                String cardnumber = editCardNumber.getText().toString();

                String expirydate = editExpiryDate.getText().toString();

                String securitycode = editSecurityCode.getText().toString();

                String message = "Name on the card: " + nameoncard + "\n" + "Card number: "
                        + cardnumber + "\n" + "Card Expiry date: " + expirydate + "\n"
                        + "Card Security Code: " + securitycode;

                AlertDialog.Builder builder = new AlertDialog.Builder(PayActivity.this);
                builder.setMessage(message)
                        .setTitle("Card Information")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();




            }
        });


    }
}