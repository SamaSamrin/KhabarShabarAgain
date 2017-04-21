package com.example.user.khabarshabar2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        final ImageView iv = (ImageView) findViewById(R.id.coffee_image);
        iv.setImageResource(R.drawable.final_icon);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide);
        iv.startAnimation(animation);
       /* iv.animate().rotationBy(30).start();
            iv.animate().rotationBy(30).setDuration(10).start();
        */
    }

    public void goToLogin(View view){
        Intent i = new Intent(Welcome.this, Login.class);
        startActivity(i);
    }
}
