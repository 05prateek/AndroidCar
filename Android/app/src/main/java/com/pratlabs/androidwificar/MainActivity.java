package com.pratlabs.androidwificar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

import me.tankery.lib.circularseekbar.CircularSeekBar;


public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    ImageButton up ;
    ImageButton down ;
    ImageButton left ;
    ImageButton right;
    ImageButton stop ;
    Context app;
    String url, IPstring;
    EditText ip;
    sendreq Sendreq;
    AlertDialog alertDialog;
    CircularSeekBar seekbar;
    TextView speed;
    int x ;
    View dialogView;
    View cns;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app = this;
        Sendreq = new sendreq();
        cns=findViewById(R.id.cns);
         up = findViewById(R.id.arrow_up);
         down = findViewById(R.id.arrow_down);
         left = findViewById(R.id.arrow_left);
         right = findViewById(R.id.arrow_right);
         stop = findViewById(R.id.stop);
        ip = findViewById(R.id.editIp);
        speed=findViewById(R.id.speed);
        seekbar=findViewById(R.id.bar);
        up.setOnTouchListener(this);
        down.setOnTouchListener(this);
        left.setOnTouchListener(this);
        right.setOnTouchListener(this);
        stop.setOnTouchListener(this);
        IPstring = ip.getText().toString();
        url = "http://" + IPstring + "/";
        seekbar.setOnSeekBarChangeListener(new CircularSeekBar.OnCircularSeekBarChangeListener() {
            @Override
            public void onProgressChanged(CircularSeekBar circularSeekBar, float progress, boolean fromUser) {
                x = (int) seekbar.getProgress();
                String yo= String.valueOf(x+400);
                speed.setText(yo);
                Sendreq.posst(url,yo,cns,app);
            }

            @Override
            public void onStopTrackingTouch(CircularSeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(CircularSeekBar seekBar) {

            }
        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.info:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                ViewGroup viewGroup = findViewById(android.R.id.content);
                dialogView = LayoutInflater.from(app).inflate(R.layout.customview, viewGroup, false);
                TextView gitlink =dialogView.findViewById(R.id.gitlink);
                gitlink.setMovementMethod((LinkMovementMethod.getInstance()));
                TextView linkgmail =dialogView.findViewById(R.id.gmail);
                linkgmail.setMovementMethod((LinkMovementMethod.getInstance()));
                linkgmail.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent email=new Intent(Intent.ACTION_SENDTO).setData(Uri.parse("mailto:rawat.prateek05@gmail.com"));
                        startActivity(email);
                    }
                });


                builder.setView(dialogView);
                alertDialog = builder.create();
                builder.show();

                break;
            default:
                break;
        }

        return true;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        Vibrator vb = (Vibrator)   getSystemService(Context.VIBRATOR_SERVICE);
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            vb.vibrate(100);}

        switch (v.getId()) {
            case R.id.arrow_up:
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    up.setBackgroundResource(R.drawable.arrow_up_pressed);
                    Sendreq.posst(url, "F", v,app);}

                else if (event.getAction() == MotionEvent.ACTION_UP)
                { up.setBackgroundResource(R.drawable.arrow_up);
                    Sendreq.posst(url, "S",v,app);}
                break;

            case R.id.arrow_down:
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {down.setBackgroundResource(R.drawable.arrow_down_pressed);
                    Sendreq.posst(url, "B", v,app);}
                else if (event.getAction() == MotionEvent.ACTION_UP)
                {Sendreq.posst(url, "S", v,app);
                    down.setBackgroundResource(R.drawable.arrow_down);}

                break;
            case R.id.arrow_left:
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    left.setBackgroundResource(R.drawable.arrow_left_pressed);
                    Sendreq.posst(url, "L", v,app);}
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    Sendreq.posst(url, "S", v, app);
                    left.setBackgroundResource(R.drawable.arrow_left);
                }
                break;
            case R.id.arrow_right:
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Sendreq.posst(url, "R", v, app);
                    right.setBackgroundResource(R.drawable.arrow_right_pressed);
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    Sendreq.posst(url, "S", v, app);
                    right.setBackgroundResource(R.drawable.arrow_right);
                }

                break;
            case R.id.stop:
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    stop.setBackgroundResource(R.drawable.stop_pressed);
                    Sendreq.posst(url, "S", v,app);}
                else if (event.getAction() == MotionEvent.ACTION_UP)
                    stop.setBackgroundResource(R.drawable.stop);

                break;

            default:


        }
        return true;
    }
}



