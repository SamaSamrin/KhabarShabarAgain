package com.example.user.khabarshabar2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.IdRes;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class GuestPage extends Activity {

    private final String TAG = "**GuestPage**";

    private TextView chosenAgeDisplay;
    private SeekBar ageSeekBar;
    private EditText heightFeetInput;
    private EditText heightInchInput;
    private EditText weightInput;

    String chosenGender = "default gender";
    int chosenAge = 0;
    double height = 0.0;
    double weight = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_page);

        chosenAgeDisplay = (TextView) findViewById(R.id.chosenAgeDisplay);
        ageSeekBar = (SeekBar) findViewById(R.id.ageProgressBar);
        showChosenAge();
        if (chosenAge == 0)
            chosenAge = 25;

        heightFeetInput = (EditText) findViewById(R.id.feetInput);
        heightInchInput = (EditText) findViewById(R.id.inchInput);
        weightInput = (EditText) findViewById(R.id.weightInput);
    }

    private void showChosenAge(){
        ageSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                String currentAgeString = String.valueOf(i);
                chosenAgeDisplay.setText(currentAgeString);
                chosenAge = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void onSelectedRadioButton(View v){

        RadioButton selectedGender = (RadioButton) v;
        boolean selected = selectedGender.isChecked();

        switch (v.getId()){
            case R.id.male:
                if(selected){
                    chosenGender = selectedGender.getText().toString();
                }
                break;
            case R.id.female:
                if(selected){
                    chosenGender = selectedGender.getText().toString();
                }
                break;
        }
       // Log.e(TAG, chosenGender);//works
        //Toast.makeText(this, chosenGender, Toast.LENGTH_LONG).show();//works
    }


    public void goToResultPage(View v){
        //getting inputs
        String heightFeet = heightFeetInput.getText().toString();
        int heightFeetInt = Integer.parseInt(heightFeet);
        double tempFeet = Double.parseDouble(heightFeet);
        String heightInch = heightInchInput.getText().toString();
        int heightInchInt = Integer.valueOf(heightInch);
        double tempInch = 0.0;
        if  (heightInchInt<=9 && heightInchInt>=0)
            tempInch = (double) heightInchInt/10;
        else if (heightInchInt>9)
            tempInch = (double) heightInchInt/100;
        height = tempFeet + tempInch;
        String weightString = weightInput.getText().toString();
        weight = Double.parseDouble(weightString);// null object reference
        //sending intent
        Intent i = new Intent(GuestPage.this, ResultPage.class);
        i.putExtra("gender", chosenGender);
        i.putExtra("age", chosenAge);
        i.putExtra("heightFeet", heightFeetInt);
        i.putExtra("heightInch", heightInchInt);
        i.putExtra("height", height);
        i.putExtra("weight", weight);
        startActivity(i);
    }
}
