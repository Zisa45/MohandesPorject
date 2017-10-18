package com.example.zeinab.menu2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dropbox.core.v2.users.FullAccount;
import com.squareup.picasso.Picasso;

import java.io.File;

public class SaraTemp extends AppCompatActivity {

    private static final int IMAGE_REQUEST_CODE = 101;
    private String ACCESS_TOKEN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sara_temp);


        if (!tokenExists()) {
            //No token
            //Back to LoginActivity
            Intent intent = new Intent(SaraTemp.this, Login.class);
            startActivity(intent);
        }

        ACCESS_TOKEN = retrieveAccessToken();
        getUserAccount();

//        Button fab = (Button) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                upload();
//            }
//        });

    }

    private boolean tokenExists() {
        SharedPreferences prefs = getSharedPreferences("com.example.valdio.dropboxintegration", Context.MODE_PRIVATE);
        String accessToken = prefs.getString("access-token", null);
        return accessToken != null;
    }

    private String retrieveAccessToken() {
        //check if ACCESS_TOKEN is stored on previous app launches
        SharedPreferences prefs = getSharedPreferences("com.example.valdio.dropboxintegration", Context.MODE_PRIVATE);
        String accessToken = prefs.getString("access-token", null);
        if (accessToken == null) {
            Log.d("AccessToken Status", "No token found");
            return null;
        } else {
            //accessToken already exists
            Log.d("AccessToken Status", "Token exists");
            return accessToken;
        }
    }


    protected void getUserAccount() {
        if (ACCESS_TOKEN == null)return;
        new UserAccountTask(DropboxClient.getClient(ACCESS_TOKEN), new UserAccountTask.TaskDelegate() {
            @Override
            public void onAccountReceived(FullAccount account) {
                //Print account's info
                Log.d("User", account.getEmail());
                Log.d("User", account.getName().getDisplayName());
                Log.d("User", account.getAccountType().name());
                updateUI(account);
            }
            @Override
            public void onError(Exception error) {
                Log.d("User", "Error receiving account details.");
            }
        }).execute();
    }

    private void updateUI(FullAccount account) {
//        ImageView profile = (ImageView) findViewById(R.id.imageView);
//        TextView name = (TextView) findViewById(R.id.name_textView);
        TextView email = (TextView) findViewById(R.id.email_textView);

//        name.setText(account.getName().getDisplayName());
        email.setText(account.getEmail());
//        Picasso.with(this)
//                .load(account.getProfilePhotoUrl())
//                .resize(200, 200)
//                .into(profile);
    }

    private void upload() {
        if (ACCESS_TOKEN == null)return;
        //Select image to upload
//        File sd = Environment.getExternalStorageDirectory();
//        String csvFile = "zizi" + ".xls";
//
//        File directory = new File(sd.getAbsolutePath());
//        File file = Export.export(directory, "zizi", 1);
//        new UploadTask(DropboxClient.getClient(ACCESS_TOKEN), file, getApplicationContext()).execute();
    }






}
