package com.example.user.khabarshabar2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Login extends Activity {

    String emailInput;
    String passwordInput;
    String username = "Sama Samrin";

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

        //set username value, from given email address in database

        Intent i = new Intent(Login.this, NavigationDrawer.class);
        i.putExtra("username", username);
        i.putExtra("email", emailInput);
        startActivity(i);
    }

    public void goToGuestPage(View view){
        Intent i = new Intent(Login.this, GuestPage.class);
        startActivity(i);
    }
}
