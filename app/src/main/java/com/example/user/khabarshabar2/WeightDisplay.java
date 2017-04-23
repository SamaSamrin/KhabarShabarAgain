package com.example.user.khabarshabar2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class WeightDisplay extends Activity {

    TextView currentWeightDisplay;
    TextView goalWeightDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_display);

        currentWeightDisplay = (TextView) findViewById(R.id.currentWeightDisplay);
        goalWeightDisplay = (TextView) findViewById(R.id.goalWeightDisplay);
    }

    private void displayAllWeights(){
        //set current weight display text

        //set goal weight display text
    }

    public void goToNavigationDrawer(View v) {
        Intent i = new Intent(WeightDisplay.this, NavigationDrawer.class);
        startActivity(i);
    }
}
