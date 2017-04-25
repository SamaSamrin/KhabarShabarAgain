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
    DatabaseHelper helper;
    String dbname = "DietBossDB";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //creating or opening database
        String datapath = Environment.getDataDirectory().toString();
        String appname = getApplicationName(this);
        Log.e(TAG, "app name = "+appname);//DietBoss
        String dbpath = datapath+"/data/"+appname+"/databases/"+dbname;
        database = openOrCreateDatabase(dbname, MODE_PRIVATE, null);
        //^dbpath not working, needs root privileges : http://stackoverflow.com/questions/4452538/location-of-sqlite-database-on-the-device
        //so used db name instead
       // Log.e(TAG, "db path = "+dbpath);
        boolean temp = doesDatabaseExist(this, dbname);
        Log.e(TAG, "db exists? - "+String.valueOf(temp));//returns true
        //initiating helper class
        //SQLiteDatabase.CursorFactory factory = database.; //HOW TO GET THE CURSOR FACTORY?
        int version = database.getVersion();//gives 0, needed >=1
        helper = new DatabaseHelper(this, dbname, null, 1);
        //how to set/get the cursor factory if not null
        //helper.onCreate(database);//manually calling for now to check db manipulations
    }

    private boolean doesDatabaseExist(Context context, String dbname){
        File file = context.getDatabasePath(dbname);
        if (file==null)
            return false;
        else
            return true;
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

        Intent i = new Intent(Login.this, WeightDisplay.class);
        i.putExtra("username", username);
        i.putExtra("email", emailInput);
        startActivity(i);
    }

    public void goToGuestPage(View view){
        Intent i = new Intent(Login.this, GuestPage.class);
        startActivity(i);
    }
}
