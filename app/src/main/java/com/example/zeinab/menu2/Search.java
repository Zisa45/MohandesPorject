package com.example.zeinab.menu2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;


public class Search extends Activity {

    EditText etSearchTxt;
    Button btnSearch;
    ListView lv;
    DatabaseManager dbm;
    CheckBox cbIns, cbSta, cbPro;
    ArrayList insArr, staArr, prArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        etSearchTxt = (EditText) findViewById(R.id.et_search);
        btnSearch = (Button) findViewById(R.id.btn_search);
        lv = (ListView) findViewById(R.id.listView);
        cbIns = (CheckBox) findViewById(R.id.cb_instruction);
        cbSta = (CheckBox) findViewById(R.id.cb_standard);
        cbPro = (CheckBox) findViewById(R.id.cb_project);

        dbm = new DatabaseManager(this);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String searchtxt = etSearchTxt.getText().toString();

                insArr = new ArrayList();
                staArr = new ArrayList();
                prArr = new ArrayList();

                if (cbIns.isChecked()){
                    insArr = dbm.searchOnInstruction(searchtxt);
                }

                if (cbSta.isChecked()){
                    staArr = dbm.searchOnStandard(searchtxt);
                }

                if (cbPro.isChecked()){
                    prArr = dbm.searchOnProject(searchtxt);
                }

                ArrayList test = new ArrayList();
                for (int i = 0; i<insArr.size(); i++)
                    test.add(insArr.get(i).toString());

                for (int i = 0; i<staArr.size(); i++)
                    test.add(staArr.get(i).toString());

                for (int i = 0; i<prArr.size(); i++)
                    test.add(prArr.get(i).toString());

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Search.this,
                        android.R.layout.simple_list_item_1, android.R.id.text1,test);

                // Assign adapter to ListView
                lv.setAdapter(adapter);

                // ListView Item Click Listener
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // ListView Clicked item index
                        int itemPosition     = position;
                        int ins_id = 0;
                        // ListView Clicked item value
                        String  itemValue    = (String) lv.getItemAtPosition(position);


                        if(insArr.contains(itemValue)){
                            ins_id = dbm.getInstructionId(itemValue);
                            Intent intent = new Intent(getBaseContext(), ShowInstructions.class);
                            intent.putExtra("ins_id" ,Integer.toString(ins_id));
                            startActivity(intent);

                        }

                        if(staArr.contains(itemValue)){
                            ins_id = dbm.getStandardId(itemValue);
                            Intent intent = new Intent(getBaseContext(), ShowStandards.class);
                            intent.putExtra("ins_id" ,Integer.toString(ins_id));
                            startActivity(intent);

                        }

                        if(prArr.contains(itemValue)){
                            ins_id = dbm.getProjectId(itemValue);
                            Intent intent = new Intent(getBaseContext(), ShowProject.class);
                            intent.putExtra("project_id" ,Integer.toString(ins_id));
                            startActivity(intent);

                        }



//                        if (ins_id == -1){
//                            ins_id = dbm.getStandardId(itemValue);
//                        }
//
//                        Intent intent = new Intent(getBaseContext(), ShowInstructions.class);
//                        intent.putExtra("ins_id" ,Integer.toString(ins_id));
//                        startActivity(intent);

                    }
                });

            }
        });
    }
}
