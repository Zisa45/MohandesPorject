package com.example.zeinab.menu2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;


public class DatabaseManager extends SQLiteOpenHelper{

    private static final String databaseName = "m2.db";
    private static final int version = 1;

    private static final String instruction_table = "instruction";
    private static final String Instruction_id = "instructionId";
    private static final String Instruction_title = "title";
    private static final String Instruction_text = "text";
    private static final String Instruction_picId = "picId";
    private static final String Instruction_tableId = "tableId";

    private static final String standard_table = "standard";

    private static final String image_table = "image";

    private static final String table_table = "[table]";

    private static final String tag_table = "tag";

    private static final String tagInstruction_table = "[tag-instruction]";

    private static final String tagStandard_table = "[tag-standard]";


    public DatabaseManager(Context context) {

        super(context, databaseName, null, version);
        Log.i("Zeinab", "DB Created!!");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String cQuery = "CREATE TABLE instruction (\n" +
                "    instructionId CHAR (7)     PRIMARY KEY,\n" +
                "    text          TEXT,\n" +
                "    imageId       CHAR (7)     REFERENCES image (imageId),\n" +
                "    tableId       CHAR (7)     REFERENCES [table] (tableId),\n" +
                "    title         VARCHAR (30) \n" +
                ");";
        db.execSQL(cQuery);
        Log.i("Zeinab", "Table1 Created!!");

        cQuery = "CREATE TABLE image (\n" +
                "    imageId   CHAR (7) PRIMARY KEY,\n" +
                "    imageName STRING,\n" +
                "    imageData BLOB\n" +
                ");\n";
        db.execSQL(cQuery);
        Log.i("Zeinab", "Table2 Created!!");


        cQuery = "CREATE TABLE standard (\n" +
                "    standardId CHAR (7)     PRIMARY KEY,\n" +
                "    text       TEXT,\n" +
                "    imageId    CHAR (7)     REFERENCES image (imageId),\n" +
                "    tableId    CHAR (7)     REFERENCES [table] (tableId),\n" +
                "    title      VARCHAR (30) \n" +
                ");\n";
        db.execSQL(cQuery);

        cQuery = "CREATE TABLE [table] (\n" +
                "    tableId   CHAR (7) PRIMARY KEY,\n" +
                "    tableName STRING,\n" +
                "    tableData BLOB\n" +
                ");\n";
        db.execSQL(cQuery);

        cQuery = "CREATE TABLE tag (\n" +
                "    tagId CHAR (7) PRIMARY KEY,\n" +
                "    name  STRING\n" +
                ");\n";
        db.execSQL(cQuery);

        cQuery = "CREATE TABLE [tag-instruction] (\n" +
                "    tagId         CHAR (7) REFERENCES tag (tagId),\n" +
                "    instructionId CHAR (7) REFERENCES instruction (instructionId) \n" +
                ");\n";
        db.execSQL(cQuery);

        cQuery = "CREATE TABLE [tag-standard] (\n" +
                "    tagId      CHAR (7) REFERENCES tag (tagId),\n" +
                "    standardId CHAR (7) REFERENCES standard (standardId) \n" +
                ");\n";
        db.execSQL(cQuery);

        Log.i("Zeinab", "Tables Created!!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + instruction_table);
        db.execSQL("DROP TABLE IF EXISTS " + standard_table);
        db.execSQL("DROP TABLE IF EXISTS " + table_table);
        db.execSQL("DROP TABLE IF EXISTS " + image_table);
        db.execSQL("DROP TABLE IF EXISTS " + tag_table);
        db.execSQL("DROP TABLE IF EXISTS " + tagInstruction_table);
        db.execSQL("DROP TABLE IF EXISTS " + tagStandard_table);

        // create new table
        onCreate(db);
    }

    public void addEntry(byte[] image) throws SQLiteException {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("imageId", "1");
        cv.put("imageName",    "zeinab");
        cv.put("imageData", image);
        database.insert("image", null, cv );
        database.close();
        Log.i("Zeinab", "inserted!!");
    }

    public Bitmap RetriveEntry (){
        byte[] image;
        Bitmap bmp;
        SQLiteDatabase gdb = this.getReadableDatabase();
        String gQuery = "select * From image";
        Cursor gcur = gdb.rawQuery(gQuery, null);
        gcur.moveToFirst();
        image = gcur.getBlob(2);
        bmp = BitmapFactory.decodeByteArray(image, 0, image.length);
        Log.i("Zeinab", "Retrived!!");
        return bmp;
    }

}
