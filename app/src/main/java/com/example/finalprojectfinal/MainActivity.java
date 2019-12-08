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
    Double subtotal;
    Double tax;
    Double grandTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button custom = (Button)findViewById(R.id.button7);
        custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CustomPercent.class));
            }
        });

        fifteenPercent = (Button) findViewById(R.id.button);
        eighteenPercent = (Button) findViewById(R.id.percent_18);
        twentyPercent = (Button) findViewById(R.id.percent_20);
        editText = (EditText) findViewById(R.id.editText5);
        textView15 = (TextView) findViewById(R.id.textView15);
        textView11 = (TextView) findViewById(R.id.textView11);
        textView12 = (TextView) findViewById(R.id.textView12);
        textView13 = (TextView) findViewById(R.id.textView13);

    }
}
