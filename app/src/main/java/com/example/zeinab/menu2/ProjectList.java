package com.example.zeinab.menu2;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.PropertyPermission;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import com.dropbox.core.DbxApiException;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.users.FullAccount;

public class ProjectList extends AppCompatActivity {

    ListView listView ;
    DatabaseManager dbm;
    private String ACCESS_TOKEN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_list);

        ACCESS_TOKEN = retrieveAccessToken();

        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/B Zar_YasDL.com.ttf");
        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.list);

        // Defined Array values to show in ListView
        String[] values = new String[] { "Android List View",
                "Adapter implementation",
                "Simple List View In Android",
                "Create List View Android",
                "Android Example",
                "List View Source Code",
                "List View Array Adapter",
                "Android Example List View"
        };

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data
        dbm = new DatabaseManager(this);
        Instruction i = new Instruction();
//        listView.setTypeface(tf);

        ArrayList test = dbm.getAllProjects();
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
                String  itemValue    = (String) listView.getItemAtPosition(position);

                int project_id =dbm.getProjectId(itemValue);
                Intent intent = new Intent(getBaseContext(), ShowProject.class);
                intent.putExtra("project_id" ,Integer.toString(project_id));
                startActivity(intent);


            }

        });


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                // ListView Clicked item index
                int itemPosition     = position;

                final PopupMenu menu = new PopupMenu(getApplicationContext(), view);
                menu.getMenuInflater().inflate(R.menu.popup_menu, menu.getMenu());
                menu.show();



                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {

                            case R.id.extract:


                                AlertDialog.Builder alert = new AlertDialog.Builder(ProjectList.this);
                                Dialog alert1 = new Dialog(ProjectList.this);

//                                Window window = alert.getWindow();
//                                WindowManager.LayoutParams wlp = window.getAttributes();
//
//                                wlp.gravity = Gravity.BOTTOM;
//                                wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
//                                window.setAttributes(wlp);

//                                alert.setTitle("نام فایل");
                                alert.setMessage("نام فایل خروجی را وارد کنید.");

                                final EditText input = new EditText(ProjectList.this);
                                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.MATCH_PARENT,
                                        LinearLayout.LayoutParams.MATCH_PARENT);
                                input.setLayoutParams(lp);
                                alert.setView(input);

                                alert.setPositiveButton("تایید",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,int id) {
                                                // get user input and set it to result
                                                // edit text
                                                String userinputResult = input.getText().toString();

//                                                SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd_HH:mm:ss");
//                                                Date now = new Date();
//                                                String fileName = formatter.format(now) + "__"+ userinputResult;

//                                                TextView txtatePicker.setText(fileName);

                                                // ListView Clicked item value
                                                String  itemValue    = (String) listView.getItemAtPosition(position);

                                                int project_id =dbm.getProjectId(itemValue);
                                                File temp = export(project_id, userinputResult);
//                                                Intent intent = new Intent(getBaseContext(), Export.class);
//                                                intent.putExtra("project_id" ,Integer.toString(project_id));
//                                                intent.putExtra("file_name", userinputResult);
//                                                startActivity(intent);
                                            }
                                        });


                                alert.setNegativeButton("انصراف",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,
                                                                int id) {
                                                dialog.cancel();
                                            }
                                        });



                                alert.show();


                                break;
                            case R.id.delete:
                                AlertDialog.Builder dAlert = new AlertDialog.Builder(ProjectList.this);

                                dAlert.setMessage("آیا از حذف این پروژه مطمئن هستید؟");

                                dAlert.setPositiveButton("تایید",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,int id) {
                                                String  itemValue    = (String) listView.getItemAtPosition(position);

                                                Integer project_id =dbm.getProjectId(itemValue);
                                                dbm.deleteProject(project_id.toString());


                                                //update List
                                                ArrayList test = dbm.getAllProjects();
                                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(ProjectList.this,
                                                        android.R.layout.simple_list_item_1, android.R.id.text1,test);

                                                // Assign adapter to ListView
                                                listView.setAdapter(adapter);

                                                Toast.makeText(getApplicationContext(), "پروژه با موفقیت حذف شد.", Toast.LENGTH_SHORT).show();

                                            }
                                        });


                                dAlert.setNegativeButton("انصراف",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,
                                                                int id) {
                                                dialog.cancel();
                                            }
                                        });

                                dAlert.show();
                                break;


                            case R.id.backup:

                                AlertDialog.Builder balert = new AlertDialog.Builder(ProjectList.this);
                                balert.setMessage("نام فایل خروجی را وارد کنید.");

                                final EditText binput = new EditText(ProjectList.this);
                                LinearLayout.LayoutParams blp = new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.MATCH_PARENT,
                                        LinearLayout.LayoutParams.MATCH_PARENT);
                                binput.setLayoutParams(blp);
                                balert.setView(binput);

                                balert.setPositiveButton("تایید",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,int id) {

                                                String userinputResult = binput.getText().toString();
                                                String  itemValue    = (String) listView.getItemAtPosition(position);
                                                int project_id =dbm.getProjectId(itemValue);

                                                backup(project_id, userinputResult);
                                            }
                                        });

                                balert.setNegativeButton("انصراف",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,
                                                                int id) {
                                                dialog.cancel();
                                            }
                                        });
                                balert.show();

                                break;


                            case R.id.addFavorite:
                                Toast.makeText(getApplicationContext(), "your desire action is " + item.toString(), Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }

                        return true;
                    }
                });

                return true;
            }
        });

    }

    private void backup(Integer projectId, String file_name) {
        if (ACCESS_TOKEN == null)return;

//        File sd = Environment.getExternalStorageDirectory();
//        String csvFile = "zizi" + ".xls";
//        File directory = new File(sd.getAbsolutePath());

        File file = export(projectId, file_name);
        new UploadTask(DropboxClient.getClient(ACCESS_TOKEN), file, getApplicationContext()).execute();
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




    File export(Integer projectId, String file_name){

        DatabaseManager dbm;
        Project prj;
        visit vst;
        dbm = new DatabaseManager(this);
        prj = new Project();
        String project_id = projectId.toString();

        prj = dbm.getProject(getApplicationContext(),project_id);

////////////
        File sd1 = Environment.getExternalStorageDirectory();
        String exstPath1 = sd1.getPath();
        String dir1 = exstPath1+"/"+"اندیشمند نظارت";

        String []s=new String[2]; //declare an array for storing the files i.e the path of your source files
        s[0]=exstPath1 + "/12.xls";    //Type the path of the files in here
        s[1]=exstPath1 + "/a.xls"; // path of the second file


//        Bitmap bitmap;
//        File file = new File("path");
//        OutputStream os = new BufferedOutputStream(new FileOutputStream(file));
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
//        os.close();


        zip(s,dir1+"/MyZipFolder.zip");    //call the zip function
///////////
        //old
        final Cursor prCursor = dbm.getProjectExport(project_id);
        final Cursor viCursor = dbm.getVisitExport(project_id);


        File sd = Environment.getExternalStorageDirectory();
        String exstPath = sd.getPath();
        File dir = new File(exstPath+"/"+"اندیشمند نظارت");
        String csvFile = file_name + ".xls";

        File directory = new File(exstPath+"/"+"اندیشمند نظارت");
        //create directory if not exist
        if (!directory.isDirectory()) {
            directory.mkdirs();
        }
        try {

            //file path
            File file = new File(directory, csvFile);
            WorkbookSettings wbSettings = new WorkbookSettings();
            wbSettings.setLocale(new Locale("en", "EN"));
            WritableWorkbook workbook;
            workbook = Workbook.createWorkbook(file, wbSettings);
            //Excel sheet name. 0 represents first sheet
            WritableSheet sheet = workbook.createSheet(prj.title, 0);
            // column and row
            sheet.addCell(new Label(0, 0, "عنوان بازدید"));
            sheet.addCell(new Label(1, 0, "شماره پرونده"));
            sheet.addCell(new Label(2, 0, "توضیحات"));
            sheet.addCell(new Label(3, 0, "تاریخ بازدید"));
            sheet.addCell(new Label(4, 0, "شماره قرارداد"));
            sheet.addCell(new Label(5, 0, "شماره وضعیت"));
            sheet.addCell(new Label(6, 0, "پیمانکار"));
            sheet.addCell(new Label(7, 0, "مالک"));
            sheet.addCell(new Label(8, 0, "طراح"));
            sheet.addCell(new Label(9, 0, "ناظر"));
            sheet.addCell(new Label(10, 0, "آدرس"));
            sheet.addCell(new Label(11, 0, "تاریخ شروع پروژه"));
            sheet.addCell(new Label(12, 0, "تاریخ خاتمه پروژه"));
            prCursor.moveToFirst();
            if (viCursor.moveToFirst()) {
                do {
                    String title = viCursor.getString(viCursor.getColumnIndex("title"));
                    String fileNum = prCursor.getString(prCursor.getColumnIndex("FileNum"));
                    String description = viCursor.getString(viCursor.getColumnIndex("text"));
                    String visitDate = viCursor.getString(viCursor.getColumnIndex("Date"));
                    String contractNum = prCursor.getString(prCursor.getColumnIndex("contractNum"));
                    String condition = prCursor.getString(prCursor.getColumnIndex("condition"));
                    String contractorName = prCursor.getString(prCursor.getColumnIndex("contractorName"));
                    String ownerName = prCursor.getString(prCursor.getColumnIndex("ownerName"));
                    String designerName = prCursor.getString(prCursor.getColumnIndex("designerName"));
                    String observerName = prCursor.getString(prCursor.getColumnIndex("observerName"));
                    String address = prCursor.getString(prCursor.getColumnIndex("address"));
                    String startDate = prCursor.getString(prCursor.getColumnIndex("startDate"));
                    String endDate = prCursor.getString(prCursor.getColumnIndex("endDate"));


                    int i = viCursor.getPosition() + 1;
                    sheet.addCell(new Label(0, i, title));
                    sheet.addCell(new Label(1, i, fileNum));
                    sheet.addCell(new Label(2, i, description));
                    sheet.addCell(new Label(3, i, visitDate));
                    sheet.addCell(new Label(4, i, contractNum));
                    sheet.addCell(new Label(5, i, condition));
                    sheet.addCell(new Label(6, i, contractorName));
                    sheet.addCell(new Label(7, i, ownerName));
                    sheet.addCell(new Label(8, i, designerName));
                    sheet.addCell(new Label(9, i, observerName));
                    sheet.addCell(new Label(10, i, address));
                    sheet.addCell(new Label(11, i, startDate));
                    sheet.addCell(new Label(12, i, endDate));
                } while (viCursor.moveToNext());
            }

            //closing cursor
            viCursor.close();
            prCursor.close();
            workbook.write();
            workbook.close();
            Toast.makeText(getApplication(),"گرفتن خروجی با موفقیت انجام شد!", Toast.LENGTH_SHORT).show();
            return file;
        } catch (WriteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }




    public void zip(String[] files, String zipFile)
    {

        try {
            syncDropbox();
        } catch (DbxException e) {
            e.printStackTrace();
        }

        Integer BUFFER=1024;

        String[] _files= files;
        String _zipFile= zipFile;

        try  {
            BufferedInputStream origin = null;
            FileOutputStream dest = new FileOutputStream(_zipFile);

            ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));

            byte data[] = new byte[BUFFER];

            for(int i=0; i < _files.length; i++) {
                Log.d("add:",_files[i]);
                Log.v("Compress", "Adding: " + _files[i]);
                FileInputStream fi = new FileInputStream(_files[i]);
                origin = new BufferedInputStream(fi, BUFFER);
                ZipEntry entry = new ZipEntry(_files[i].substring(_files[i].lastIndexOf("/") + 1));
                out.putNextEntry(entry);
                int count;
                while ((count = origin.read(data, 0, BUFFER)) != -1) {
                    out.write(data, 0, count);
                }
                origin.close();
            }

            out.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    void syncDropbox() throws DbxException {

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    while(true) {
                        sleep(1000);
                        String ACCESS_TOKEN = "n8hh8hu7bvq5w1f";

                        DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
                        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

                        FullAccount account = client.users().getCurrentAccount();
                        System.out.println(account.getName().getDisplayName());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (DbxApiException e) {
                    e.printStackTrace();
                } catch (DbxException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();


    }
}
