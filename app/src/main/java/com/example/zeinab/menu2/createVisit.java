package com.example.zeinab.menu2;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
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
import java.util.ArrayList;

public class createVisit extends AppCompatActivity implements
        TimePickerDialog.OnTimeSetListener,
        DatePickerDialog.OnDateSetListener,

        View.OnClickListener {

    private static final String TIMEPICKER = "TimePickerDialog",
            DATEPICKER = "DatePickerDialog", MULTIDATEPICKER = "MultiDatePickerDialog";

    private Button timeButton, dateButton, multiDataButton;
    private ImageButton camera , location;
    private ImageView image;
    private GridView gv;
    private EditText txt , filenum , title;
    int PLACE_PICKER_REQUEST ;
    private ArrayList<String> pathList;
    private  DatabaseManager dbm;
    private  Place place;
    ArrayList<Bitmap> list;
    AsyncTask<Void, Void, Bitmap> mTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit);

        gv = (GridView) findViewById(R.id.gridview);
        txt = (EditText) findViewById(R.id.et_txt);
        title = (EditText) findViewById(R.id.et_title);
        filenum = (EditText) findViewById(R.id.et_fileNum);
        list = new ArrayList<Bitmap>();

        initializeViews();
        handleClicks();

        TextView done = (TextView) findViewById(R.id.tv_done);
        dbm = new DatabaseManager(this);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = dateButton.getText().toString();
                String text = txt.getText().toString();
                String location = place.getLatLng().toString();
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
                    temp.location =place.getLatLng().toString();
                    temp.title = Title;
                    temp.fileNum = Filenum;


                    Log.i("Zeinab", "zisa");
                    dbm.insertVisit(temp);

                    Toast.makeText(createVisit.this, "ساخت پروژه با موفقیت انجام شد!", Toast.LENGTH_SHORT).show();
                    Log.i("Zeinab", "Project Inserted!");
                }
            }
        });
    }

    private void initializeViews() {

        dateButton = (Button) findViewById(R.id.date_button);
        PersianCalendar now = new PersianCalendar();
        dateButton.setText(now.getPersianLongDate());

        location = (ImageButton) findViewById(R.id.location);
        camera = (ImageButton) findViewById(R.id.camera);
        //image= (ImageView) findViewById(R.id.image);
    }


    private void handleClicks() {
        dateButton.setOnClickListener(this);
        location.setOnClickListener(this);
        camera.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.date_button: {
                PersianCalendar now = new PersianCalendar();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        createVisit.this,
                        now.getPersianYear(),
                        now.getPersianMonth(),
                        now.getPersianDay()
                );

                dpd.show(getFragmentManager(), DATEPICKER);
                break;
            }

            case R.id.location: {
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
                break;
            }
            case R.id.camera: {

                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,2);

//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 2);
            }
         default:
                break;
        }
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                place = PlacePicker.getPlace(data, this);

                Toast.makeText(this, place.getName(), Toast.LENGTH_LONG).show();

            }
        }

       if (requestCode ==2)
       {

          // Bitmap bp = (Bitmap) data.getExtras().get("data");

           // image.setImageBitmap(bp);

          // File file = new File(data.getData().getPath());
           list.add((Bitmap) data.getExtras().get("data"));

           gv.setAdapter(new GridAdapter(this));
//         Uri uri = (Uri) data.getExtras().get("data");
//          image.setImageURI(uri);
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

