package com.example.zeinab.menu2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

public class First extends AppCompatActivity {

    Button btnInsert, btnRetrive;
    ImageView iv;
    DatabaseManager dbm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        btnInsert = (Button) findViewById(R.id.btn_insert);
        btnRetrive = (Button) findViewById(R.id.btn_retrive);
        iv = (ImageView) findViewById(R.id.imageView2);
        dbm = new DatabaseManager(this);

        btnInsert.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);


            }
        });

        btnRetrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bmp = dbm.RetriveEntry();
                iv.setImageBitmap(bmp);

                Toast.makeText(First.this, "Retrive Successfully! :D", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        Bitmap bm = (Bitmap) data.getExtras().get("data");
//menate hatame taie
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 0, stream);
        byte[] image = stream.toByteArray();
//

//        int size = bm.getRowBytes() * bm.getHeight();
//        ByteBuffer b = ByteBuffer.allocate(size);
//
//        bm.copyPixelsToBuffer(b);
//
//        byte[] image = new byte[size];
//
//        try {
//            b.get(image, 0, image.length);
//        } catch (BufferUnderflowException e) {
//            // always happens
//        }
        dbm.addEntry(image);
        Toast.makeText(First.this, "Save Successfully! :D", Toast.LENGTH_SHORT).show();

//        iv.setImageBitmap(bm);

    }
}
