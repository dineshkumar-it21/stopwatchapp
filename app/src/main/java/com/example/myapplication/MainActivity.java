package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int seconds;
    private boolean running;
    private boolean wasRunning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
            savedInstanceState.getInt("seconds");
            savedInstanceState.getBoolean("running");
            savedInstanceState.getBoolean("wasRunning");
        }

        runtimer();
    }

    public void onStart(View view){
        running = true;

    }

    public void onStop(View view){
        running= false;
    }

    public void onReset(View view){
        running = false;
        seconds = 0;

    }

    @Override
    protected void onPause() {
        super.onPause();
        wasRunning=running;
        running=false;


    }

    @Override
    protected void onResume() {
        super.onResume();
        if(wasRunning){
            running = true;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("seconds",seconds);
        outState.putBoolean("running",running);
        outState.putBoolean("wasRunning",wasRunning);
    }

    private void runtimer() {

        final TextView timeview = findViewById(R.id.textView);
       final  Handler handler = new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds %3600)/60;
                int secs = seconds%60;

                String time = String.format(Locale.getDefault(),
                                     "%d:%02d:%02d"
                                     ,hours,minutes,secs);

                timeview.setText(time);
                if(running){
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }
        });
    }
}