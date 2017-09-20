package com.example.zeinab.menu2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zeinab.menu2.project.ObserverList;

public class CreateProject extends AppCompatActivity {

    TextView tvObserver;
    TextView tvObserverName;
    TextView tvDesigner;
    TextView tvDesignerName;
    TextView tvOwner;
    TextView tvOwnerName;
    TextView tvContractor;
    TextView tvContractorName;
    ImageButton ibAddContractor, ibAddDesigner, ibAddOwner, ibAddObserver;
    DatabaseManager dbm;

    String observerName, designerName, ownerName, contractorName;
    String title, fileNum, contractNum, address, end, start, condition;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        editor = prefs.edit();

        final EditText etTitle = (EditText) findViewById(R.id.et_title);
        final EditText etFileNum = (EditText) findViewById(R.id.et_fileNum);
        final EditText etContractNum = (EditText) findViewById(R.id.et_contractNum);
        final EditText etAddress = (EditText) findViewById(R.id.et_address);
        final EditText etStart = (EditText) findViewById(R.id.et_start);
        final EditText etEnd = (EditText) findViewById(R.id.et_end);
        final CheckBox cbCurrent = (CheckBox) findViewById(R.id.cb_current);
        final CheckBox cbStoped = (CheckBox) findViewById(R.id.cb_stoped);
        final CheckBox cbFinished = (CheckBox) findViewById(R.id.cb_finished);
        ibAddContractor = (ImageButton) findViewById(R.id.addContractor);
        ibAddDesigner = (ImageButton) findViewById(R.id.addDesigner);
        ibAddObserver = (ImageButton) findViewById(R.id.addObserver);
        ibAddOwner = (ImageButton) findViewById(R.id.addOwner);

        cbCurrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbFinished.isChecked()){
                    cbFinished.toggle();
                }
                if (cbStoped.isChecked()){
                    cbStoped.toggle();
                }
            }
        });

        cbFinished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbCurrent.isChecked()){
                    cbCurrent.toggle();
                }
                if (cbStoped.isChecked()){
                    cbStoped.toggle();
                }
            }
        });

        cbStoped.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbFinished.isChecked()){
                    cbFinished.toggle();
                }
                if (cbCurrent.isChecked()){
                    cbCurrent.toggle();
                }
            }
        });

        ibAddDesigner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = etTitle.getText().toString();
                fileNum = etFileNum.getText().toString();
                contractNum = etContractNum.getText().toString();
                address = etAddress.getText().toString();
                end = etEnd.getText().toString();
                start = etStart.getText().toString();
                condition = "";
                if (cbCurrent.isChecked()){
                    condition = "current";
                }else if (cbFinished.isChecked()){
                    condition = "finished";
                }else if (cbStoped.isChecked()){
                    condition = "stopped";
                }

                editor.putString("fileNum", fileNum);
                editor.putString("contractNum", contractNum);
                editor.putString("condition", condition);
                editor.putString("address", address);
                editor.putString("end", end);
                editor.putString("start", start);
                editor.putString("title", title);

                editor.putString("designerName", designerName);
                editor.putString("observerName", observerName);
                editor.putString("ownerName", ownerName);
                editor.putString("contractorName", contractorName);
                editor.commit();

                startActivity(new Intent(CreateProject.this, AddDesigner.class));
                finish();
            }
        });

        ibAddOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = etTitle.getText().toString();
                fileNum = etFileNum.getText().toString();
                contractNum = etContractNum.getText().toString();
                address = etAddress.getText().toString();
                end = etEnd.getText().toString();
                start = etStart.getText().toString();
                condition = "";
                if (cbCurrent.isChecked()){
                    condition = "current";
                }else if (cbFinished.isChecked()){
                    condition = "finished";
                }else if (cbStoped.isChecked()){
                    condition = "stopped";
                }

                editor.putString("fileNum", fileNum);
                editor.putString("contractNum", contractNum);
                editor.putString("condition", condition);
                editor.putString("address", address);
                editor.putString("end", end);
                editor.putString("start", start);
                editor.putString("title", title);

                editor.putString("designerName", designerName);
                editor.putString("observerName", observerName);
                editor.putString("ownerName", ownerName);
                editor.putString("contractorName", contractorName);
                editor.commit();

                startActivity(new Intent(CreateProject.this, AddOwner.class));
                finish();
            }
        });

        ibAddContractor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = etTitle.getText().toString();
                fileNum = etFileNum.getText().toString();
                contractNum = etContractNum.getText().toString();
                address = etAddress.getText().toString();
                end = etEnd.getText().toString();
                start = etStart.getText().toString();
                condition = "";
                if (cbCurrent.isChecked()){
                    condition = "current";
                }else if (cbFinished.isChecked()){
                    condition = "finished";
                }else if (cbStoped.isChecked()){
                    condition = "stopped";
                }

                editor.putString("fileNum", fileNum);
                editor.putString("contractNum", contractNum);
                editor.putString("condition", condition);
                editor.putString("address", address);
                editor.putString("end", end);
                editor.putString("start", start);
                editor.putString("title", title);

                editor.putString("designerName", designerName);
                editor.putString("observerName", observerName);
                editor.putString("ownerName", ownerName);
                editor.putString("contractorName", contractorName);
                editor.commit();

                startActivity(new Intent(CreateProject.this, AddContractor.class));
                finish();
            }
        });

        ibAddObserver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = etTitle.getText().toString();
                fileNum = etFileNum.getText().toString();
                contractNum = etContractNum.getText().toString();
                address = etAddress.getText().toString();
                end = etEnd.getText().toString();
                start = etStart.getText().toString();
                condition = "";
                if (cbCurrent.isChecked()){
                    condition = "current";
                }else if (cbFinished.isChecked()){
                    condition = "finished";
                }else if (cbStoped.isChecked()){
                    condition = "stopped";
                }

                editor.putString("fileNum", fileNum);
                editor.putString("contractNum", contractNum);
                editor.putString("condition", condition);
                editor.putString("address", address);
                editor.putString("end", end);
                editor.putString("start", start);
                editor.putString("title", title);

                editor.putString("designerName", designerName);
                editor.putString("observerName", observerName);
                editor.putString("ownerName", ownerName);
                editor.putString("contractorName", contractorName);
                editor.commit();

                startActivity(new Intent(CreateProject.this, AddObserver.class));
                finish();
            }
        });



        fileNum = prefs.getString("fileNum", "");
        contractNum = prefs.getString("contractNum", "");
        condition = prefs.getString("condition", "");
        address = prefs.getString("address", "");
        end = prefs.getString("end", "");
        start = prefs.getString("start", "");
        title = prefs.getString("title", "");

        etFileNum.setText(fileNum);
        etContractNum.setText(contractNum);
        etAddress.setText(address);
        etEnd.setText(end);
        etStart.setText(start);
        etTitle.setText(title);

        if (condition == "current"){
            cbCurrent.setChecked(true);
        }else if (condition == "finished"){
            cbFinished.setChecked(true);
        }else if (condition == "stopped"){
            cbStoped.setChecked(true);
        }

        tvObserver = (TextView) findViewById(R.id.tv_observer);
        tvObserverName = (TextView) findViewById(R.id.tv_observerName);
        tvObserver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                obClicked = true;
                title = etTitle.getText().toString();
                fileNum = etFileNum.getText().toString();
                contractNum = etContractNum.getText().toString();
                address = etAddress.getText().toString();
                end = etEnd.getText().toString();
                start = etStart.getText().toString();
                condition = "";
                if (cbCurrent.isChecked()){
                    condition = "current";
                }else if (cbFinished.isChecked()){
                    condition = "finished";
                }else if (cbStoped.isChecked()){
                    condition = "stopped";
                }

                editor.putString("fileNum", fileNum);
                editor.putString("contractNum", contractNum);
                editor.putString("condition", condition);
                editor.putString("address", address);
                editor.putString("end", end);
                editor.putString("start", start);
                editor.putString("title", title);

                editor.putString("designerName", designerName);
                editor.putString("observerName", observerName);
                editor.putString("ownerName", ownerName);
                editor.putString("contractorName", contractorName);
                editor.commit();
                startActivity(new Intent(CreateProject.this, ObserverList.class));
                finish();
            }
        });

        tvDesignerName = (TextView) findViewById(R.id.tv_designerName);
        tvDesigner = (TextView) findViewById(R.id.tv_designer);
        tvDesigner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                deClicked = true;
                title = etTitle.getText().toString();
                fileNum = etFileNum.getText().toString();
                contractNum = etContractNum.getText().toString();
                address = etAddress.getText().toString();
                end = etEnd.getText().toString();
                start = etStart.getText().toString();
                condition = "";
                if (cbCurrent.isChecked()){
                    condition = "current";
                }else if (cbFinished.isChecked()){
                    condition = "finished";
                }else if (cbStoped.isChecked()){
                    condition = "stopped";
                }

                editor.putString("fileNum", fileNum);
                editor.putString("contractNum", contractNum);
                editor.putString("condition", condition);
                editor.putString("address", address);
                editor.putString("end", end);
                editor.putString("start", start);
                editor.putString("title", title);

                editor.putString("designerName", designerName);
                editor.putString("observerName", observerName);
                editor.putString("ownerName", ownerName);
                editor.putString("contractorName", contractorName);
                editor.commit();
                startActivity(new Intent(CreateProject.this, DesignerList.class));
                finish();
            }
        });

        tvOwnerName = (TextView) findViewById(R.id.tv_ownerName);
        tvOwner = (TextView) findViewById(R.id.tv_owner);
        tvOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                deClicked = true;
                title = etTitle.getText().toString();
                fileNum = etFileNum.getText().toString();
                contractNum = etContractNum.getText().toString();
                address = etAddress.getText().toString();
                end = etEnd.getText().toString();
                start = etStart.getText().toString();
                condition = "";
                if (cbCurrent.isChecked()){
                    condition = "current";
                }else if (cbFinished.isChecked()){
                    condition = "finished";
                }else if (cbStoped.isChecked()){
                    condition = "stopped";
                }

                editor.putString("fileNum", fileNum);
                editor.putString("contractNum", contractNum);
                editor.putString("condition", condition);
                editor.putString("address", address);
                editor.putString("end", end);
                editor.putString("start", start);
                editor.putString("title", title);

                editor.putString("designerName", designerName);
                editor.putString("observerName", observerName);
                editor.putString("ownerName", ownerName);
                editor.putString("contractorName", contractorName);
                editor.commit();
                startActivity(new Intent(CreateProject.this, OwnerList.class));
                finish();
            }
        });

        tvContractorName = (TextView) findViewById(R.id.tv_contractorName);
        tvContractor = (TextView) findViewById(R.id.tv_contractor);
        tvContractor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                deClicked = true;
                title = etTitle.getText().toString();
                fileNum = etFileNum.getText().toString();
                contractNum = etContractNum.getText().toString();
                address = etAddress.getText().toString();
                end = etEnd.getText().toString();
                start = etStart.getText().toString();
                condition = "";
                if (cbCurrent.isChecked()){
                    condition = "current";
                }else if (cbFinished.isChecked()){
                    condition = "finished";
                }else if (cbStoped.isChecked()){
                    condition = "stopped";
                }

                editor.putString("fileNum", fileNum);
                editor.putString("contractNum", contractNum);
                editor.putString("condition", condition);
                editor.putString("address", address);
                editor.putString("end", end);
                editor.putString("start", start);
                editor.putString("title", title);

                editor.putString("designerName", designerName);
                editor.putString("observerName", observerName);
                editor.putString("ownerName", ownerName);
                editor.putString("contractorName", contractorName);
                editor.commit();
                startActivity(new Intent(CreateProject.this, ContractorList.class));
                finish();
            }
        });

