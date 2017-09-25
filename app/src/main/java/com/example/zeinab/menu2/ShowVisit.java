package com.example.zeinab.menu2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class ShowVisit extends AppCompatActivity {

    private String visitId;
    private DatabaseManager dbm;
    private visit vis;
    private TextView date , title , fileNum , text;
    private GridView gv;
    private ImageButton location , gallery;
    private ArrayList<byte[]> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_visit);



        date = (TextView) findViewById(R.id.date_button);
        title = (TextView) findViewById(R.id.et_title);
        fileNum = (TextView) findViewById(R.id.et_fileNum);
        text = (TextView) findViewById(R.id.et_txt);

        location = (ImageButton) findViewById(R.id.location);
        gallery = (ImageButton) findViewById(R.id.gallery);




        visitId = getIntent().getStringExtra("visitId");
        dbm = new DatabaseManager(this);
        vis = new visit();
        vis = dbm.getVisit(getApplicationContext(),visitId);

        date.setText(vis.date);
        title.setText(vis.title);
        fileNum.setText(vis.fileNum);
        text.setText(vis.text);


        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("sara1" , vis.location);
                startActivity(new Intent(ShowVisit.this, MapsActivity.class).putExtra("place", vis.location));
            }
        });


        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShowVisit.this, gallery.class).putExtra("visitId", visitId));
            }
        });
    }


}
