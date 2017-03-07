package com.example.user.khabarshabar2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Login extends Activity {

    String emailInput;
    String passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void goOnClick(View view){
        EditText emailInputField = (EditText) findViewById(R.id.emailField);
        EditText passwordField = (EditText) findViewById(R.id.passwordField);
        emailInput = emailInputField.getText().toString();
        passwordInput = passwordField.getText().toString();

        Intent i = new Intent(Login.this, NavigationDrawer.class);
        startActivity(i);
    }

    public void goToSignUp(View view){
        Intent i = new Intent(Login.this, SignUp.class);
        startActivity(i);
    }
}
