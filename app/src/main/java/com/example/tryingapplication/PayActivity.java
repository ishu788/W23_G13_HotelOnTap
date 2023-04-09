package com.example.tryingapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
    CardInfo cardInfo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);


        TextView payPrice = findViewById(R.id.paymentAmount);


        String price = getIntent().getStringExtra("price");
        String personNumber = getIntent().getStringExtra("persons");

        int prices = Integer.parseInt(price);
        int persons= Integer.parseInt(personNumber);

        int finalValue = prices * persons;


        payPrice.setText( "$" + String.valueOf(finalValue));


        CardType[] cardList = new CardType[]{

                new CardType("Master", R.drawable.mastercard),
                new CardType("VISA", R.drawable.visa),
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

        edtName.setFilters(new InputFilter[]{

                new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence cs, int start, int end, Spanned dest, int dstart, int dend) {
                        return cs.toString().replaceAll("[^a-zA-Z ]*","");
                    }
                }
        });

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
                while (true) {
                    if (pos >= s.length())
                        break;

                    if (space == s.charAt(pos) && (((pos + 1) % 5)
                            != 0 || pos + 1 == s.length())) {
                        s.delete(pos, pos + 1);
                    } else {
                        pos++;
                    }
                }
                pos = 4;
                while (true) {
                    if (pos >= s.length())
                        break;
                    final char c = s.charAt(pos);

                    if ("0123456789".indexOf(c) >= 0) {
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

                if (ch.length() == 2 && start == 1) {
                    edtExpiry.setText(ch + "/");
                    edtExpiry.setSelection(ch.length() + 1);
                } else if (ch.length() == 2 && before == 1) {

                    ch = ch.substring(0, 1);
                    edtExpiry.setText(ch);
                    edtExpiry.setSelection(ch.length());
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        cardInfo = (CardInfo) getIntent().getSerializableExtra("card_data");
        if(cardInfo != null) {
            edtName.setText(cardInfo.getNameOnCard());
            edtCardNum.setText(cardInfo.getCardNumber());
            edtExpiry.setText(cardInfo.getExpiryDate());
            edtSecurityCode.setText(cardInfo.getSecurityCode());
        }

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cardInfo = new CardInfo();
                cardInfo.setNameOnCard(edtName.getText().toString());
                cardInfo.setCardNumber(edtCardNum.getText().toString());
                cardInfo.setExpiryDate(edtExpiry.getText().toString());
                cardInfo.setSecurityCode(edtSecurityCode.getText().toString());

                Intent intent = new Intent(getApplicationContext(), PaymentDetailsActivity.class);
                intent.putExtra("card_data", (CharSequence) cardInfo);
                startActivity(intent);
            }
        });

    }
}