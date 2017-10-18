package com.example.zeinab.menu2;

import android.database.Cursor;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class Export extends AppCompatActivity {

    DatabaseManager dbm = new DatabaseManager(this);
    Project prj;
    visit vst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_export);


        String project_id, file_name;
        project_id = getIntent().getStringExtra("project_id");
        file_name = getIntent().getStringExtra("file_name");
        dbm = new DatabaseManager(this);
        prj = new Project();
        prj = dbm.getProject(getApplicationContext(),project_id);




        //old



        File sd = Environment.getExternalStorageDirectory();
        String csvFile = file_name + ".xls";

        File directory = new File(sd.getAbsolutePath());
        //create directory if not exist
        if (!directory.isDirectory()) {
            directory.mkdirs();
        }

        export(directory, csvFile, project_id);
    }


    void export(File directory, String csvFile, String project_id){
        try {

            final Cursor cursor = dbm.getProjectExport(project_id);
            //file path
            File file = new File(directory, csvFile);
            WorkbookSettings wbSettings = new WorkbookSettings();
            wbSettings.setLocale(new Locale("en", "EN"));
            WritableWorkbook workbook;
            workbook = Workbook.createWorkbook(file, wbSettings);
            //Excel sheet name. 0 represents first sheet
            WritableSheet sheet = workbook.createSheet(prj.title, 0);
            // column and row
            sheet.addCell(new Label(0, 0, "عنوان پروژه"));
            sheet.addCell(new Label(1, 0, "شماره پرونده"));
            sheet.addCell(new Label(2, 0, "شماره قرارداد"));
            sheet.addCell(new Label(3, 0, "شماره وضعیت"));
            sheet.addCell(new Label(4, 0, "پیمانکار"));
            sheet.addCell(new Label(5, 0, "مالک"));
            sheet.addCell(new Label(6, 0, "طراح"));
            sheet.addCell(new Label(7, 0, "ناظر"));
            sheet.addCell(new Label(8, 0, "آدرس"));
            sheet.addCell(new Label(9, 0, "تاریخ شروع"));
            sheet.addCell(new Label(10, 0, "تاریخ خاتمه"));

            if (cursor.moveToFirst()) {
                do {
                    String title = cursor.getString(cursor.getColumnIndex("title"));
                    String fileNum = cursor.getString(cursor.getColumnIndex("FileNum"));
                    String contractNum = cursor.getString(cursor.getColumnIndex("contractNum"));
                    String condition = cursor.getString(cursor.getColumnIndex("condition"));
                    String contractorName = cursor.getString(cursor.getColumnIndex("contractorName"));
                    String ownerName = cursor.getString(cursor.getColumnIndex("ownerName"));
                    String designerName = cursor.getString(cursor.getColumnIndex("designerName"));
                    String observerName = cursor.getString(cursor.getColumnIndex("observerName"));
                    String address = cursor.getString(cursor.getColumnIndex("address"));
                    String startDate = cursor.getString(cursor.getColumnIndex("startDate"));
                    String endDate = cursor.getString(cursor.getColumnIndex("endDate"));


                    int i = cursor.getPosition() + 1;
                    sheet.addCell(new Label(0, i, title));
                    sheet.addCell(new Label(1, i, fileNum));
                    sheet.addCell(new Label(2, i, contractNum));
                    sheet.addCell(new Label(3, i, condition));
                    sheet.addCell(new Label(4, i, contractorName));
                    sheet.addCell(new Label(5, i, ownerName));
                    sheet.addCell(new Label(6, i, designerName));
                    sheet.addCell(new Label(7, i, observerName));
                    sheet.addCell(new Label(8, i, address));
                    sheet.addCell(new Label(9, i, startDate));
                    sheet.addCell(new Label(10, i, endDate));
                } while (cursor.moveToNext());
            }

            //closing cursor
            cursor.close();
            workbook.write();
            workbook.close();
            Toast.makeText(getApplication(),"گرفتن خروجی با موفقیت انجام شد!", Toast.LENGTH_SHORT).show();


        } catch (WriteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}