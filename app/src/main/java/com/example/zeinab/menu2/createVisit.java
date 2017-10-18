package com.example.zeinab.menu2;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;

import com.mohamadamin.persianmaterialdatetimepicker.time.RadialPickerLayout;
import com.mohamadamin.persianmaterialdatetimepicker.time.TimePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class createVisit extends AppCompatActivity implements
        TimePickerDialog.OnTimeSetListener,
        DatePickerDialog.OnDateSetListener,

        View.OnClickListener {

    private static final String TIMEPICKER = "TimePickerDialog",
            DATEPICKER = "DatePickerDialog", MULTIDATEPICKER = "MultiDatePickerDialog";

    private Button timeButton, dateButton, multiDataButton;
    private ImageView image;
    private GridView gv , pgv;
    private EditText txt , filenum , title;
    int PLACE_PICKER_REQUEST ;
    private ArrayList<String> pathList;
    private  DatabaseManager dbm;
    private  Place place;
    private String project_id;
    private View lay;
    private TextView location ,project_title;
    ArrayList<Bitmap> list , plist;
    AsyncTask<Void, Void, Bitmap> mTask;
    private Button proceedings , camera;
    private static final int camera_request  = 2;
    private static final int gallery_request = 3;
    private static final int proceeding_camera_request = 4;
    private  static final int proceeding_gallery_request = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit);

        project_id = getIntent().getStringExtra("project_id");

        gv = (GridView) findViewById(R.id.gridview);
        pgv = (GridView) findViewById(R.id.gridview2);
        txt = (EditText) findViewById(R.id.et_txt);
        title = (EditText) findViewById(R.id.et_title);
        filenum = (EditText) findViewById(R.id.et_fileNum);
        project_title = (TextView) findViewById(R.id.project_title);

        proceedings = (Button) findViewById(R.id.proceedings);

        list = new ArrayList<Bitmap>();
        plist = new ArrayList<Bitmap>();

        dbm = new DatabaseManager(this);
        Project prj = new Project();
        prj = dbm.getProject(getApplicationContext(),project_id);
        project_title.setText( "عنوان پروژه "+ prj.title);


        initializeViews();
        handleClicks();

        TextView done = (TextView) findViewById(R.id.tv_done);
        dbm = new DatabaseManager(this);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = dateButton.getText().toString();
                String location;
                String text = txt.getText().toString();
                if(place != null) {
                    location = place.getLatLng().toString();
                }
                String Filenum = filenum.getText().toString();
                String Title =title.getText().toString();




                if (TextUtils.isEmpty(date) || TextUtils.isEmpty(text) || TextUtils.isEmpty(Title) || TextUtils.isEmpty(Filenum)) {
                    Toast.makeText(createVisit.this, "لطفا اطلاعات را به طور کامل پر کنید!", Toast.LENGTH_SHORT).show();
                }
                else {
                    visit temp = new visit();
                    temp.text = text;
                    temp.date = date;
                    temp.images = list;
                    if(place!= null){
                        temp.location =place.getLatLng().toString();
                    }
                    temp.title = Title;
                    temp.fileNum = Filenum;
                    temp.projectId = project_id;
                    temp.p_images = plist;


                    Log.i("Zeinab", "zisa");
                    dbm.insertVisit(temp);

                    Toast.makeText(createVisit.this, "ساخت پروژه با موفقیت انجام شد!", Toast.LENGTH_SHORT).show();
                    Log.i("Zeinab", "Project Inserted!");

                    startActivity(new Intent(createVisit.this, MainActivity.class));
                    finish();
                }
            }
        });

        TextView cancel = (TextView) findViewById(R.id.tv_cancle);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(createVisit.this, MainActivity.class));
                finish();
            }
        });
    }

    private void initializeViews() {

        dateButton = (Button) findViewById(R.id.date_button);
        PersianCalendar now = new PersianCalendar();
        dateButton.setText(now.getPersianLongDate());
        lay = findViewById(R.id.layer);

        location = (TextView) findViewById(R.id.loc_text);
        camera = (Button) findViewById(R.id.camera);
        //image= (ImageView) findViewById(R.id.image);
    }


    private void handleClicks() {
        dateButton.setOnClickListener(this);
//        location.setOnClickListener(this);
        camera.setOnClickListener(this);
        proceedings.setOnClickListener(this);
        lay.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {



        if(view.getId() == R.id.date_button) {
            PersianCalendar now = new PersianCalendar();
            DatePickerDialog dpd = DatePickerDialog.newInstance(
                    createVisit.this,
                    now.getPersianYear(),
                    now.getPersianMonth(),
                    now.getPersianDay()
            );

            dpd.show(getFragmentManager(), DATEPICKER);

        }

//       else if(view.getId() == R.id.location) {
//                //startActivity(new Intent(createVisit.this, MapsActivity.class));
//                PLACE_PICKER_REQUEST = 1;
//                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
//
//
//
//                try {
//                    startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
//                } catch (GooglePlayServicesRepairableException e) {
//                    e.printStackTrace();
//                } catch (GooglePlayServicesNotAvailableException e) {
//                    e.printStackTrace();
//                }
//
//            }


        else if(view.getId() == R.id.proceedings)
        {
            PopupMenu menu = new PopupMenu(getApplicationContext(), view);
            menu.getMenuInflater().inflate(R.menu.visit, menu.getMenu());
            menu.show();

            menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {

                    switch (item.getItemId()) {
                        case R.id.action1:
                        {Intent intent = new Intent();
                            intent.setType("image/*");
                            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                            intent.setAction(Intent.ACTION_GET_CONTENT);
                            startActivityForResult(Intent.createChooser(intent, "Select Picture"), proceeding_gallery_request);
                            return true;}

                        case R.id.action2:{
                            Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent,proceeding_camera_request);
                            return true;}

                        case R.id.action3:

                            return true;

                        default:
                            return true;

                    }
                }
            });
        }


        else if(view.getId() == R.id.camera)
        {
            PopupMenu menu = new PopupMenu(getApplicationContext(), view);
            menu.getMenuInflater().inflate(R.menu.visit, menu.getMenu());
            menu.show();

            menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {

                    switch (item.getItemId()) {
                        case R.id.action1:
                        {Intent intent = new Intent();
                            intent.setType("image/*");
                            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                            intent.setAction(Intent.ACTION_GET_CONTENT);
                            startActivityForResult(Intent.createChooser(intent, "Select Picture"), gallery_request);
                            return true;}

                        case R.id.action2:{
                            Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent,camera_request);
                            return true;}

                        case R.id.action3:

                            return true;

                        default:
                            return true;

                    }
                }
            });
        }

        else  if(view.getId() == R.id.layer)
        {
            //startActivity(new Intent(createVisit.this, MapsActivity.class));
            PLACE_PICKER_REQUEST = 1;
            PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();



            try {
                startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
            } catch (GooglePlayServicesRepairableException e) {
                e.printStackTrace();
            } catch (GooglePlayServicesNotAvailableException e) {
                e.printStackTrace();
            }
//

        }




    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                place = PlacePicker.getPlace(data, this);
                location.setText(place.getAddress());
                Toast.makeText(this, place.getName(), Toast.LENGTH_LONG).show();

            }
        }if (requestCode == camera_request)
        {
            list.add((Bitmap) data.getExtras().get("data"));
            gv.setAdapter(new GridAdapter(this , list));

        }

        if (requestCode == gallery_request)
        {

            ClipData clipData = data.getClipData();
            if(clipData != null) {
                for (int i = 0; i < clipData.getItemCount(); i++) {
                    Uri uri = clipData.getItemAt(i).getUri();

                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                        // Log.d(TAG, String.valueOf(bitmap));


                        list.add(bitmap);
                        gv.setAdapter(new GridAdapter(this, list ));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            else{
                Uri uri = data.getData();try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    // Log.d(TAG, String.valueOf(bitmap));


                    list.add(bitmap);
                    gv.setAdapter(new GridAdapter(this , list));

                } catch (IOException e) {
                    e.printStackTrace();
                }



            }

        }



        if (requestCode ==proceeding_camera_request)
        {
            plist.add((Bitmap) data.getExtras().get("data"));
            pgv.setAdapter(new GridAdapter(this , plist));

        }

        if (requestCode ==proceeding_gallery_request)
        {

            ClipData clipData = data.getClipData();
            if(clipData != null) {
                for (int i = 0; i < clipData.getItemCount(); i++) {
                    Uri uri = clipData.getItemAt(i).getUri();

                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                        // Log.d(TAG, String.valueOf(bitmap));


                        plist.add(bitmap);
                        pgv.setAdapter(new GridAdapter(this, plist ));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            else{
                Uri uri = data.getData();try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    // Log.d(TAG, String.valueOf(bitmap));


                    plist.add(bitmap);
                    pgv.setAdapter(new GridAdapter(this , plist));

                } catch (IOException e) {
                    e.printStackTrace();
                }



            }

        }
    }



    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
        String hourString = hourOfDay < 10 ? "0" + hourOfDay : "" + hourOfDay;
        String minuteString = minute < 10 ? "0" + minute : "" + minute;
        String time = "You picked the following time: " + hourString + ":" + minuteString;
        // timeTextView.setText(time);
    }



