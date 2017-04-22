package com.example.user.khabarshabar2;

import android.app.Activity;
import android.content.Intent;
import java.util.Calendar;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import android.app.DatePickerDialog;

public class SignUp extends Activity {

    private final String TAG = "**Sign Up**";

    String userFullName;
    String email;
    String password;
    String gender;
    String dateOfBirth;
    int age;
    double height;
    double weight;
    double idealweight;

    EditText fullnameField;
    EditText emailField;
    EditText passwordField;
    EditText dateOfBirthField;
    EditText heightInFeetField;
    EditText heightInInchField;
    EditText weightField;
    EditText goalWeightInput;
    EditText goalTimeInput;
    TextView idealWeightDisplay;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //populating spinners
       /* Spinner dobDaysSpinner = (Spinner) findViewById(R.id.dobDaysSpinner);
        Spinner dobMonthsSpinner = (Spinner) findViewById(R.id.dobMonthsSpinner);
        Spinner dobYearsSpinner = (Spinner) findViewById(R.id.dobYearsSpinner);

        ArrayAdapter<CharSequence> dobDaysAdapter = ArrayAdapter.createFromResource(this, R.array.days, android.R.layout.simple_spinner_dropdown_item);
        dobDaysSpinner.setAdapter(dobDaysAdapter);

        Spinner goalTimeSpinner = (Spinner) findViewById(R.id.goalTimeSpinner);
        ArrayAdapter<CharSequence> goalTimesAdapter = ArrayAdapter.createFromResource(this, R.array.time_variants, android.R.layout.simple_spinner_dropdown_item);
        goalTimeSpinner.setAdapter(goalTimesAdapter);*/

        //setting view fields
        fullnameField = (EditText) findViewById(R.id.fullNameInputField);
        emailField = (EditText) findViewById(R.id.emailInputField);
        passwordField = (EditText) findViewById(R.id.passwordField);
        //dateOfBirthField = (EditText) findViewById(R.id.dateOfBirthInput);
        heightInFeetField = (EditText) findViewById(R.id.heightInputFeet);
        heightInInchField = (EditText) findViewById(R.id.heightInputInch);
        weightField = (EditText) findViewById(R.id.weightInput);
        goalWeightInput = (EditText) findViewById(R.id.goalWeightInput);
        goalTimeInput = (EditText) findViewById(R.id.goalTime);
        idealWeightDisplay = (TextView) findViewById(R.id.idealWeightDisplay);

        RadioButton male = (RadioButton) findViewById(R.id.maleRadioButton);
        RadioButton female = (RadioButton) findViewById(R.id.femaleRadioButton);
        //taking values from intent
        Intent i = getIntent();
        if(i!=null) {
            gender = i.getStringExtra("gender");
           // Log.e(TAG, "passed gender = "+gender);
            age = i.getIntExtra("age", 25);
            height = i.getDoubleExtra("height", 0.0);
            int tempHeightFeet = i.getIntExtra("heightInFeet", 0);
            int tempHeightInch = i.getIntExtra("heightInInch", 0);
            weight = i.getDoubleExtra("weight", 0.0);
            idealweight = i.getDoubleExtra("idealWeight", 0.0);
            //setting gender radio buttons based on given info
            if (gender.equals("Female"))
                female.setChecked(true);
            else
                male.setChecked(true);
            //setting age, height and weight from given info
            heightInFeetField.setText(String.valueOf(tempHeightFeet));
            heightInInchField.setText(String.valueOf(tempHeightInch));
            weightField.setText(String.valueOf(weight));
            idealWeightDisplay.setText("(your ideal weight - "+String.valueOf(idealweight)+")");
        }else{
            Log.e(TAG, "intent was null");
        }
    }

    public void showDatePickerDialog(View v){
        //trying to use date picker without fragment
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        //with fragment, but not working
        DialogFragment dateFragment = new DatePicker();
        FragmentActivity tempActivity = dateFragment.getActivity();
        if(tempActivity!=null) {
            FragmentManager manager = tempActivity.getSupportFragmentManager();//Attempt to invoke virtual method 'android.support.v4.app.FragmentManager android.support.v4.app.FragmentActivity.getSupportFragmentManager()' on a null object reference
            //^not sure if this is right
            dateFragment.show(manager, "datePicker");
        } else{
            Log.e(TAG, "null fragment activity");
        }
    }

    private void getAllInfo(){
        userFullName = fullnameField.getText().toString();
        email = emailField.getText().toString();
        password = passwordField.getText().toString();
    }

    public void goBackToLogin(View view){
        getAllInfo();
        Intent i = new Intent(SignUp.this, Login.class);
        startActivity(i);
    }
}