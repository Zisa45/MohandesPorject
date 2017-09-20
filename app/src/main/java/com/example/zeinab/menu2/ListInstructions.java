package com.example.zeinab.menu2;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ListInstructions extends AppCompatActivity {

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;
    DatabaseManager dbm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbm = new DatabaseManager(this);
        setContentView(R.layout.activity_list_instructions);
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expandableListDetail = dbm.joinAssortWithInstruction();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);


        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        expandableListTitle.get(groupPosition) + " List Expanded.",
//                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        expandableListTitle.get(groupPosition) + " List Collapsed.",
//                        Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                String  itemValue    =  expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition);
                int ins_id =dbm.getInstructionId(itemValue);
                Intent intent = new Intent(getBaseContext(), ShowInstructions.class);
                intent.putExtra("ins_id" ,Integer.toString(ins_id));
                startActivity(intent);


//                Toast.makeText(
//                        getApplicationContext(),
//                        expandableListTitle.get(groupPosition)
//                                + " -> "
//                                + expandableListDetail.get(
//                                expandableListTitle.get(groupPosition)).get(
//                                childPosition), Toast.LENGTH_SHORT
//                ).show();
                return false;
            }
        });
    }

}




class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableListTitle;
    private HashMap<String, List<String>> expandableListDetail;

    public CustomExpandableListAdapter(Context context, List<String> expandableListTitle,
                                       HashMap<String, List<String>> expandableListDetail) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String expandedListText = (String) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item, null);
        }
        TextView expandedListTextView = (TextView) convertView
                .findViewById(R.id.expandedListItem);
        expandedListTextView.setText(expandedListText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.listTitle);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}


//
//public class ListInstructions extends Activity {
//    ListView listView ;
//    DatabaseManager dbm;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_list_instructions);
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
//        Instruction i = new Instruction();
////        listView.setTypeface(tf);
//
//        //ArrayList test = dbm.getAllInstuctions();
//        ArrayList test = dbm.getAllassortments();
//        Log.d("Zeianb", test.get(1).toString());
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, android.R.id.text1,test);
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
//                int itemPosition     = position;
//
//                // ListView Clicked item value
//                String  itemValue    = (String) listView.getItemAtPosition(position);
//
//                // Show Alert
////                Toast.makeText(getApplicationContext(),
////                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
////                        .show();
//                // a function that returns the id of instruction with specific title
//                int ins_id =dbm.getInstructionId(itemValue);
//                Intent intent = new Intent(getBaseContext(), ShowInstructions.class);
//                intent.putExtra("ins_id" ,Integer.toString(ins_id));
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
//
//
//}

