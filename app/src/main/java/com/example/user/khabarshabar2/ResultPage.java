package com.example.user.khabarshabar2;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;


public class ResultPage extends Activity {

    private String gender = "default gender";
    private int age = 0;
    private double height = 0.0;//in feet.inch
    private double weight = 0.0;//in kg.gram

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);

        Intent i = getIntent();
        gender = i.getStringExtra("gender");
        age = i.getIntExtra("age", 0);
        height = i.getDoubleExtra("height", 0.0);
        weight = i.getDoubleExtra("weight", 0.0);

        TextView genderView = (TextView) findViewById(R.id.genderView);
        genderView.setText(gender);
        TextView ageView = (TextView) findViewById(R.id.ageView);
        ageView.setText(String.valueOf(age));//**** error
        TextView heightView = (TextView) findViewById(R.id.heightView);
        heightView.setText(String.valueOf(height));
        TextView weightView = (TextView) findViewById(R.id.weightView);
        weightView.setText(String.valueOf(weight));


    }
}
