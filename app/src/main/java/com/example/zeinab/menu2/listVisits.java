//package com.example.zeinab.menu2;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.graphics.Typeface;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//
//import java.util.ArrayList;
//
//public class listVisits extends Activity {
//    ListView listView;
//    DatabaseManager dbm;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_list_visits);
//
//        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/B Zar_YasDL.com.ttf");
//        // Get ListView object from xml
//        listView = (ListView) findViewById(R.id.list);
//
//
//        // Define a new Adapter
//        // First parameter - Context
//        // Second parameter - Layout for the row
//        // Third parameter - ID of the TextView to which the data is written
//        // Forth - the Array of data
//        dbm = new DatabaseManager(this);
//       // Instruction i = new Instruction();
////        listView.setTypeface(tf);
//
//        //ArrayList test = dbm.getAllInstuctions();
//        ArrayList test = dbm.getAllvisits();
//        //Log.d("Zeianb", test.get(1).toString());
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, android.R.id.text1, test);
//
//        // Assign adapter to ListView
//        listView.setAdapter(adapter);
//
//        // ListView Item Click Listener
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                // ListView Clicked item index
//                int itemPosition = position;
//
//                // ListView Clicked item value
//                String itemValue = (String) listView.getItemAtPosition(position);
//
//                // Show Alert
////                Toast.makeText(getApplicationContext(),
////                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
////                        .show();
//                // a function that returns the id of instruction with specific title
//                int visitId = dbm.getVisitId(itemValue);
//                Intent intent = new Intent(getBaseContext(), ShowVisit.class);
//                intent.putExtra("visitId", Integer.toString(visitId));
//                startActivity(intent);
//
//            }
//
////            @Override
////            public void onItemClick(AdapterView<?> parent, View view,
////                                    int position, long id) {
////
////                // ListView Clicked item index
////                int itemPosition     = position;
////
////                // ListView Clicked item value
////                String  itemValue    = (String) listView.getItemAtPosition(position);
////
////                // Show Alert
////                Toast.makeText(getApplicationContext(),
////                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
////                        .show();
////
////            }
////
//        });
//    }
//}
//
//
//
