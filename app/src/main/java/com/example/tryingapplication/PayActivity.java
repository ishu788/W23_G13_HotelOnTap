package com.example.tryingapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PayActivity extends AppCompatActivity {
    TextView txtNameOnCard;
    EditText edtName;
    TextView txtCardNumber;
    EditText edtCardNum;
    TextView txtExpirdDate;
    EditText edtExpiry;
    TextView txtSecurityCode;
    EditText edtSecurityCode;
    TextView txtCardType;
    RecyclerView recyclerView;
    Button btnPay;



    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_pay);

        CardType[] cardList = new CardType[]{

                new CardType("Master", R.drawable.mastercard),
                new CardType("VISA",R.drawable.visa),
        };
        recyclerView = findViewById(R.id.recyclerView);
        CardAdapter cardAdapter = new CardAdapter(cardList);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(cardAdapter);

        txtNameOnCard = findViewById(R.id.txtNameOnCard);
        edtName = findViewById(R.id.editNameOnCard);
        txtCardNumber = findViewById(R.id.txtCardNumber);
        edtCardNum = findViewById(R.id.editCardNumber);
        txtExpirdDate = findViewById(R.id.txtExpirdDate);
        edtExpiry = findViewById(R.id.editExpiryDate);
        txtSecurityCode = findViewById(R.id.txtSecurityCode);
        edtSecurityCode = findViewById(R.id.editSercuityCode);
        btnPay = findViewById(R.id.btnPayment);

        edtCardNum.addTextChangedListener(new TextWatcher() {

            final char space = ' ';
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int pos = 0;
                while (true){
                    if (pos >= s.length())
                        break;

                    if (space == s.charAt(pos) && (((pos + 1) % 5)
                            != 0 || pos + 1 == s.length())){
                        s.delete(pos, pos + 1);
                    }
                    else {
                        pos++;
                    }
                }
                pos = 4;
                while (true){
                    if (pos >= s.length())
                        break;
                    final char c = s.charAt(pos);

                    if ("0123456789".indexOf(c) >= 0){
                        s.insert(pos, " " + space);
                    }

                    pos += 5;
                }

            }
        });
        edtExpiry.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int in, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String ch = s.toString();

                if(ch.length() == 2 && start == 1){
                    edtExpiry.setText(ch + "/");
                    edtExpiry.setSelection(ch.length() + 1);
                }
                else if (ch.length() == 2 && before == 1){

                    ch = ch.substring(0, 1);
                    edtExpiry.setText(ch);
                    edtExpiry.setSelection(ch.length());
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CardInfo cardInfo = new CardInfo();
                cardInfo.setNameOnCard(edtName.getText().toString());
                cardInfo.setCardNumber(edtCardNum.getText().toString());
                cardInfo.setExpiryDate(edtExpiry.getText().toString());
                cardInfo.setSecurityCode(edtSecurityCode.getText().toString());

                Intent intent = new Intent(getApplicationContext(),PaymentDetailsActivity.class);
                intent.putExtra("card_data", cardInfo.toString());
                startActivity(intent);
            }
        });





    }

}