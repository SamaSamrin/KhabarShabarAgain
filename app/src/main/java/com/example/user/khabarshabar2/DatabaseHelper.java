package com.example.user.khabarshabar2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.util.Log;
import android.widget.Toast;

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
            Log.e(TAG, "table created");
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
            Log.e(TAG, "inserted data successfully");
        } catch (Exception e){
            e.printStackTrace();
            Log.e(TAG, "insert UNSUCCESSFUL");
        }
        //*****how to encrypt password?******
        //extracting values from table
       // :|  Cursor cursor = sqLiteDatabase.query(false, "Users", "username", );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //for upgrading from version i to version i1
    }

    //custom constructor cannot be made?
   /* public DatabaseHelper(SQLiteDatabase db){
        this.db = db;
    }*/

   private String queryGenerator(String keyword, String tableName,String[] columnNames, String[] dataTypes){
       String query = keyword+" "+tableName+" ";
       if (columnNames.length  != dataTypes.length)
           query = "invalid";
       else{
           for(int i=0; i<columnNames.length; i++)
               query = query + columnNames[i]+" "+dataTypes[i]+" , ";
           query = query + ")";
       }
       return "";
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
