package com.example.stopwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int second=0 ;
    private boolean running=false ;
    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start=findViewById(R.id.btnstart);
        //savedInstanceState.getInt("Keyseconds");
        //savedInstanceState.getBoolean("keyrunning");
        //conditions();
    }

    @Override
//    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putInt("Keyseconds",second);
//        outState.putBoolean("keyrunning",running);
//    }

    protected void onResume() {
        super.onResume();
        Log.d("Mainactivity","First onResume invoked");
        running=true;
       // Toast.makeText(this, " First OnResume", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Mainactivity","First onPause invoked");
        running=false;
//        Toast.makeText(this, " First OnPause", Toast.LENGTH_SHORT).show();
    }

    public void conditions(){

        int hour = second / 3600;
        int minutes = (second % 3600) / 60 ;
        int secs = second % 60 ;
        String time = String.format(Locale.getDefault(),"%02d:%02d:%02d",hour,minutes,secs);
        start.setText(time);

        if(running){
            second++;
            // Update UI every 5 millisecond
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    conditions();
                }
            }, 5); // 1000 milliseconds = 1 second
        }



    }
    public void onclickstart(View view){
        if (running==true) {
            // If the stopwatch is already running, stop it
            running = false;
            start.setText("Resume");
        } else {
            // If the stopwatch is not running, start it
            running = true ;
            conditions();
            start.setText("Stop");
        }
    }

    public void onclickstop(View view){
        running = false;
        start.setText("Resume");

    }

    public void onclickreset(View view){
        running = false ;
        start.setText("Start");
        second=0 ;
    }
}