//ob
        if (!(TextUtils.isEmpty((getIntent().getStringExtra("observerName"))))) {

            observerName = getIntent().getStringExtra("observerName");
            editor.putString("observerName", observerName);
            editor.commit();
//            obClicked = false;
        }

//        String restoredText = prefs.getString("text", null);
//        if (restoredText != null) {
        observerName = prefs.getString("observerName", "");//"No name defined" is the default value.
//        }
        tvObserverName.setText(observerName);




        if (!(TextUtils.isEmpty((getIntent().getStringExtra("designerName"))))) {

            designerName = getIntent().getStringExtra("designerName");
            editor.putString("designerName", designerName);
            editor.commit();
        }
        designerName = prefs.getString("designerName", "");//"No name defined" is the default value.
        tvDesignerName.setText(designerName);


        if (!(TextUtils.isEmpty((getIntent().getStringExtra("ownerName"))))) {

            ownerName = getIntent().getStringExtra("ownerName");
            editor.putString("ownerName", ownerName);
            editor.commit();
        }
        ownerName = prefs.getString("ownerName", "");//"No name defined" is the default value.
        tvOwnerName.setText(ownerName);


        if (!(TextUtils.isEmpty((getIntent().getStringExtra("contractorName"))))) {

            contractorName = getIntent().getStringExtra("contractorName");
            editor.putString("contractorName", contractorName);
            editor.commit();
        }
        contractorName = prefs.getString("contractorName", "");//"No name defined" is the default value.
        tvContractorName.setText(contractorName);


        ///

        TextView done = (TextView) findViewById(R.id.tv_done);
        dbm = new DatabaseManager(this);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                title = etTitle.getText().toString();
                fileNum = etFileNum.getText().toString();
                contractNum = etContractNum.getText().toString();
                address = etAddress.getText().toString();
                end = etEnd.getText().toString();
                start = etStart.getText().toString();
                condition = "";
                if (cbCurrent.isChecked()){
                    condition = "current";
                }else if (cbFinished.isChecked()){
                    condition = "finished";
                }else if (cbStoped.isChecked()){
                    condition = "stopped";
                }

                if (TextUtils.isEmpty(fileNum) || TextUtils.isEmpty(contractNum) ||
                        TextUtils.isEmpty(address) || TextUtils.isEmpty(end) ||
                        TextUtils.isEmpty(start) || TextUtils.isEmpty(contractorName) ||
                        TextUtils.isEmpty(ownerName) || TextUtils.isEmpty(designerName) ||
                        TextUtils.isEmpty(observerName) || TextUtils.isEmpty(condition)) {
                    Toast.makeText(CreateProject.this, "لطفا اطلاعات را به طور کامل پر کنید!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Project p = new Project();
                    p.title = title;
                    p.fileNum = fileNum;
                    p.contractNum = contractNum;
                    p.condition = condition;
                    p.contractorName = contractorName;
                    p.ownerName = ownerName;
                    p.designerName = designerName;
                    p.observerName = observerName;
                    p.address = address;
                    p.startDate = start;
                    p.endDate = end;

                    Log.i("Zeinab", "zisa");
                    dbm.insertProject(p);

                    Toast.makeText(CreateProject.this, "ساخت پروژه با موفقیت انجام شد!", Toast.LENGTH_SHORT).show();

                    title = "";
                    fileNum = "";
                    contractNum = "";
                    address = "";
                    end = "";
                    start = "";
                    condition = "";
                    observerName = "";
                    designerName = "";
                    ownerName = "";
                    contractorName = "";

                    editor.putString("fileNum", fileNum);
                    editor.putString("contractNum", contractNum);
                    editor.putString("condition", condition);
                    editor.putString("address", address);
                    editor.putString("end", end);
                    editor.putString("start", start);
                    editor.putString("title", title);

                    editor.putString("designerName", designerName);
                    editor.putString("observerName", observerName);
                    editor.putString("ownerName", ownerName);
                    editor.putString("contractorName", contractorName);
                    editor.commit();

                    startActivity(new Intent(CreateProject.this, MainActivity.class));
                    finish();
                    Log.i("Zeinab", "Project Inserted!");
                }
            }
        });

        TextView cancel = (TextView) findViewById(R.id.tv_cancle);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = "";
                fileNum = "";
                contractNum = "";
                address = "";
                end = "";
                start = "";
                condition = "";
                observerName = "";
                designerName = "";
                ownerName = "";
                contractorName = "";

                editor.putString("fileNum", fileNum);
                editor.putString("contractNum", contractNum);
                editor.putString("condition", condition);
                editor.putString("address", address);
                editor.putString("end", end);
                editor.putString("start", start);
                editor.putString("title", title);

                editor.putString("designerName", designerName);
                editor.putString("observerName", observerName);
                editor.putString("ownerName", ownerName);
                editor.putString("contractorName", contractorName);
                editor.commit();

                startActivity(new Intent(CreateProject.this, MainActivity.class));
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        title = "";
        fileNum = "";
        contractNum = "";
        address = "";
        end = "";
        start = "";
        condition = "";
        observerName = "";
        designerName = "";
        ownerName = "";
        contractorName = "";

        editor.putString("fileNum", fileNum);
        editor.putString("contractNum", contractNum);
        editor.putString("condition", condition);
        editor.putString("address", address);
        editor.putString("end", end);
        editor.putString("start", start);
        editor.putString("title", title);

        editor.putString("designerName", designerName);
        editor.putString("observerName", observerName);
        editor.putString("ownerName", ownerName);
        editor.putString("contractorName", contractorName);
        editor.commit();

        startActivity(new Intent(CreateProject.this, MainActivity.class));
        finish();
//        super.onBackPressed();
    }

}