package com.example.user.khabarshabar2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.widget.Toast;

import static android.R.attr.id;

/**
 * Created by ASUS on 4/20/2017.
 */

class DatabaseHelper extends SQLiteOpenHelper {

    SQLiteDatabase db;
    private static final String TAG = "**DatabaseHelper**";

    //table names
    static final String usersTableName = "Users";
    static final String foodsTableName = "Food Items";
    static final String exercisesTableName = "Exercises";

    //user table's column ids and names

    //database info
    static final String dbName = "DietBoss";
    static final int dbVersion = 1;

    //default constructor
    DatabaseHelper(Context context){
        super(context, dbName, null, dbVersion);
    }

    DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, dbName, null, dbVersion);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {//call hocche kokhon?
        /*creates the database if not already existing
          called by framework, when db is accessed but not created*/
        //creation and initial population of tables should happen here
        try {
            db.execSQL("CREATE TABLE IF NOT EXISTS "+usersTableName+" (" +
                    "_id INTEGER PRIMARY KEY ASC AUTOINCREMENT," +
                    "username TEXT," +
                    "email TEXT," +
                    "password TEXT," +
                    "gender TEXT, " +
                    "initialWeight REAL, " +
                    "height REAL," +
                    "idealWeight REAL," +
                    "currentWeight REAL );");
            //how to encrypt password
            Log.e(TAG, "users table created");//DONE
            db.execSQL("CREATE TABLE IF NOT EXISTS "+foodsTableName+" (" +
                    "_id INTEGER PRIMARY KEY ASC AUTOINCREMENT," +
                    "name TEXT," +
                    "unitOfMeasurement TEXT," +
                    "caloriePerUnit REAL );");
            Log.e(TAG, "food items table created");
            db.execSQL("CREATE TABLE IF NOT EXISTS "+exercisesTableName+" (" +
                    "_id INTEGER PRIMARY KEY ASC AUTOINCREMENT," +
                    "name TEXT," +
                    "category TEXT," +
                    "calorieLostPerMinute REAL );");
            Log.e(TAG, "exercises table created");
        } catch (Exception e){
            e.printStackTrace();
            Log.e(TAG, "some table not created");
        }
        /*//inserting values
        try {
            db.execSQL("INSERT ");
            /*ContentValues values = new ContentValues();
            values.put("username", "Sama Samrin");
            values.put("email", "samasamrin@gmail.com");
            values.put("password", "123456");
            values.put("gender", "female");
            values.put("initialWeight", "70kg");
            values.put("height", "5.2");

            db.insert("Users", null, values);//users table-e value-gula dilam
            Log.e(TAG, "inserted data successfully");//DONE
        } catch (Exception e){
            e.printStackTrace();
            Log.e(TAG, "insert UNSUCCESSFUL");
        }
        //*****how to encrypt password?******
        manipulateData();*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //for upgrading from version i to version i1
    }

    //custom constructor cannot be made?
   /* public DatabaseHelper(SQLiteDatabase db){
        this.db = db;
    }*/

   private void manipulateData(){
       //selecting values from table
       Cursor cursor = getReadableDatabase().rawQuery("SELECT username FROM Users ;", new String[] {"id"});

       int numberOfRows = cursor.getCount();
       Log.e(TAG, "number of rows = "+numberOfRows);
       if(numberOfRows!=0){
           String username = cursor.getString(cursor.getColumnIndex("username"));
           String password = cursor.getString(cursor.getColumnIndex("password"));
           Log.e(TAG, "username = "+username);
           Log.e(TAG, "password = "+password);
       }
       cursor.close();
       //altering values in a cell

       //deleting a row

       //adding a column

       //joining tables

   }

/*   @Override
//    public SQLiteDatabase getReadableDatabase() {
//        //to get access to the db, for reading
//        return super.getReadableDatabase();
//    }
//
//    @Override
//    public SQLiteDatabase getWritableDatabase() {
//        //to get access to the db, for writing
//        return super.getWritableDatabase();
//    }*/
}
