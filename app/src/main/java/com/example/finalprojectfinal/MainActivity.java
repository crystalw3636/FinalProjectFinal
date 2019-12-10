package com.example.finalprojectfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button fifteenPercent;
    Button eighteenPercent;
    Button twentyPercent;
    EditText editText;
    TextView textView15;
    TextView textView11;
    TextView textView12;
    TextView textView13;
    TextView subtotal1;
    double subtotal;
    double tax;
    double grandTotal;
    double tipAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getIntent();
        subtotal = getIntent().getDoubleExtra("subtotal", 0);
        tax = getIntent().getDoubleExtra("tax", 0);
        Button custom = (Button)findViewById(R.id.button7);
        custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CustomPercent.class));
            }
        });
        String subtotalPopulate = String.valueOf(subtotal);

        fifteenPercent = (Button) findViewById(R.id.button);
        eighteenPercent = (Button) findViewById(R.id.percent_18);
        twentyPercent = (Button) findViewById(R.id.percent_20);
        textView15 = (TextView) findViewById(R.id.textView15);
        textView11 = (TextView) findViewById(R.id.textView11);
        textView12 = (TextView) findViewById(R.id.textView12);
        textView13 = (TextView) findViewById(R.id.textView13);
        subtotal1 = (TextView)  findViewById(R.id.subtotal1);
        subtotal1.setText(subtotalPopulate);


    }
    public void OnClick(View view) {

        if (view.getId() == fifteenPercent.getId()){
            tipAmount = subtotal * .15;
            textView11.setText("$" + tipAmount);
            grandTotal = subtotal + tax + tipAmount;
            textView15.setText("$" + grandTotal);
        }

        if (view.getId() == eighteenPercent.getId()){
            tipAmount = subtotal * .18;
            textView12.setText("$" + tipAmount);
            grandTotal = subtotal + tax + tipAmount;
            textView15.setText("$" + grandTotal);
        }

        if (view.getId() == twentyPercent.getId()){
            tipAmount = subtotal * .2;
            textView13.setText("$" + tipAmount);
            grandTotal = subtotal + tax + tipAmount;
            textView15.setText("$" + grandTotal);
        }


    }


}
