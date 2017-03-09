package com.example.user.khabarshabar2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class NavigationDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public String username = "default username" ;
    private String email = "default@gmail.com";
    private static final String TAG = "***ND***";

    private  DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        Intent i = getIntent();
        if(i != null) {
            username = i.getStringExtra("username");
            email = i.getStringExtra("email");
        } else{
            Log.e(TAG, "null intent");
        }


        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        View v = getLayoutInflater().inflate(R.layout.nav_header_navigation_drawer, drawer ,false);
        //inflate(the layout resource we need from another xml, the ViewGroup in this activity's xml that hosts the first resource, attachToRoot )
        if(v != null) {
            TextView usernameView = (TextView) v.findViewById(R.id.username_display);
            if (usernameView != null) {
                usernameView.setText(username);
                Log.e(TAG, "The received username is: "+username);
            } else
                Log.e(TAG, "username View of drawer header is null");

            TextView emailView = (TextView) v.findViewById(R.id.email_display);
            if (emailView != null) {
                emailView.setText(email);
                Log.e(TAG, "the received email address is: "+email);
            } else
                Log.e(TAG, "email View of drawer header is null");
        } else {
            Log.e(TAG, "inflated view is null");
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        View v = getLayoutInflater().inflate(R.layout.nav_header_navigation_drawer, drawer ,false);
        //inflate(the layout resource we need from another xml, the ViewGroup in this activity's xml that hosts the first resource, attachToRoot )
        if(v != null) {
            TextView usernameView = (TextView) v.findViewById(R.id.username_display);
            if (usernameView != null) {
                usernameView.setText(username);
                Log.e(TAG, "The received username is: "+username);
            } else
                Log.e(TAG, "username View of drawer header is null");

            TextView emailView = (TextView) v.findViewById(R.id.email_display);
            if (emailView != null) {
                emailView.setText(email);
                Log.e(TAG, "the received email address is: "+email);
            } else
                Log.e(TAG, "email View of drawer header is null");
        } else {
            Log.e(TAG, "inflated view is null");
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager manager =  getSupportFragmentManager() ;


        if (id == R.id.my_account) {
            MyAccount account = new MyAccount();
            manager.beginTransaction().add(R.id.container , account).commit();
            //add (id of the fragment container, the fragment)
        } else if (id == R.id.food_history) {
            FoodHistory foodHistory = new FoodHistory();
            manager.beginTransaction().add(R.id.container, foodHistory).commit();
        } else if (id == R.id.exercises) {
            Exercises exercises = new Exercises();
            manager.beginTransaction().add(R.id.container, exercises).commit();
        } else if (id == R.id.community) {
            Community community = new Community();
            manager.beginTransaction().add(R.id.container, community).commit();
        } else if (id == R.id.help) {

        } else if (id == R.id.logout) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
