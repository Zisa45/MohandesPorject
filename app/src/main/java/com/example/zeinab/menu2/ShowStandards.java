package com.example.zeinab.menu2;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class ShowStandards extends AppCompatActivity {
    DatabaseManager dbm;
    TextView title;
    Standardclass stn;
    String ins_id;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_standards);


        title = (TextView) findViewById(R.id.tv_title);



        ins_id = getIntent().getStringExtra("ins_id");
        dbm = new DatabaseManager(this);
        stn = new Standardclass();
        stn = dbm.getStandard(getApplicationContext(),ins_id);

        title.setText(stn.title);
        //Toast.makeText(getApplicationContext(),inst.title+inst.txt, Toast.LENGTH_LONG).show();

        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/B Tehran_YasDL.com.ttf");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(tf);
                    ((TextView) tabViewChild).setTextSize(20);
                }
            }
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_standards, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            switch (position) {
                case 0:

//                    bundle.putString("inst_title", inst.title);
                    bundle.putString("inst_txt", stn.txt);
                    instructions tab1 = new instructions();
                    tab1.setArguments(bundle);
                    return tab1;
                case 1:
                    bundle.putStringArrayList("inst_image" , stn.imageName);
                    images tab2 = new images();
                    tab2.setArguments(bundle);
                    return tab2;
                case 2:
                    tables tab3 = new tables();
                    bundle.putStringArrayList("inst_table" , stn.tableName);
                    tab3.setArguments(bundle);
                    return tab3;
                case 3:
                    similarAssortment tab4 = new similarAssortment();
                    ArrayList test = dbm.getSimilarStandard(ins_id);
                    bundle.putStringArrayList("similar" , test);
                    tab4.setArguments(bundle);
                    return tab4;
                default:
                    return null;
            }
        }
        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "شرح دستورالعمل";
                case 1:
                    return "تصاویر";
                case 2:
                    return "جداول";
                case 3:
                    return "مشابه ها";
            }
            return null;
        }
    }
}
