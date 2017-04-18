package com.example.user.khabarshabar2;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;


public class ResultPage extends Activity {

    private String gender = "default gender";
    private int age = 0;
    private int heightFeet = 0;
    private int heightInch = 0;
    private double height = 0.0;//in feet.inch
    private double weight = 0.0;//in kg.gram
    private String result = "";
    private double idealWeight = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);

        Intent i = getIntent();
        gender = i.getStringExtra("gender");
        age = i.getIntExtra("age", 0);
        height = i.getDoubleExtra("height", 0.0);//
        weight = i.getDoubleExtra("weight", 0.0);//

        TextView genderView = (TextView) findViewById(R.id.genderView);
        genderView.setText(gender);
        TextView ageView = (TextView) findViewById(R.id.ageView);
        ageView.setText(String.valueOf(age));

        heightFeet = i.getIntExtra("heightFeet",0);
        heightInch = i.getIntExtra("heightInch", 0);

        TextView heightView = (TextView) findViewById(R.id.heightView);
        heightView.setText(String.valueOf(height));
        TextView weightView = (TextView) findViewById(R.id.weightView);
        weightView.setText(String.valueOf(weight));

        result = mainCalcuation();
        TextView resultView = (TextView) findViewById(R.id.resultView);
        String smiley = "";
        if(result=="normal")
            smiley = ":D";
        else
            smiley = ":(";
        resultView.setText("According to BMI you're currently "+result+" "+smiley);

    }

    private String mainCalcuation(){
        String tempResult = "";

        double totalInch = (double) (heightFeet*12) + heightInch;
        double heightInMeters = totalInch * 0.0254;
        double BMI = weight/(heightInMeters*heightInMeters);

        if (age >= 18){
            if(BMI < 16.0)
                tempResult = "severe thinness";
            else if (BMI>=16.0 && BMI<18.5)
                tempResult = "moderate thinness";
            else if (BMI>=18.5 && BMI<25)
                tempResult = "normal";
            else if (BMI>=25 && BMI<30)
                tempResult = "overweight";
            else if (BMI>=30 && BMI<35)
                tempResult = "obese class 1";
            else if (BMI>=35 && BMI<=40)
                tempResult = "obese class 2";
            else if (BMI>40)
                tempResult = "obese class 3";
        }

        return tempResult;
    }
}
