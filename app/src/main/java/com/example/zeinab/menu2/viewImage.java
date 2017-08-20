package com.example.zeinab.menu2;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.graphics.Matrix;
import android.util.FloatMath;
import android.util.Log;



public class viewImage extends Activity {

    Zoom view ;
    private static final String TAG = "Touch";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_image);

        String f = getIntent().getStringExtra("img");
        view  = (Zoom) findViewById(R.id.imageview);

        //Intent i = getIntent();
        //File f = i.getExtras().getParcelable("img");


        //File file = new File(Uri.parse(f).getPath());
        //File file = new File("/text.jpg");
        Bitmap bmp = null;


        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inJustDecodeBounds = false;
        options.inSampleSize = 2;

        Bitmap  b = BitmapFactory.decodeResource(getApplicationContext().getResources(),getApplicationContext().getResources().
                getIdentifier(f, "drawable", getApplicationContext().getPackageName()), options);


        view .setImageBitmap(b);





//        iv.setImageURI(Uri.parse(f));
    }

}


