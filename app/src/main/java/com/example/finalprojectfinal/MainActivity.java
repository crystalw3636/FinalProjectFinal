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
        if (subtotal1.getText() != null) {
            calculate15P();
            calculate18P();
            calculate20P();
        }
    }

    public void calculate15P() {
        String fifteen = subtotal1.getText().toString();
        double fifteenD = Double.parseDouble(fifteen);
        double fifteenTip = fifteenD * 0.15;
        String fifteenTwoDec = String.format("%.2f", fifteenTip);
        textView11.setText(fifteenTwoDec);
    }

    public void calculate18P() {
        String eighteen = subtotal1.getText().toString();
        double eighteenD = Double.parseDouble(eighteen);
        double eighteenTip = eighteenD * 0.18;
        String eighteenTwoDec = String.format("%.2f", eighteenTip);
        textView12.setText(eighteenTwoDec);
    }

    public void calculate20P() {
        String twenty = subtotal1.getText().toString();
        double twentyD = Double.parseDouble(twenty);
        double twentyTip = twentyD* 0.20;
        String twentyTwoDec = String.format("%.2f", twentyTip);
        textView13.setText(twentyTwoDec);
    }

}
