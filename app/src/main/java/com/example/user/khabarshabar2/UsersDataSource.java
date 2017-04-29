package com.example.user.khabarshabar2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 30-Apr-17.
 */

//DAO class of Users for db connection and data manipulation

class UsersDataSource {
    private static final String TAG = "**Data Source Class**";

    private SQLiteDatabase database;
    private DatabaseHelper helper;

    private String[] allColumns = {"_id", "username", "password", "email", "gender", "height", "initialWeight", "goalWeight", "currentWeight"};

    //constructor
    UsersDataSource(Context context) {
        helper = new DatabaseHelper(context);
    }

    void open() throws SQLException {
        database = helper.getWritableDatabase();
    }

    void close() {
        helper.close();
    }

    User createUser(String username){//shudhu username input nicchi for trial
        ContentValues values = new ContentValues();
        values.put("username", username);
        long id = database.insert("Users", null, values);//arguments : tableName, nullColumnHack, ContentValues
        //returns the row id of the newly created row
        Cursor cursor = null;
        User user = null;
        if(id != -1) {
            cursor = database.query("Users", allColumns, "_id  = " + id, null, null, null, null);
            cursor.moveToFirst();
            user = cursorToUser(cursor);//ERROR//SOLVED
            cursor.close();
        }else{
            Log.e(TAG, "id returned -1");
        }
        return user;
    }

    User createUser(String email, String password){//shudhu username input nicchi for trial
        //User user = new User(email, password);//redundant
        ContentValues values = new ContentValues();
        values.put("email", email);
        values.put("password", password);
        long id = database.insert("Users", null, values);//arguments : tableName, nullColumnHack, ContentValues
        //^ returns the row id of the newly created row
        Cursor cursor = null;
        User user = null;
        if(id != -1) {
            cursor = database.query("Users", allColumns, "_id  = " + id, null, null, null, null);//no such column: goalWeight (code 1): , while compiling: SELECT
            cursor.moveToFirst();
            user = cursorToUser(cursor);
            user.id=id;
            Log.e(TAG, "user created with id="+String.valueOf(id)+" email="+email+" password="+password);
            cursor.close();
        }else{
            Log.e(TAG, "id returned -1");
        }
        return user;
    }

    void addInfoToUser(String email, String username, String gender, double height, double initialWeight){
        ContentValues values = new ContentValues();
        if(!username.equals(""))
            values.put("username", username);
        if(!gender.equals(""))
            values.put("gender", gender);
        if(height!=0.0)
            values.put("height", height);
        if(initialWeight!=0.0)
            values.put("initialWeight", initialWeight);
        User user = getUser(email);
        long userId = user.getId();
        int id = (int) database.insertWithOnConflict("Users", null, values, SQLiteDatabase.CONFLICT_IGNORE);
        if (id == -1) {
            database.update("Users", values, "_id=?", new String[] {String.valueOf(userId)});  // number 1 is the _id here, update to variable for your code
        }else{
            Log.e(TAG, "insert with conflict returned -1 ");
        }
        //user.username = username;

    }

    void setGoalWeightOfUser(String email, double goalWeight){
        ContentValues values = new ContentValues();
        if(goalWeight!=0.0)
            values.put("goalWeight", goalWeight);
        User user = getUser(email);
        long userId = user.getId();
        int id = (int) database.insertWithOnConflict("Users", null, values, SQLiteDatabase.CONFLICT_IGNORE);
        if (id == -1) {
            database.update("Users", values, "_id=?", new String[] {String.valueOf(userId)});  // number 1 is the _id here, update to variable for your code
        }else{
            Log.e(TAG, "insert with conflict returned -1 ");
        }
    }

    void deleteUser(User user){
        if (user != null) {
            long id = user.getId();
            Log.e(TAG, "user's id = "+String.valueOf(id));
            database.delete("Users", "_id =" + id, null);//delete the given id (matching with the "_id" column) from the Users table
        }else{
            Log.e(TAG, "user argument is null");
        }
    }

    void deleteUser(String username){
        if (!username.equals("")) {
            User user = getUser(username);
            long id = user.getId();
            Log.e(TAG, "user's id = "+String.valueOf(id));
            database.delete("Users", "_id =" + id, null);//delete the given id (matching with the "_id" column) from the Users table
        }else{
            Log.e(TAG, "user argument is null")    ;
        }
    }

    List<User> getAllUsers(){
        List<User> users = new ArrayList<User>();
        Cursor cursor = database.query("Users", allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            User user = cursorToUser(cursor);
            users.add(user);
            cursor.moveToNext();
        }
        cursor.close();
        return users;
    }

    List<String> getAllUsernames(){
        List<String> usernames = new ArrayList<String>();
        Cursor cursor = database.query("Users", allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            User user = cursorToUser(cursor);
            String username = user.username;
            usernames.add(username);
            cursor.moveToNext();
        }
        cursor.close();
        return usernames;
    }


    User getUser(String email){
        Cursor cursor = database.query("Users", allColumns,"email = "+email, null, null, null, null );//
        cursor.moveToFirst();
        User user = null;
        while (!cursor.isAfterLast()) {
            user = cursorToUser(cursor);
            cursor.moveToNext();
        }
        cursor.close();
        return  user;
    }

    User getUser(long id){
        Cursor cursor = database.query("Users", allColumns,"_id = "+id, null, null, null, null );
        cursor.moveToFirst();
        User user = null;
        while (!cursor.isAfterLast()) {
            user = cursorToUser(cursor);
            cursor.moveToNext();
        }
        cursor.close();
        return  user;
    }

    private User cursorToUser(Cursor cursor){
        User user = new User();
        user.id = cursor.getLong(0);
        user.username = cursor.getString(1);
        return user;
    }

}

