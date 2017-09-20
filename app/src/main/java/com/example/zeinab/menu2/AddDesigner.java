package com.example.zeinab.menu2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddDesigner extends AppCompatActivity {

    DatabaseManager dbm;
    String name, address, phoneNum, tel, fax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_designer);


        final EditText etName = (EditText) findViewById(R.id.et_name);
        final EditText etPhoneNum = (EditText) findViewById(R.id.et_phoneNum);
        final EditText etTel = (EditText) findViewById(R.id.et_tel);
        final EditText etFax = (EditText) findViewById(R.id.et_fax);
        final EditText etAddress = (EditText) findViewById(R.id.et_address);
        TextView done = (TextView) findViewById(R.id.tv_done);
        dbm = new DatabaseManager(this);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = etName.getText().toString();
                phoneNum = etPhoneNum.getText().toString();
                tel = etTel.getText().toString();
                fax = etFax.getText().toString();
                address = etAddress.getText().toString();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phoneNum) ||
                        TextUtils.isEmpty(tel) || TextUtils.isEmpty(fax) ||
                        TextUtils.isEmpty(address) ) {
                    Toast.makeText(AddDesigner.this, "لطفا اطلاعات را به طور کامل پر کنید!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Superviser s = new Superviser();
                    s.name = name;
                    s.phoneNumber = phoneNum;
                    s.tel = tel;
                    s.fax = fax;
                    s.address = address;

                    Log.i("Zeinab", "zisa");
                    dbm.insertDesigner(s);

                    Toast.makeText(AddDesigner.this, "ساخت طراح با موفقیت انجام شد!", Toast.LENGTH_SHORT).show();


                    startActivity(new Intent(AddDesigner.this, CreateProject.class));
                    finish();
                    Log.i("Zeinab", "Designer Inserted!");
                }
            }
        });


        TextView cancel = (TextView) findViewById(R.id.tv_cancle);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(AddDesigner.this, CreateProject.class));
                finish();
            }
        });

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(AddDesigner.this, CreateProject.class));
        finish();
    }
}
