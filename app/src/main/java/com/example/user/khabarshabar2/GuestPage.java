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
    private RadioGroup genderRadioGroup;
    private EditText heightFeetInput;
    private EditText heightInchInput;
    private EditText weightInput;

    String chosenGender = "default gender";
    int chosenAge = 0;
    int heightFeet = 0;
    int heightInch = 0;
    int weight = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_page);

        genderRadioGroup = (RadioGroup) findViewById(R.id.genderGroup);

        chosenAgeDisplay = (TextView) findViewById(R.id.chosenAgeDisplay);
        ageSeekBar = (SeekBar) findViewById(R.id.ageProgressBar);
        showChosenAge();

        heightFeetInput = (EditText) findViewById(R.id.feetInput);
        heightInchInput = (EditText) findViewById(R.id.inchInput);
        weightInput = (EditText) findViewById(R.id.weightInput);
        setAllOnClickListeners();
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
        Log.e(TAG, chosenGender);//works
        //Toast.makeText(this, chosenGender, Toast.LENGTH_LONG).show();//works
    }

    private void setAllOnClickListeners(){



    }

    public void goToResultPage(View v){
        Intent i = new Intent(GuestPage.this, ResultPage.class);
        i.putExtra("gender", chosenGender);
        i.putExtra("age", chosenAge);
        startActivity(i);
    }
}
