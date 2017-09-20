package com.example.zeinab.menu2;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class similarAssortment extends Fragment {
    ListView listView ;
    Typeface tf;
    DatabaseManager dbm;
    String inst_id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inst_id = getArguments().getString("inst_id");
        View rootView = inflater.inflate(R.layout.fragment_similar, container, false);

        tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/B Zar_YasDL.com.ttf");
        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();

        listView = (ListView) getView().findViewById(R.id.list);
        dbm = new DatabaseManager(getContext());
        Instruction i = new Instruction();
//        listView.setTypeface(tf);

        //ArrayList test = dbm.getAllInstuctions();
        //ArrayList test = dbm.getSimilarInstruction(inst_id);
        ArrayList test = getArguments().getStringArrayList("similar");;

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, android.R.id.text1,test);

        // Assign adapter to ListView
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);

                // Show Alert
//                Toast.makeText(getApplicationContext(),
//                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
//                        .show();
                // a function that returns the id of instruction with specific title
                int ins_id =dbm.getInstructionId(itemValue);
                Intent intent = new Intent(getView().getContext(), ShowInstructions.class);
                intent.putExtra("ins_id" ,Integer.toString(ins_id));
                startActivity(intent);


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

}


