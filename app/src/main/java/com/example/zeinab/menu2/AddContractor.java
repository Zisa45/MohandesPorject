package com.example.zeinab.menu2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddContractor extends AppCompatActivity {

    DatabaseManager dbm;
    String name, address, phoneNum, tel, fax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contractor);
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
                    Toast.makeText(AddContractor.this, "لطفا اطلاعات را به طور کامل پر کنید!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Superviser s = new Superviser();
                    s.name = name;
                    s.phoneNumber = phoneNum;
                    s.tel = tel;
                    s.fax = fax;
                    s.address = address;

                    Log.i("Zeinab", "zisa");
                    dbm.insertContractor(s);

                    Toast.makeText(AddContractor.this, "ساخت پیمانکار با موفقیت انجام شد!", Toast.LENGTH_SHORT).show();


                    startActivity(new Intent(AddContractor.this, CreateProject.class));
                    finish();
                    Log.i("Zeinab", "Owner Inserted!");
                }
            }
        });


        TextView cancel = (TextView) findViewById(R.id.tv_cancle);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(AddContractor.this, CreateProject.class));
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(AddContractor.this, CreateProject.class));
        finish();
    }
}
