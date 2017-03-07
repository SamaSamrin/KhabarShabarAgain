package com.example.user.khabarshabar2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SignUp extends Activity {

    String userFullName;
    String email;
    String password;
    String dateOfBirth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    private void getAllInfo(){
        EditText fullnameField = (EditText) findViewById(R.id.fullNameInputField);
        EditText emailField = (EditText) findViewById(R.id.emailInputField);
        EditText passwordField = (EditText) findViewById(R.id.passwordField);
        EditText dateOfBirthField = (EditText) findViewById(R.id.dateOfBirthInput);
        EditText heightInFeetField = (EditText) findViewById(R.id.heightInputFeet);
        EditText heightInInchField = (EditText) findViewById(R.id.heightInputInch);
        EditText weightField = (EditText) findViewById(R.id.weightInput);
        EditText goalWeightInput = (EditText) findViewById(R.id.goalWeightInput);
        EditText goalTimeInput = (EditText) findViewById(R.id.goalTime);

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