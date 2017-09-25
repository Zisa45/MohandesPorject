package com.example.zeinab.menu2;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;

public class gallery extends AppCompatActivity {
    private GridView gv;
    String visitId;
    ArrayList <Bitmap> list;
    private DatabaseManager dbm;
    private visit vis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        list = new ArrayList<>();

        visitId = getIntent().getStringExtra("visitId");

        dbm = new DatabaseManager(this);
        vis = new visit();
        vis = dbm.getVisit(getApplicationContext(),visitId);

        list = vis.images;

        gv = (GridView) findViewById(R.id.gridview);
        gv.setAdapter(new GridAdapter(this));
    }
    class GridAdapter extends BaseAdapter {
        public ImageView iv;
        public Bitmap bmp = null;
        public File file ;
        private Context mContext;


        public GridAdapter(Context c) {
            mContext = c;
        }
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }


        class ViewHolder {

            ImageView icon;

        }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            Log.e("sara" , "this part takes time");


//            LayoutInflater inflater = getLayoutInflater();
//            convertView = getLayoutInflater().inflate(R.layout.gallery_gridsq, parent, false);
//            iv = (ImageView) convertView.findViewById(R.id.icon);
            //file = new File(Uri.parse(getItem(position).toString()).getPath());

//            return convertView;

            ImageView imageView;



            // Check whether convertView is null
            if (convertView == null) {
                // If convert is null, then this means we cannot recycle an old view,
                // and need to create a new one
                imageView = new ImageView(mContext);
                imageView.setRotation(90);

                // To make sure each image is displayed as you intended, you may need to assign some properties to
                // your ImageViews. I’m going to use setLayoutParams to specify how each image should be resized
                DisplayMetrics metrics = getApplicationContext().getResources().getDisplayMetrics();
                int screenWidth = metrics.widthPixels;
                // int dimen = (int) (getResources().getDimensionPixelSize(100)/getResources().getDisplayMetrics().density);
                imageView.setLayoutParams(new GridView.LayoutParams(screenWidth/4, screenWidth/4));
//                imageView.setLayoutParams(new GridView.LayoutParams(300, 300));
                // setScaleType defines how the image should be scaled and positioned. I’m using the CENTER_CROP
                // value as this maintains the image’s aspect ratio by scaling it in both directions, and then
                // centers the newly-scaled image.
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            } else {
                // If the view passed to getView is not null, then recycle the view
                imageView = (ImageView) convertView;
            }

            // Use the position integer to select an image from the gridviewImages array, and set it to the
            // ImageView we just created

            //mTask =  new myTask(imageView,Uri.parse(getItem(position).toString()) ).execute();
            imageView.setImageBitmap(list.get(position));

//            Log.e("sara", getFilesDir()+ file.getAbsolutePath());
//            Uri pictureUri = Uri.fromFile(file);


//                      Glide.with(mContext)
//                    .load(getFilesDir()+ file.getAbsolutePath()) // Uri of the picture
//                    .into(imageView);



            return imageView;
        }




    }
}


