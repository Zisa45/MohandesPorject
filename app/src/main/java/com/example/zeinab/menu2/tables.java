package com.example.zeinab.menu2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;

public class tables extends Fragment {
    GridView gv;
    ArrayList<String> list;
    Context context;
    Bitmap bmp;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tables, container, false);


        File f = new File(Environment.getExternalStorageDirectory().toString());

        list = getArguments().getStringArrayList("inst_table");

        gv = (GridView) rootView.findViewById(R.id.gridview);
        gv.setAdapter(new tables.GridAdapter(getActivity()));

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity().getApplicationContext(), viewImage.class).putExtra("img", list.get(position).toString()));
            }
        });
        return rootView;
    }


    class GridAdapter extends BaseAdapter {
        private Context mContext;

        public GridAdapter(Context c) {
            mContext = c;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }


        class ViewHolder {

            ImageView icon;

        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = getActivity().getLayoutInflater();
//            convertView = inflater.inflate(R.layout.gallery_gridsq, null);
            convertView = getActivity().getLayoutInflater().inflate(R.layout.gallery_gridsq, parent, false);
            ImageView iv = (ImageView) convertView.findViewById(R.id.icon);
            Log.e("sara", list.get(position));

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            options.inJustDecodeBounds = false;
            options.inSampleSize = 8;

            Bitmap b = BitmapFactory.decodeResource(mContext.getResources(), mContext.getResources().
                    getIdentifier(list.get(position), "drawable", mContext.getPackageName()), options);


            iv.setImageBitmap(b);

            // iv.setImageURI(Uri.parse(getItem(position).toString()));

            return convertView;
        }
    }
}
