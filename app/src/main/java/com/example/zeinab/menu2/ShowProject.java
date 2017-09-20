package com.example.zeinab.menu2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class ShowProject extends AppCompatActivity {

    TextView tvObserver;
    TextView tvObserverName;
    TextView tvDesigner;
    TextView tvDesignerName;
    TextView tvOwner;
    TextView tvOwnerName;
    TextView tvContractor;
    TextView tvContractorName;
    DatabaseManager dbm;
    Project prj;
    String project_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_project);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvObserver = (TextView) findViewById(R.id.tv_observer);
        tvObserverName = (TextView) findViewById(R.id.tv_observerName);
        tvDesignerName = (TextView) findViewById(R.id.tv_designerName);
        tvDesigner = (TextView) findViewById(R.id.tv_designer);
        tvOwnerName = (TextView) findViewById(R.id.tv_ownerName);
        tvOwner = (TextView) findViewById(R.id.tv_owner);
        tvContractorName = (TextView) findViewById(R.id.tv_contractorName);
        tvContractor = (TextView) findViewById(R.id.tv_contractor);

        final TextView etTitle = (TextView) findViewById(R.id.et_title);
        final TextView etFileNum = (TextView) findViewById(R.id.et_fileNum);
        final TextView etContractNum = (TextView) findViewById(R.id.et_contractNum);
        final TextView etAddress = (TextView) findViewById(R.id.et_address);
        final TextView etStart = (TextView) findViewById(R.id.et_start);
        final TextView etEnd = (TextView) findViewById(R.id.et_end);
        final TextView etCondition = (TextView) findViewById(R.id.et_condition);

        project_id = getIntent().getStringExtra("project_id");
        dbm = new DatabaseManager(this);
        prj = new Project();
        prj = dbm.getProject(getApplicationContext(),project_id);

        etTitle.setText(prj.title);
        etFileNum.setText(prj.fileNum);
        etContractNum.setText(prj.contractNum);
        etAddress.setText(prj.address);
        etStart.setText(prj.startDate);
        etEnd.setText(prj.endDate);
        tvObserverName.setText(prj.observerName);
        tvContractorName.setText(prj.contractorName);
        tvDesignerName.setText(prj.designerName);
        tvOwnerName.setText(prj.ownerName);

        if (prj.condition.equals("finished"))
            etCondition.setText("اتمام");
        else if (prj.condition.equals("current"))
            etCondition.setText("جاری");
        else if (prj.condition.equals("stopped"))
            etCondition.setText("متوقف");




//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }
}
