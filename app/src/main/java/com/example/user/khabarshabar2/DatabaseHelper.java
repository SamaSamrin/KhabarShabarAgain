package com.example.user.khabarshabar2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.util.Log;
import android.widget.Toast;

import static android.R.attr.id;

/**
 * Created by ASUS on 4/20/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    SQLiteDatabase db;
    private static final String TAG = "**DatabaseHelper**";

    //default constructor
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {//call hocche kokhon?
        /*creates the database if not already existing
          called by framework, when db is accessed but not created*/
        //creation and initial population of tables should happen here
        try {
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS Users (" +
                    "_id INTEGER PRIMARY KEY ASC AUTOINCREMENT," +
                    "username TEXT," +
                    "email TEXT," +
                    "password TEXT );");
            Log.e(TAG, "table created");//DONE
        } catch (Exception e){
            e.printStackTrace();
            Log.e(TAG, "table not created");
        }
        //inserting values
        try {
            ContentValues values = new ContentValues();
            values.put("username", "Sama Samrin");
            values.put("email", "samasamrin@gmail.com");
            values.put("password", "123456");
            sqLiteDatabase.insert("Users", null, values);//users table-e value-gula dilam
            Log.e(TAG, "inserted data successfully");//DONE
        } catch (Exception e){
            e.printStackTrace();
            Log.e(TAG, "insert UNSUCCESSFUL");
        }
        //*****how to encrypt password?******

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //for upgrading from version i to version i1
    }

    //custom constructor cannot be made?
   /* public DatabaseHelper(SQLiteDatabase db){
        this.db = db;
    }*/

   private void manipualteData(){
       //selecting values from table
       Cursor cursor = getReadableDatabase().rawQuery("SELECT username FROM Users ;", new String[] {"id"});
       cursor.close();
       //altering values in a cell

       //deleting a row

       //adding a column

       //joining tables

   }

    @Override
    public SQLiteDatabase getReadableDatabase() {
        //to get access to the db, for reading
        return super.getReadableDatabase();
    }

    @Override
    public SQLiteDatabase getWritableDatabase() {
        //to get access to the db, for writing
        return super.getWritableDatabase();
    }
}
