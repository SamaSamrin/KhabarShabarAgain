package com.example.user.khabarshabar2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.io.File;

public class Login extends Activity {

    private final String TAG = "**Login**";

    String emailInput = "default@email.com";
    String passwordInput = "123456";
    String username = "default name";
    static SQLiteDatabase database;
    String dbname = "DietBossDB";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        String datapath = Environment.getDataDirectory().toString();
        String appname = getApplicationName(this);
        Log.e(TAG, "app name = "+appname);//DietBoss
        String dbpath = datapath+"/data/"+appname+"/databases/"+dbname;
        //database = openOrCreateDatabase(dbpath, MODE_PRIVATE, null);
        //^ not working, needs root privileges
        Log.e(TAG, "db path = "+dbpath);
    }

    public static String getApplicationName(Context context){
        String appname = "";
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        int stringId = applicationInfo.labelRes;
        if(stringId==0)
            appname = applicationInfo.nonLocalizedLabel.toString();
        else
            appname = context.getString(stringId);
        return appname;
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
