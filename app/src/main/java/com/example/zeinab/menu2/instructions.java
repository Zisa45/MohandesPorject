package com.example.zeinab.menu2;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


public class instructions extends Fragment {
    TextView text;
    String strtext;
    Typeface tf;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        strtext = getArguments().getString("inst_txt");
        View rootView = inflater.inflate(R.layout.fragment_instructions, container, false);

        tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/B Zar_YasDL.com.ttf");
        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();

        text = (TextView) getView().findViewById(R.id.mytext);
//        text.setTypeface(tf);
        text.setText(strtext);
    }
}
