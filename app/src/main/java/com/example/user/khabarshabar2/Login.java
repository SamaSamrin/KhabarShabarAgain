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
    UsersDataSource usersDataSource;
    EditText emailInputField;
    EditText passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usersDataSource = new UsersDataSource(this);
        usersDataSource.open();

        emailInputField = (EditText) findViewById(R.id.emailField);
        passwordField = (EditText) findViewById(R.id.passwordField);
        /*
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
        ///initiating helper class
        //SQLiteDatabase.CursorFactory factory = database.; //HOW TO GET THE CURSOR FACTORY?
        int version = database.getVersion();//gives 0, needed >=1
        helper = new DatabaseHelper(this, dbname, null, 1);
        //how to set/get the cursor factory if not null*/
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
        emailInput = emailInputField.getText().toString();
        passwordInput = passwordField.getText().toString();

        //match given email to database users email, to find the user
        User user = usersDataSource.getUser(emailInput);
        if(user==null) {
            Log.e(TAG, "user not added in database");
            Toast.makeText(this, "You are not a member yet. Click below to sign up!", Toast.LENGTH_LONG).show();
        }
        Intent i = new Intent(Login.this, WeightDisplay.class);
        i.putExtra("username", username);
        i.putExtra("email", emailInput);
        startActivity(i);
    }

    public void goToGuestPage(View view){
        Intent i = new Intent(Login.this, GuestPage.class);
        startActivity(i);
    }

    public  void goToSignUp(View view){
        Intent i = new Intent(Login.this, SignUp.class);
        startActivity(i);
    }
}