//       @Override
//    public void onDateSet(MultiDatePickerDialog view, ArrayList<PersianCalendar> selectedDays) {
//        String date = "You picked the following dates:\n\t";
//        for (PersianCalendar calendar : selectedDays) {
//            date += calendar.getPersianDay() + "/" + (calendar.getPersianMonth() + 1)
//                    + "/" + calendar.getPersianYear() + "\n\t";
//        }
//        multiDateTextView.setText(date);
//    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        // Note: monthOfYear is 0-indexed
        String Month = null;

        if(monthOfYear ==0)
            Month = "فروردین";
        if(monthOfYear ==1)
            Month = "ردیبهشت";
        if(monthOfYear ==2)
            Month = "خرداد";

        if(monthOfYear ==3)
            Month = "تیر";
        if(monthOfYear ==4)
            Month = "مرداد";
        if(monthOfYear ==5)
            Month = "شهریور";

        if(monthOfYear ==6)
            Month = "مهر";
        if(monthOfYear ==7)
            Month = "آبان";
        if(monthOfYear ==8)
            Month = "آذر";

        if(monthOfYear ==9)
            Month = "دی";
        if(monthOfYear ==10)
            Month = "بهمن";
        if(monthOfYear ==11)
            Month = "اسفند";

        String date =  dayOfMonth + " " + Month + " " +  year;
        view.getSelectedDay();
        dateButton.setText(date);
    }


    class GridAdapter extends BaseAdapter {
        public ImageView iv;
        public Bitmap bmp = null;
        public File file ;
        private Context mContext;
        private ArrayList <Bitmap> mylist;


        public GridAdapter(Context c , ArrayList<Bitmap> l) {
            mContext = c;
            mylist = l;
        }
        @Override
        public int getCount() {
            return mylist.size();
        }

        @Override
        public Object getItem(int position) {
            return mylist.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }


        class ViewHolder {

            ImageView icon;

        }


        @Override
        public View getView(final int position,  View convertView, ViewGroup parent) {
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
            imageView.setImageBitmap(mylist.get(position));

//            Log.e("sara", getFilesDir()+ file.getAbsolutePath());
//            Uri pictureUri = Uri.fromFile(file);


//                      Glide.with(mContext)
//                    .load(getFilesDir()+ file.getAbsolutePath()) // Uri of the picture
//                    .into(imageView);



            return imageView;
        }




    }


}

