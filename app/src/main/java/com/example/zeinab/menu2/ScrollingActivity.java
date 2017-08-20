//package com.example.zeinab.menu2;
//
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Typeface;
//import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.text.TextUtils;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//public class ScrollingActivity extends AppCompatActivity {
//
//    DatabaseManager dbm;
////    ImageView tag;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_scrolling);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
////        tag = (ImageView) findViewById(R.id.btn_tagUpload);
//        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/B Zar_YasDL.com.ttf");
//        final EditText imageName = (EditText) findViewById(R.id.et_imageName);
//        final EditText tableName = (EditText) findViewById(R.id.et_tableName);
//        final EditText edtTitle = (EditText) findViewById(R.id.et_title);
//        final EditText edtTxt = (EditText) findViewById(R.id.et_txt);
//        TextView cancle = (TextView) findViewById(R.id.tv_cancle);
//        TextView title = (TextView) findViewById(R.id.tv_title);
//        TextView titr = (TextView) findViewById(R.id.tv_titr);
//        TextView done = (TextView) findViewById(R.id.tv_done);
//        final TextView tag = (TextView) findViewById(R.id.tv_tag);
//
//
//        imageName.setTypeface(tf);
//        tableName.setTypeface(tf);
//        edtTitle.setTypeface(tf);
//        edtTxt.setTypeface(tf);
//        cancle.setTypeface(tf);
//        title.setTypeface(tf);
//        titr.setTypeface(tf);
//        done.setTypeface(tf);
//        tag.setTypeface(tf);
//
//        dbm = new DatabaseManager(this);
//
//
//        done.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String iTitle = edtTitle.getText().toString();
//                String iTxt = edtTxt.getText().toString();
//                String iImageName = imageName.getText().toString();
//                String iTableName = tableName.getText().toString();
//
//
////                Bitmap iImage = BitmapFactory.decodeResource(getResources(), R.drawable.zeinab);
////                Bitmap iTable = BitmapFactory.decodeResource(getResources(), R.drawable.sara);
//
//
//
//                if (TextUtils.isEmpty(iTitle) || TextUtils.isEmpty(iTxt)) {
//                    Toast.makeText(ScrollingActivity.this, "Please fill all blanks! :P", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    Instruction i = new Instruction();
//                    i.title = iTitle;
//                    i.txt = iTxt;
//                    i.imageName = iImageName;
//                    i.tableName = iTableName;
//                    Log.i("Zeinab", "zisa");
//                    dbm.insertInstruction(i);
//                    Integer imageId, instructionId;
////                    imageId = dbm.getImageId(iImageName);
////                    instructionId = dbm.getInstructionId(iTitle);
//                    dbm.insertImgIns(iImageName,iTitle);
//                    dbm.inserttblIns(iTableName,iTitle);
//
//                    Toast.makeText(ScrollingActivity.this, "You did it well! :D", Toast.LENGTH_SHORT).show();
//                    Log.i("Zeinab", "Instruction Inserted!");
//                }
//            }
//        });
//
//
//        cancle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(ScrollingActivity.this, ListInstructions.class));
//            }
//        });
//
//
//
//
//
//
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//    }
//}