package com.example.zeinab.menu2;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ScrollingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/B Zar_YasDL.com.ttf");
        TextView tv = (TextView) findViewById(R.id.textView3);
        TextView tv6 = (TextView) findViewById(R.id.textView6);
        TextView tv5 = (TextView) findViewById(R.id.textView5);
        TextView tv7 = (TextView) findViewById(R.id.textView7);
        EditText et = (EditText) findViewById(R.id.editText);


        tv.setTypeface(tf);
        tv5.setTypeface(tf);
        tv6.setTypeface(tf);
        tv7.setTypeface(tf);
        et.setTypeface(tf);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
