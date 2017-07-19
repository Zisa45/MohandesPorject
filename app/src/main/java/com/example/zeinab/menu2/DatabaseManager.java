package com.example.zeinab.menu2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;


public class DatabaseManager extends SQLiteOpenHelper{

    private static final String databaseName = "sixth.db";
    private static final int version = 1;

    public DatabaseManager(Context context) {

        super(context, databaseName, null, version);
        Log.i("Zeinab", "DB Created!!");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String cQuery = "CREATE TABLE image (\n" +
                "    imageData BLOB\n" +
                ");\n";

        db.execSQL(cQuery);
        Log.i("Zeinab", "Table Created!!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + databaseName);

        // create new table
        onCreate(db);
    }

    public void addEntry(byte[] image) throws SQLiteException {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        //cv.put("imageName",    name);
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
        image = gcur.getBlob(0);
        bmp = BitmapFactory.decodeByteArray(image, 0, image.length);
        Log.i("Zeinab", "Retrived!!");
        return bmp;
    }

}
