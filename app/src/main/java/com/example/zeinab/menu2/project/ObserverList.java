package com.example.zeinab.menu2.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.zeinab.menu2.CreateProject;
import com.example.zeinab.menu2.DatabaseManager;
import com.example.zeinab.menu2.DesignerList;
import com.example.zeinab.menu2.R;
import com.example.zeinab.menu2.ShowInstructions;

import java.util.ArrayList;

public class ObserverList extends AppCompatActivity {

    ListView listView ;
    DatabaseManager dbm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observer_list);

        listView = (ListView) findViewById(R.id.list);
        dbm = new DatabaseManager(this);

        ArrayList test = dbm.getAllObservers();
//        Log.d("Zeianb", test.get(1).toString());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1,test);

        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  observerName    = (String) listView.getItemAtPosition(position);
//                int ins_id =dbm.getInstructionId(itemValue);
                Intent intent = new Intent(getBaseContext(), CreateProject.class);
                intent.putExtra("observerName" ,observerName);
                startActivity(intent);
                finish();

            }

//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//
//                // ListView Clicked item index
//                int itemPosition     = position;
//
//                // ListView Clicked item value
//                String  itemValue    = (String) listView.getItemAtPosition(position);
//
//                // Show Alert
//                Toast.makeText(getApplicationContext(),
//                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
//                        .show();
//
//            }
//
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ObserverList.this, CreateProject.class));
        finish();
    }
}
