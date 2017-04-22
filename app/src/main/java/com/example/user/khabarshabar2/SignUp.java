package com.example.user.khabarshabar2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

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
        //setting view fields
        fullnameField = (EditText) findViewById(R.id.fullNameInputField);
        emailField = (EditText) findViewById(R.id.emailInputField);
        passwordField = (EditText) findViewById(R.id.passwordField);
        dateOfBirthField = (EditText) findViewById(R.id.dateOfBirthInput);
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
            Log.e(TAG, "passed gender = "+gender);
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
            idealWeightDisplay.setText("your ideal weight - "+String.valueOf(idealweight));
        }else{
            Log.e(TAG, "intent was null");
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