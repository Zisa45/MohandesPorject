package com.example.zeinab.menu2;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //create directory
        File exst = Environment.getExternalStorageDirectory();
        String exstPath = exst.getPath();
        File dir = new File(exstPath+"/"+"اندیشمند نظارت");
        boolean success = dir.mkdir();

        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/B Tehran_YasDL.com.ttf");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CreateProject.class));

//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

        TextView tv = (TextView) findViewById(R.id.txt);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/B Tehran_YasDL.com.ttf");

        tv.setTypeface(custom_font);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        android.app.FragmentManager fragmentManager = getFragmentManager();

        if (id == R.id.nav_first_layout) {
            startActivity(new Intent(MainActivity.this, ListStandards.class));


        } else if (id == R.id.nav_second_layout) {
            startActivity(new Intent(MainActivity.this,ListInstructions.class));


        }
        else if (id == R.id.nav_third_layout) {
            startActivity(new Intent(MainActivity.this, ProjectList.class));


        }
        else if (id == R.id.nav_manage) {
            startActivity(new Intent(MainActivity.this, SaraTemp.class));
//            Toast.makeText(getApplicationContext() , "not implemented!" , Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_favorite) {
            Toast.makeText(getApplicationContext() , "not implemented!" , Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_search) {
            startActivity(new Intent(MainActivity.this, Search.class));
            //Toast.makeText(getApplicationContext() , "not implemented!" , Toast.LENGTH_LONG).show();
        }

        else if (id == R.id.nav_about) {
            startActivity(new Intent(MainActivity.this, About.class));
//            startActivity(new Intent(MainActivity.this, Export.class));
            //Toast.makeText(getApplicationContext() , "not implemented!" , Toast.LENGTH_LONG).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
