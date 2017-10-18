package com.example.zeinab.menu2;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DatabaseManager extends SQLiteOpenHelper{

    private static final String databaseName = "proDB25.db";
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
                "    instructionId INTEGER      PRIMARY KEY AUTOINCREMENT\n" +
                "                               NOT NULL,\n" +
                "    text          TEXT,\n" +
                "    title         VARCHAR (30) UNIQUE\n" +
                ");\n";
        db.execSQL(cQuery);


        cQuery = "CREATE TABLE image (\n" +
                "    imageId   INTEGER PRIMARY KEY\n" +
                "                      NOT NULL,\n" +
                "    imageName STRING  UNIQUE\n" +
                ");\n";
        db.execSQL(cQuery);


        cQuery =  "CREATE TABLE standard (\n" +
                "    standardId INTEGER      PRIMARY KEY AUTOINCREMENT\n" +
                "                               NOT NULL,\n" +
                "    text          TEXT,\n" +
                "    title         VARCHAR (30) UNIQUE\n" +
                ");\n";
        db.execSQL(cQuery);;


        cQuery = "CREATE TABLE [table] (\n" +
                "    tableId   INTEGER PRIMARY KEY\n" +
                "                      UNIQUE,\n" +
                "    tableName STRING  UNIQUE\n" +
                ");\n";
        db.execSQL(cQuery);

        cQuery = "CREATE TABLE tag (\n" +
                "    tagId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                "    name  STRING\n" +
                ");\n";
        db.execSQL(cQuery);


        cQuery = "CREATE TABLE [tag-instruction] (\n" +
                "    tagId         INTEGER REFERENCES tag (tagId),\n" +
                "    instructionId INTEGER REFERENCES instruction (instructionId) \n" +
                ");\n";
        db.execSQL(cQuery);


        cQuery = "CREATE TABLE [tag-standard] (\n" +
                "    tagId      INTEGER REFERENCES tag (tagId),\n" +
                "    standardId INTEGER REFERENCES standard (standardId) \n" +
                ");\n";
        db.execSQL(cQuery);

        cQuery = "CREATE TABLE [image-instruction] (\n" +
                "    imageId       INTEGER REFERENCES image (imageId),\n" +
                "    instructionId INTEGER REFERENCES instruction (instructionId) \n" +
                ");\n";
        db.execSQL(cQuery);

        cQuery = "CREATE TABLE [table-instruction] (\n" +
                "    instructionId INTEGER REFERENCES instruction (instructionId),\n" +
                "    tableId       INTEGER REFERENCES [table] (tableId) \n" +
                ");\n";
        db.execSQL(cQuery);

        //standard's images and tables

        cQuery = "CREATE TABLE [image-standard] (\n" +
                "    imageId       INTEGER REFERENCES image (imageId),\n" +
                "    standardId INTEGER REFERENCES standard (standardId) \n" +
                ");\n";
        db.execSQL(cQuery);

        cQuery = "CREATE TABLE [table-standard] (\n" +
                "    standardId INTEGER REFERENCES standard (instructionId),\n" +
                "    tableId       INTEGER REFERENCES [table] (tableId) \n" +
                ");\n";
        db.execSQL(cQuery);


        //phase2
        cQuery = "CREATE TABLE contractor (\n" +
                "    contractorId INTEGER      PRIMARY KEY AUTOINCREMENT" +
                "                              NOT NULL,\n" +
                "    name         VARCHAR (60) NOT NULL,\n" +
                "    phoneNumber  NUMERIC      NOT NULL,\n" +
                "    tel          NUMERIC,\n" +
                "    fax          NUMERIC,\n" +
                "    address      TEXT\n" +
                ");";
        db.execSQL(cQuery);


        cQuery = "CREATE TABLE owner (\n" +
                "    ownerId INTEGER           PRIMARY KEY AUTOINCREMENT" +
                "                              NOT NULL,\n" +
                "    name         VARCHAR (60) NOT NULL,\n" +
                "    phoneNumber  NUMERIC      NOT NULL,\n" +
                "    tel          NUMERIC,\n" +
                "    fax          NUMERIC,\n" +
                "    address      TEXT\n" +
                ");";
        db.execSQL(cQuery);


        cQuery = "CREATE TABLE designer (\n" +
                "    designerId INTEGER        PRIMARY KEY AUTOINCREMENT" +
                "                              NOT NULL,\n" +
                "    name         VARCHAR (60) NOT NULL,\n" +
                "    phoneNumber  NUMERIC      NOT NULL,\n" +
                "    tel          NUMERIC,\n" +
                "    fax          NUMERIC,\n" +
                "    address      TEXT\n" +
                ");";
        db.execSQL(cQuery);

        cQuery = "CREATE TABLE observer (\n" +
                "    observerId INTEGER        PRIMARY KEY AUTOINCREMENT" +
                "                              NOT NULL,\n" +
                "    name         VARCHAR (60) NOT NULL,\n" +
                "    phoneNumber  NUMERIC      NOT NULL,\n" +
                "    tel          NUMERIC,\n" +
                "    fax          NUMERIC,\n" +
                "    address      TEXT\n" +
                ");";
        db.execSQL(cQuery);

        cQuery = "CREATE TABLE project (\n" +
                "    projectId      INTEGER      PRIMARY KEY AUTOINCREMENT" +
                "                                NOT NULL,\n" +
                "    title          NUMERIC,\n" +
                "    FileNum        NUMERIC,\n" +
                "    contractNum    NUMERIC,\n" +
                "    contractorName VARCHAR (60),\n" +
                "    ownerName      VARCHAR (60),\n" +
                "    designerName   VARCHAR (60),\n" +
                "    observerName   VARCHAR (60),\n" +
                "    address        TEXT,\n" +
                "    startDate      DATE,\n" +
                "    endDate        DATE,\n" +
                "    condition      VARCHAR (20) \n" +
                ");";
        db.execSQL(cQuery);

        //for instructions
        cQuery = "CREATE TABLE iAssortment (\n" +
                "    assortId INTEGER      PRIMARY KEY AUTOINCREMENT\n" +
                "                               NOT NULL,\n" +
                "    title         VARCHAR (30) UNIQUE\n" +
                ");\n";
        db.execSQL(cQuery);

        cQuery = "CREATE TABLE sAssortment (\n" +
                "    assortId INTEGER      PRIMARY KEY AUTOINCREMENT\n" +
                "                               NOT NULL,\n" +
                "    title         VARCHAR (30) UNIQUE\n" +
                ");\n";
        db.execSQL(cQuery);


        cQuery = "CREATE TABLE [iAssortment-instruction] (\n" +
                "    assortId       INTEGER REFERENCES iAssortment (assortId),\n" +
                "    instructionId INTEGER REFERENCES instruction(instructionId) \n" +
                ");\n";
        db.execSQL(cQuery);

        cQuery = "CREATE TABLE [sAssortment-standard] (\n" +
                "    assortId       INTEGER REFERENCES sAssortment (assortId),\n" +
                "    standardId INTEGER REFERENCES standard(standardId) \n" +
                ");\n";
        db.execSQL(cQuery);


        cQuery = "CREATE TABLE vImage (\n" +
                "\t`imageId`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`image`\tBLOB\n" +
                ");";
        db.execSQL(cQuery);

        cQuery = "CREATE TABLE [createVisit-vImage] (\n" +
                "    visitId       INTEGER REFERENCES createVisit (visitId),\n" +
                "    imageId INTEGER REFERENCES vImage(ImageId) \n" +
                ");\n";
        db.execSQL(cQuery);

        cQuery = "CREATE TABLE createvisit (\n" +
                "\t`visitId`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`projectId`\tINTEGER,\n" +
                "\t`title`\tTEXT,\n" +
                "\t`text`\tTEXT,\n" +
                "\t`FileNum`\tNUMERIC,\n" +
                "\t`location`\tTEXT,\n" +
                "\t`Date`\tTEXT\n" +
                ");";
        db.execSQL(cQuery);


        cQuery = "CREATE TABLE [v_Pimage] (\n" +
                "\t`imageId`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "    visitId       INTEGER REFERENCES createVisit (visitId),\n" +
                "\t`image`\tBLOB\n" +
                ");";
        db.execSQL(cQuery);


        addAll(db);

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

//    public void addAllImage() throws SQLiteException{
//        SQLiteDatabase idb = this.getWritableDatabase();
//
//        idb.close();
//    }

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
        gdb.close();
        return bmp;
    }

    public void addAll(SQLiteDatabase db)throws SQLiteException{

        String cQuery = "INSERT INTO `image` (imageId,imageName) VALUES (1,'p1'),\n" +
                " (2,'p2'),\n" +
                " (3,'p3'),\n" +
                " (4,'p4'),\n" +
                " (5,'p5'),\n" +
                " (6,'p6'),\n" +
                " (7,'p7'),\n" +
                " (8,'p8')";
        db.execSQL(cQuery);


        cQuery = "INSERT INTO `table` (tableId,tableName) VALUES (1,'t1'),\n" +
                " (2,'t2'),\n" +
                " (3,'t3'),\n" +
                " (4,'t4'),\n" +
                " (5,'t5'),\n" +
                " (6,'t6')";
        db.execSQL(cQuery);


        cQuery = "INSERT INTO `instruction` (instructionId,text,title) VALUES (1,'چكيده در این مقاله، به بررسی علل آلودگی مقره\u200C های خطوط برق در نقاط با آلودگی شدید (مناطق جنوب کشور، صنایع نفت و پتروشیمی) و ارائه پیشنهاد جهت شستشوی مقره \u200Cها در حالت خط گرم پرداخته شده است. عدم خاموشی برق، صنایع حیاتی کشور \u200C(مانند خطوط برق صنایع نفت و پتروشیمی شهر عسلویه و مناطق جنوبی کشور)، مستلزم تعمیرات و شستشوی منظم و دوره \u200Cای مقره\u200C های خطوط برق به دلیل آلودگی \u200Cها شدید منطقه می \u200Cباشد، به همین جهت، انتخاب روشی مناسب و هوشمند جهت تعمیرات و بازرسی خطوط برق این صنایع ضروری است. در این پژوهش، روش جدیدی (رباتیک پروازی) جهت اجرای همزمان شستشوی مقره \u200Cها و نظارت تجهیزات خطوط برق در جهت کاهش هزینه \u200Cهای تعمیرات و بالا بردن قابلیت اطمینان برق صنایع حیاتی کشور پیشنهاد شده است. در این طرح، از یک هلیکوپتر بدون سرنشین\u200C همراه با یک خودرو کوچک، شامل پمپ، مخزن سیال و مواردی دیگر در کنار هم استفاده شده است که تمامی مراحل شستشوی مقره را به صورت خودکار انجام می\u200C دهد و ضمن اجرای عملیات شستشوی مقره \u200Cها، یراق آلات خطوط برق را نظارت و از لحاظ عیوب بررسی می\u200Cکند.','شستشوی مقره \u200Cهای خطوط برق'),\n" +
                " (2,'مقره های کامپوزیتی بر خلاف مواد سرامیکی یا شیشه ای، خرد، لب پر، شکافته یا شکسته نمی شوند؛ ولی حمل و انباري داري نامناسب این مقره ها و آسیب بدنه آنها می تواند شکست الکتریکی یا مکانیکی را تسریع  نماید. ممکن است صدمات مذکور با چشم قابل مشاهده نباشد و شکست الکتریکی به مرور زمان و پس از چند سال بهره برداري باعث وقوع ناخواسته قطعی یا شکافته شدن مقره گردد.','نگهداری و نصب مقره های کامپوزیت'),\n" +
                " (3,'مقره ها یکی از اجزا مهم خطوط انتقال برق فشار قوی هستند و وظیفه اصلی آنها عایق بندی و تحمل وزن هادی است. مقره\u200Cها باید از نظر الکتریکی به حدی مقاومت بالا داشته باشند که بین سه فاز خطوط برق انتقالی و دکل ها که به زمین وصل هستند اتصالی برقرار نشود. این مقاومت الکتریکی باید در حدی باشد که در بدترین شرایط جوی نظیر گرد و غبار ، رطوبت بالا ، باران و صاعقه دچار آسیب نشوند و اتصال الکتریکی به وجود نیاورد. همچنین این تجهیزات نیاز به تعمیر و نگهداری دوره ای دارند به گونه ای که در بازه های زمانی مشخص و در صورت بروز آلودگی ، با استفاده از سیلیس ، واترجت هات لاین و سایر تجهیزات ویژه از آنها برای حفظ بیشترین کارایی نگهداری به عمل می\u200Cآید.','شستن مقره های برق فشارقوی');";
        db.execSQL(cQuery);


        cQuery = "INSERT INTO `table-instruction` (instructionId,tableId) VALUES (1,1),\n" +
                " (2,2),\n" +
                " (3,3),\n" +
                " (1,4),\n" +
                " (2,5),\n" +
                " (1,6)";
        db.execSQL(cQuery);


        cQuery = "INSERT INTO `image-instruction` (imageId,instructionId) VALUES (2,2),\n" +
                " (1,1),\n" +
                " (3,3),\n" +
                " (5,1),\n" +
                " (7,1),\n" +
                " (4,3),\n" +
                " (6,2),\n" +
                " (8,2)";
        db.execSQL(cQuery);
        Log.i("Zeinab", "addAll!");


        //inserting into standards

        cQuery = "INSERT INTO `standard` VALUES (1,'مقره های سیلیکون رابرازجمله ابزارها و تجهیزاتی هستند که کاربردهای مناسبی را در شبکه توزیع کشور دارند. در مقاله علمی زیر که به وسیله رضا امامی تهیه شده و ویژگیهای مقره های سیلیکون رابر وامتیازات آن مطرح شده است.\n" +
                "\tتا چندی قبل مقره های کامپوزیت به خاطر نشکن بودن جایگزین مقره های نسل قبل از خود شد، اما رفته رفته در حین بهره برداری خواص مختلفی ازخود نشان داد که باعث شد بازارتقاضا مقره های سیلیکون رابرافزایش چشمگیری پیدا کند.\n" +
                "\tسیلیکون به خاطر خاصیت منحصر به فردHydrophobic خود قابلیتهای بهتری را در شرایط مختلفی از خود نشان می دهد. پوشش سیلیکون درمقایسه با انواع دیگر مقره های کامپوزیتی مورد استفاده بیشتری قرار گرفته است.\n" +
                "\tخاصیت Hydrophobic از تشکیل یک آب برروی سطح سیلیکون جلوگیری می کند وآب بر روی آن به صورت قطره قطره باقی می ماند. به همین دلیل مقاومت سطحی آن کاهش پیدا نمی کند و احتمال ایجاد آرک در این نوع مقره ها به حداقل می رسد.\n" +
                "\tپیوند قوی مولکولی سیلیکون باعث می شود که اگرلایه ای از آلودگی یا غبار بر روی سطح آن بنشیند مولکولهای سیلیکون به سمت بالا حرکت کرده ولایه زاید را دربرگیرند به خاطر همین طرح خارجی پوشش همواره سیلیکونی است به این عمل خاصیت باز یافت (Recovery) می گویند.\n" +
                "\tبا توجه به نکات بالا بهترین انتخاب برای مناطق با آلودگیهای مختلف و زیاد و و یا غبارآلود استفاده از پوششهای سیلیکونی است.\n" +
                "\tاستفاده از مقره های سیلیکونی باعث کم شدن هزینه شست و شو و نگهداری می شود.\n" +
                "\tبرتری دیگر مقره های سیلیکونی نسبت به سایر مقره های کامپوزیت مقاومت بسیار خوب در برابر اشعه ماوراء بنفش خورشید است که باعث شده عمر مفید پوششهای سیلیکونی در مقایسه با سایر پوششها طولانی تر باشد.\n" +
                "\tقابل انعطاف بودن مقره های سیلیکونی از شکستگی و پارگی آنها و آسیب پذیر بودن در برابر ضربات مکانیکی جلوگیری می کند.\n" +
                "\tیکی دیگر از ویژگیهای این نوع مقره ها وزن بسیار کم آنها در مقایسه با سایر مقره ها است که این مساله باعث می شود که مقدار و وزن دکلها به همین نسبت کم شود که درکل باعث صرفه جویی درهزینه ها می شود.\n" +
                "\tوزن کم مقره های سیلیکونی باعث کم شدن هزینه حمل ونقل وآسان شدن آن می شود. مقره های سیلیکون رابر تولیدی از نوع یکپارجه و بدون درز بوده که این تکنیک در حال حاضر پیشرفته ترین روش ساخت مقره ها در دنیا است.\n" +
                "\tتولید کنندگان با بکار گیری متخصصان مختلف و استفاده از ابزارهای مورد نیاز وآزمایشهای لازم طی چندین سال به دانش فنی ساخت این نوع مقره ها دست یافته اند.','مقره های سیلیکون رابر ')";
        db.execSQL(cQuery);

        cQuery ="INSERT INTO `standard` VALUES (2,'مقره های کامپوزیتی بر خلاف مواد سرامیکی یا شیشه ای، خرد، لب پر، شکافته یا شکسته نمی شوند؛ ولی حمل و انباري داري نامناسب این مقره ها و آسیب بدنه آنها می تواند شکست الکتریکی یا مکانیکی را تسریع  نماید. ممکن است صدمات مذکور با چشم قابل مشاهده نباشد و شکست الکتریکی به مرور زمان و پس از چند سال بهره برداري باعث وقوع ناخواسته قطعی یا شکافته شدن مقره گردد.','نگهداری و نصب مقره های کامپوزیت')";
        db.execSQL(cQuery);

        cQuery ="INSERT INTO `standard` VALUES (3,'مقره ها یکی از اجزا مهم خطوط انتقال برق فشار قوی هستند و وظیفه اصلی آنها عایق بندی و تحمل وزن هادی است. مقره\u200Cها باید از نظر الکتریکی به حدی مقاومت بالا داشته باشند که بین سه فاز خطوط برق انتقالی و دکل ها که به زمین وصل هستند اتصالی برقرار نشود. این مقاومت الکتریکی باید در حدی باشد که در بدترین شرایط جوی نظیر گرد و غبار ، رطوبت بالا ، باران و صاعقه دچار آسیب نشوند و اتصال الکتریکی به وجود نیاورد. همچنین این تجهیزات نیاز به تعمیر و نگهداری دوره ای دارند به گونه ای که در بازه های زمانی مشخص و در صورت بروز آلودگی ، با استفاده از سیلیس ، واترجت هات لاین و سایر تجهیزات ویژه از آنها برای حفظ بیشترین کارایی نگهداری به عمل می\u200Cآید.','شستن مقره های برق فشارقوی')";
        db.execSQL(cQuery);

                cQuery = "INSERT INTO `table-standard` (standardId,tableId) VALUES (1,1),\n" +
                " (2,2),\n" +
                " (3,3),\n" +
                " (1,4),\n" +
                " (2,5),\n" +
                " (1,6)";
        db.execSQL(cQuery);


        cQuery = "INSERT INTO `image-standard` (imageId,standardId) VALUES (2,2),\n" +
                " (1,1),\n" +
                " (3,3),\n" +
                " (5,1),\n" +
                " (7,1),\n" +
                " (4,3),\n" +
                " (6,2),\n" +
                " (8,2)";
        db.execSQL(cQuery);




        //phase2
        cQuery = "INSERT INTO `owner` (ownerId,name,phoneNumber,tel,fax,address) VALUES (1,'آرش سرحدی',64133,215486,326431,'دانشگاه صنعتی اصفهان'),\n" +
                " (2,'زهرا آقایی',864531,541331,53133,'')";
        db.execSQL(cQuery);

        cQuery = "INSERT INTO `observer` (observerId,name,phoneNumber,tel,fax,address) VALUES (1,'سارا رجایی',84513131,51325451,5123321,'خیابان شیخ بهایی'),\n" +
                " (2,'زینب منتظری',864531,51321354,513313,NULL)";
        db.execSQL(cQuery);

        cQuery = "INSERT INTO `designer` (designerId,name,phoneNumber,tel,fax,address) VALUES (1,'امیرحسین سروری',6531318,264131543,6453131,'نهران - نبرد'),\n" +
                " (2,'محسن رهنمایی',6513344,3165431,3215413,NULL)";
        db.execSQL(cQuery);

        cQuery = "INSERT INTO `contractor` (contractorId,name,phoneNumber,tel,fax,address) VALUES (1,'پریسا حاتم نیا',2164643,268464133,3168431,'اصفهان - بزرگمهر'),\n" +
                " (2,'نیلوفر غفوری',168463,213684653,3168433,'سپاهان شهر')";
        db.execSQL(cQuery);



        //assortments

        cQuery = "INSERT INTO `iAssortment` VALUES (1,'احداث شبکه زمینی'),\n" +
                " (2,'احداث شبکه هوایی فشارمتوسط')";


        db.execSQL(cQuery);

        cQuery = "INSERT INTO [iAssortment-instruction] (assortId,instructionId) VALUES (1,1),\n" +
                "(1,2),\n" +
                "(2,3)" ;

        db.execSQL(cQuery);

        cQuery = "INSERT INTO `sAssortment` VALUES (1,'دسته ی اول'),\n" +
                " (2,'دسته ی دوم')";


        db.execSQL(cQuery);

        cQuery = "INSERT INTO [sAssortment-standard] (assortId,standardId) VALUES (1,1),\n" +
                "(1,2),\n" +
                "(2,3)" ;

        db.execSQL(cQuery);

    }


//    public void insertInstruction(Instruction iIns)throws SQLiteException{
//
//        Log.i("Zeinab", "Enter!");
//        SQLiteDatabase idb = this.getWritableDatabase();
//        ContentValues instructionCv = new ContentValues();
//
//        //image
//        ContentValues imageCv = new ContentValues();
//        imageCv.put("imageName", iIns.imageName);
//        idb.insert("image", null, imageCv);
//        //image
//
//        //table
//        ContentValues tableCv = new ContentValues();
//        tableCv.put("tableName", iIns.tableName);
//        idb.insert("[table]", null, tableCv);
//        //table
//
//        instructionCv.put("text", iIns.txt);
//        instructionCv.put("title", iIns.title);
//
////        instructionCv.put("imageName", iIns.imageName);
////        instructionCv.put("tableName", iIns.tableName);
//
//        idb.insert("instruction", null, instructionCv);
//        idb.close();
//        Log.i("Zeinab", "insertInstruction!");
//    }

    public Instruction getInstruction (Context context, String insId){
        byte[] image;
        byte[] table;
        Integer imgId, tblId;
        Instruction gIns = new Instruction();
        SQLiteDatabase gdb = this.getReadableDatabase();

        String gQuery = "select * From instruction where instructionId=\"" +insId+"\"";
        Cursor insCur = gdb.rawQuery(gQuery, null);


        if (insCur.moveToFirst()){
            Log.i("Zeinab", "if");
            gIns.txt = insCur.getString(1);
            gIns.title = insCur.getString(2);

            String imageIdQuery = "select imageId from [image-instruction] where instructionId = " + insCur.getInt(0);
            Cursor isCur = gdb.rawQuery(imageIdQuery, null);
            isCur.moveToFirst();
            imgId = isCur.getInt(0);
            int i = 0;
            gIns.imageName= new ArrayList<String>();
            while(isCur.moveToNext()){
                Log.i("sara","while");
                imgId = isCur.getInt(0);
                String imageNameQuery = "select imageName from image where imageId = " + imgId;
                Cursor isCur2 = gdb.rawQuery(imageNameQuery, null);
                isCur2.moveToFirst();

                gIns.imageName.add(i ,isCur2.getString(0));

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                options.inJustDecodeBounds = false;
                options.inSampleSize = 8;

//                Bitmap  b = BitmapFactory.decodeResource(mContext.getResources(),mContext.getResources().
//                        getIdentifier(list.get(position), "drawable", mContext.getPackageName()), options);
                gIns.imageData = BitmapFactory.decodeResource(context.getResources(),context.getResources().
                        getIdentifier(gIns.imageName.get(i), "drawable", context.getPackageName()) , options);

                i++;
            }





            //table
            String tableIdQuery = "select tableId from [table-instruction] where instructionId = " + insCur.getInt(0);
            isCur = gdb.rawQuery(tableIdQuery, null);
            isCur.moveToFirst();
            tblId = isCur.getInt(0);
             i =0;
            gIns.tableName= new ArrayList<String>();
            while(isCur.moveToNext()) {
                tblId = isCur.getInt(0);
                String tableNameQuery = "select tableName from [table] where tableId = " + tblId;
                Cursor  isCur2 = gdb.rawQuery(tableNameQuery, null);
                isCur2.moveToFirst();
                gIns.tableName.add(i , isCur2.getString(0));
                gIns.tableData = BitmapFactory.decodeResource(context.getResources(), context.getResources().
                        getIdentifier(gIns.tableName.get(i), "drawable", context.getPackageName()));
                i++;
            }//while
        }
        else{
            Log.i("Zeinab", "else");
            gIns.txt = insCur.getString(1);
            gIns.title = insCur.getString(2);
        }

        //byte[] to Bitmap
//        gIns.imageData = BitmapFactory.decodeByteArray(image, 0, image.length);
//        gIns.tableData = BitmapFactory.decodeByteArray(table, 0, table.length);
        gdb.close();
        Log.i("Zeinab", "getInstruction");
        return gIns;
    }

    public ArrayList getAllInstuctions (){

        ArrayList list = new ArrayList<String>();
        SQLiteDatabase gdb = this.getReadableDatabase();
        String gQuery = "select title From instruction";
        Cursor insCur = gdb.rawQuery(gQuery, null);
        if (insCur.moveToFirst()) {
            while (!insCur.isAfterLast()) {
                String name = insCur.getString(insCur.getColumnIndex("title"));

                list.add(name);
                insCur.moveToNext();
            }
        }

        return list;

    }


    public ArrayList getAllProjects (){

        ArrayList list = new ArrayList<String>();
        SQLiteDatabase gdb = this.getReadableDatabase();
        String gQuery = "select title From project";
        Cursor insCur = gdb.rawQuery(gQuery, null);
        if (insCur.moveToFirst()) {
            while (!insCur.isAfterLast()) {
                String name = insCur.getString(insCur.getColumnIndex("title"));

                list.add(name);
                insCur.moveToNext();
            }
        }

        return list;

    }



    public ArrayList getAllContractors (){

        ArrayList list = new ArrayList<String>();
        String name;
        SQLiteDatabase gdb = this.getReadableDatabase();
        String gQuery = "select name From contractor";
        Cursor insCur = gdb.rawQuery(gQuery, null);
        if (insCur.moveToFirst()) {
            while (!insCur.isAfterLast()) {
                name = insCur.getString(0);
//                contractor.contractorLastName = insCur.getString(2);

                list.add(name);
                insCur.moveToNext();
            }
        }

        return list;

    }

    public ArrayList getAllDesigners (){

        ArrayList list = new ArrayList<String>();
        String name;
        SQLiteDatabase gdb = this.getReadableDatabase();
        String gQuery = "select name From designer";
        Cursor insCur = gdb.rawQuery(gQuery, null);
        if (insCur.moveToFirst()) {
            while (!insCur.isAfterLast()) {
                name = insCur.getString(0);
//                contractor.contractorLastName = insCur.getString(2);

                list.add(name);
                insCur.moveToNext();
            }
        }

        return list;

    }


    public ArrayList getAllOwners (){

        ArrayList list = new ArrayList<String>();
        String name;
        SQLiteDatabase gdb = this.getReadableDatabase();
        String gQuery = "select name From owner";
        Cursor insCur = gdb.rawQuery(gQuery, null);
        if (insCur.moveToFirst()) {
            while (!insCur.isAfterLast()) {
                name = insCur.getString(0);
//                contractor.contractorLastName = insCur.getString(2);

                list.add(name);
                insCur.moveToNext();
            }
        }

        return list;

    }


    public ArrayList getAllObservers (){

        ArrayList list = new ArrayList<String>();
        String name;
        SQLiteDatabase gdb = this.getReadableDatabase();
        String gQuery = "select name From observer";
        Cursor insCur = gdb.rawQuery(gQuery, null);
        if (insCur.moveToFirst()) {
            while (!insCur.isAfterLast()) {
                name = insCur.getString(0);
//                contractor.contractorLastName = insCur.getString(2);

                list.add(name);
                insCur.moveToNext();
            }
        }

        return list;

    }


    public ArrayList searchOnInstruction(String in){
        ArrayList list = new ArrayList<String>();
        SQLiteDatabase gdb = this.getReadableDatabase();
        String gQuery = "select title from instruction where text like \"%" + in + "%\"";
        Cursor insCur = gdb.rawQuery(gQuery, null);
        if (insCur.moveToFirst()) {
            while (!insCur.isAfterLast()) {
                String name = insCur.getString(0);

                list.add(name);
                insCur.moveToNext();
            }
        }

        return list;
    }

    public ArrayList searchOnStandard(String in){
        ArrayList list = new ArrayList<String>();
        SQLiteDatabase gdb = this.getReadableDatabase();
        String gQuery = "select title from standard where text like \"%" + in + "%\"";
        Cursor insCur = gdb.rawQuery(gQuery, null);
        if (insCur.moveToFirst()) {
            while (!insCur.isAfterLast()) {
                String name = insCur.getString(0);

                list.add(name);
                insCur.moveToNext();
            }
        }

        return list;
    }

    public ArrayList searchOnProject(String in){
        ArrayList list = new ArrayList<String>();
        SQLiteDatabase gdb = this.getReadableDatabase();
        String gQuery = "select title from project where title like \"%" + in + "%\"";
        Cursor insCur = gdb.rawQuery(gQuery, null);
        if (insCur.moveToFirst()) {
            while (!insCur.isAfterLast()) {
                String name = insCur.getString(0);

                list.add(name);
                insCur.moveToNext();
            }
        }

        return list;
    }

    public int getInstructionId(String title){

        SQLiteDatabase gdb = this.getReadableDatabase();
        String gQuery = "select instructionId From instruction where title =\"" +title+"\"";
        Cursor insCur = gdb.rawQuery(gQuery, null);
        if (insCur.moveToFirst())
            return insCur.getInt(insCur.getColumnIndex("instructionId"));
        else
            return -1;
    }


    public int getProjectId(String title){

        SQLiteDatabase gdb = this.getReadableDatabase();
        String gQuery = "select projectId From project where title =\"" +title+"\"";
        Cursor insCur = gdb.rawQuery(gQuery, null);
        if (insCur.moveToFirst())
            return insCur.getInt(insCur.getColumnIndex("projectId"));
        else
            return -1;
    }

//    public void insertImgIns(String imageName,String title){
//
//        SQLiteDatabase gdb = this.getReadableDatabase();
//        Integer imgId, insId;
//
//        String imageIdQuery = "select imageId from image where imageName = \"" + imageName + "\"";
//        String instructionIdQuery = "select instructionId from instruction where title = \"" + title + "\"";
//
//        Cursor insCur = gdb.rawQuery(imageIdQuery, null);
//        insCur.moveToFirst();
//        imgId = insCur.getInt(0);
//
//        insCur = gdb.rawQuery(instructionIdQuery, null);
//        insCur.moveToFirst();
//        insId = insCur.getInt(0);
//        gdb.close();
//        ///
//        SQLiteDatabase idb = this.getWritableDatabase();
//        ContentValues imgInsCv = new ContentValues();
//
//        imgInsCv.put("imageId", imgId);
//        imgInsCv.put("instructionId", insId);
//
//        idb.insert("[image-instruction]", null, imgInsCv);
//        idb.close();
//    }

//    public void inserttblIns(String tableName,String title){
//
//        SQLiteDatabase gdb = this.getReadableDatabase();
//        Integer imgId, insId;
//
//        String tableIdQuery = "select tableId from [table] where tableName = \"" + tableName + "\"";
//        String instructionIdQuery = "select instructionId from instruction where title = \"" + title + "\"";
//
//        Cursor insCur = gdb.rawQuery(tableIdQuery, null);
//        insCur.moveToFirst();
//        imgId = insCur.getInt(0);
//
//        insCur = gdb.rawQuery(instructionIdQuery, null);
//        insCur.moveToFirst();
//        insId = insCur.getInt(0);
//        gdb.close();
//        ///
//        SQLiteDatabase idb = this.getWritableDatabase();
//        ContentValues imgInsCv = new ContentValues();
//
//        imgInsCv.put("tableId", imgId);
//        imgInsCv.put("instructionId", insId);
//
//        idb.insert("[table-instruction]", null, imgInsCv);
//        idb.close();
//    }


    /// standards function

    public ArrayList getAllstandards (){

        ArrayList list = new ArrayList<String>();
        SQLiteDatabase gdb = this.getReadableDatabase();
        String gQuery = "select title From standard";
        Cursor insCur = gdb.rawQuery(gQuery, null);
        if (insCur.moveToFirst()) {
            while (!insCur.isAfterLast()) {
                String name = insCur.getString(insCur.getColumnIndex("title"));

                list.add(name);
                insCur.moveToNext();
            }
        }

        return list;

    }


    public int getStandardId(String title){

        SQLiteDatabase gdb = this.getReadableDatabase();
        String gQuery = "select standardId From standard where title =\"" +title+"\"";
        Cursor insCur = gdb.rawQuery(gQuery, null);
        insCur.moveToFirst();
        return insCur.getInt(insCur.getColumnIndex("standardId"));
    }


    public Standardclass getStandard (Context context, String stnId){
        byte[] image;
        byte[] table;
        Integer imgId, tblId;
        Standardclass gIns = new Standardclass();
        SQLiteDatabase gdb = this.getReadableDatabase();

        String gQuery = "select * From standard where standardId=\"" +stnId+"\"";
        Cursor insCur = gdb.rawQuery(gQuery, null);


        if (insCur.moveToFirst()){
            Log.i("Zeinab", "if");
            gIns.txt = insCur.getString(1);
            gIns.title = insCur.getString(2);

            String imageIdQuery = "select imageId from [image-standard] where standardId = " + insCur.getInt(0);
            Cursor isCur = gdb.rawQuery(imageIdQuery, null);
            isCur.moveToFirst();
            imgId = isCur.getInt(0);
            int i = 0;
            gIns.imageName= new ArrayList<String>();
            while(isCur.moveToNext()){
                Log.i("sara","while");
                imgId = isCur.getInt(0);
                String imageNameQuery = "select imageName from image where imageId = " + imgId;
                Cursor isCur2 = gdb.rawQuery(imageNameQuery, null);
                isCur2.moveToFirst();

                gIns.imageName.add(i ,isCur2.getString(0));
                gIns.imageData = BitmapFactory.decodeResource(context.getResources(),context.getResources().
                        getIdentifier(gIns.imageName.get(i), "drawable", context.getPackageName()));

                i++;
            }





            //table
            String tableIdQuery = "select tableId from [table-standard] where standardId = " + insCur.getInt(0);
            isCur = gdb.rawQuery(tableIdQuery, null);
            isCur.moveToFirst();
            tblId = isCur.getInt(0);
            i =0;
            gIns.tableName= new ArrayList<String>();
            while(isCur.moveToNext()) {
                tblId = isCur.getInt(0);
                String tableNameQuery = "select tableName from [table] where tableId = " + tblId;
                Cursor  isCur2 = gdb.rawQuery(tableNameQuery, null);
                isCur2.moveToFirst();
                gIns.tableName.add(i , isCur2.getString(0));
                gIns.tableData = BitmapFactory.decodeResource(context.getResources(), context.getResources().
                        getIdentifier(gIns.tableName.get(i), "drawable", context.getPackageName()));
                i++;
            }//while
        }
        else{
            Log.i("Zeinab", "else");
            gIns.txt = insCur.getString(1);
            gIns.title = insCur.getString(2);
        }

        //byte[] to Bitmap
//        gIns.imageData = BitmapFactory.decodeByteArray(image, 0, image.length);
//        gIns.tableData = BitmapFactory.decodeByteArray(table, 0, table.length);
        gdb.close();
        Log.i("Zeinab", "getInstruction");
        return gIns;
    }



    public Cursor getProjectExport (String projectId){

        Project gPrj = new Project();
        SQLiteDatabase gdb = this.getReadableDatabase();

        String gQuery = "select * From project where projectId=\"" +projectId+"\"";
        Cursor insCur = gdb.rawQuery(gQuery, null);

        return insCur;
    }

    public Cursor getVisitExport (String projectId){

        visit gVst = new visit();
        SQLiteDatabase gdb = this.getReadableDatabase();

        String gQuery = "select * From createvisit where projectId=\"" +projectId+"\"";
        Cursor insCur = gdb.rawQuery(gQuery, null);

        return insCur;
    }

    public Project getProject (Context context, String projectId){

        Project gPrj = new Project();
        SQLiteDatabase gdb = this.getReadableDatabase();

        String gQuery = "select * From project where projectId=\"" +projectId+"\"";
        Cursor insCur = gdb.rawQuery(gQuery, null);

        insCur.moveToFirst();
        Log.i("Zeinab", "if");
        gPrj.title = insCur.getString(1);
        gPrj.fileNum = insCur.getString(2);
        gPrj.contractNum = insCur.getString(3);
        gPrj.contractorName = insCur.getString(4);
        gPrj.ownerName = insCur.getString(5);
        gPrj.designerName = insCur.getString(6);
        gPrj.observerName = insCur.getString(7);
        gPrj.address = insCur.getString(8);
        gPrj.startDate = insCur.getString(9);
        gPrj.endDate = insCur.getString(10);
        gPrj.condition = insCur.getString(11);


        gdb.close();
        Log.i("Zeinab", "getInstruction");
        return gPrj;
    }
    //






    public void updateProject(Project iPro, String projectId)throws SQLiteException{

        Log.i("Zeinab", "Enter!");
        SQLiteDatabase idb = this.getWritableDatabase();
        ContentValues projectCv = new ContentValues();

        projectCv.put("title", iPro.title);
        projectCv.put("FileNum", iPro.fileNum);
        projectCv.put("contractNum", iPro.contractNum);
        projectCv.put("contractorName", iPro.contractorName);
        projectCv.put("ownerName", iPro.ownerName);
        projectCv.put("designerName", iPro.designerName);
        projectCv.put("observerName", iPro.observerName);
        projectCv.put("address", iPro.address);
        projectCv.put("startDate", iPro.startDate);
        projectCv.put("endDate", iPro.endDate);
        projectCv.put("condition", iPro.condition);

//        idb.insert("project", null, projectCv);
        idb.update("project", projectCv, "projectId="+projectId, null);
        idb.close();
        Log.i("Zeinab", "updateProject!");
    }

    public void deleteProject (String projectId){
        SQLiteDatabase idb = this.getWritableDatabase();
        ContentValues projectCv = new ContentValues();

        idb.delete("project", "projectId = ?", new String[] { projectId });
//        idb.update("project", projectCv, "projectId="+projectId, null);
        idb.close();
        Log.i("Zeinab", "deleteProject!");
    }

    public void insertProject(Project iPro)throws SQLiteException{

        Log.i("Zeinab", "Enter!");
        SQLiteDatabase idb = this.getWritableDatabase();
        ContentValues projectCv = new ContentValues();

        projectCv.put("title", iPro.title);
        projectCv.put("FileNum", iPro.fileNum);
        projectCv.put("contractNum", iPro.contractNum);
        projectCv.put("contractorName", iPro.contractorName);
        projectCv.put("ownerName", iPro.ownerName);
        projectCv.put("designerName", iPro.designerName);
        projectCv.put("observerName", iPro.observerName);
        projectCv.put("address", iPro.address);
        projectCv.put("startDate", iPro.startDate);
        projectCv.put("endDate", iPro.endDate);
        projectCv.put("condition", iPro.condition);

        idb.insert("project", null, projectCv);
        idb.close();
        Log.i("Zeinab", "insertProject!");
    }

    public void insertDesigner(Superviser iSuper)throws SQLiteException{

        Log.i("Zeinab", "Enter!");
        SQLiteDatabase idb = this.getWritableDatabase();
        ContentValues designerCv = new ContentValues();

        designerCv.put("name", iSuper.name);
        designerCv.put("phoneNumber", iSuper.phoneNumber);
        designerCv.put("tel", iSuper.tel);
        designerCv.put("fax", iSuper.fax);
        designerCv.put("address", iSuper.address);

        idb.insert("designer", null, designerCv);
        idb.close();
        Log.i("Zeinab", "insertDesinger!");
    }

    public void insertOwner(Superviser iSuper)throws SQLiteException{

        Log.i("Zeinab", "Enter!");
        SQLiteDatabase idb = this.getWritableDatabase();
        ContentValues ownerCv = new ContentValues();

        ownerCv.put("name", iSuper.name);
        ownerCv.put("phoneNumber", iSuper.phoneNumber);
        ownerCv.put("tel", iSuper.tel);
        ownerCv.put("fax", iSuper.fax);
        ownerCv.put("address", iSuper.address);

        idb.insert("owner", null, ownerCv);
        idb.close();
        Log.i("Zeinab", "insertOwner!");
    }

    public void insertObserver(Superviser iSuper)throws SQLiteException{

        Log.i("Zeinab", "Enter!");
        SQLiteDatabase idb = this.getWritableDatabase();
        ContentValues observerCv = new ContentValues();

        observerCv.put("name", iSuper.name);
        observerCv.put("phoneNumber", iSuper.phoneNumber);
        observerCv.put("tel", iSuper.tel);
        observerCv.put("fax", iSuper.fax);
        observerCv.put("address", iSuper.address);

        idb.insert("observer", null, observerCv);
        idb.close();
        Log.i("Zeinab", "insertObserver!");
    }

    public void insertContractor(Superviser iSuper)throws SQLiteException{

        Log.i("Zeinab", "Enter!");
        SQLiteDatabase idb = this.getWritableDatabase();
        ContentValues contractorCv = new ContentValues();

        contractorCv.put("name", iSuper.name);
        contractorCv.put("phoneNumber", iSuper.phoneNumber);
        contractorCv.put("tel", iSuper.tel);
        contractorCv.put("fax", iSuper.fax);
        contractorCv.put("address", iSuper.address);

        idb.insert("contractor", null, contractorCv);
        idb.close();
        Log.i("Zeinab", "insertContractor!");
    }


    public HashMap joinAssortWithInstruction()
    {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        ArrayList list;
        SQLiteDatabase gdb = this.getReadableDatabase();
        String gQuery = "select * From iAssortment";
        Cursor insCur = gdb.rawQuery(gQuery, null);
        if (insCur.moveToFirst()) {
            while (!insCur.isAfterLast()) {
                list = new ArrayList<String>();
                String name = insCur.getString(insCur.getColumnIndex("title"));
                String Query = "select title From instruction , [iAssortment-instruction]" +
                        " where instruction.instructionId = [iAssortment-instruction].instructionId and " +
                        "[iAssortment-instruction].assortId ="+insCur.getInt(0);;
                Cursor ins2Cur = gdb.rawQuery(Query, null);
                if (ins2Cur.moveToFirst())
                    while (!ins2Cur.isAfterLast())
                    {
                        String title = ins2Cur.getString(ins2Cur.getColumnIndex("title"));
                        list.add(title);
                        ins2Cur.moveToNext();
                    }

                expandableListDetail.put(name , list);
                insCur.moveToNext();


            }
        }

        return expandableListDetail;


    }


    public ArrayList getSimilarInstruction (String inst_id){

        ArrayList list = new ArrayList<String>();
        SQLiteDatabase gdb = this.getReadableDatabase();
        String gQuery = "select assortId From [iAssortment-instruction] where instructionId ="+ Integer.parseInt(inst_id)  ;
        Cursor insCur = gdb.rawQuery(gQuery, null);
        insCur.moveToFirst();
        String Query = "select title From instruction , [iAssortment-instruction]" +
                " where instruction.instructionId = [iAssortment-instruction].instructionId and " +
                "[iAssortment-instruction].assortId ="+insCur.getInt(0);;
        Cursor ins2Cur = gdb.rawQuery(Query, null);

        if (ins2Cur.moveToFirst()) {
            while (!ins2Cur.isAfterLast()) {
                String name = ins2Cur.getString(ins2Cur.getColumnIndex("title"));

                list.add(name);
                ins2Cur.moveToNext();
            }
        }

        return list;

    }


    public HashMap joinAssortWithStandard()
    {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        ArrayList list;
        SQLiteDatabase gdb = this.getReadableDatabase();
        String gQuery = "select * From sAssortment";
        Cursor insCur = gdb.rawQuery(gQuery, null);
        if (insCur.moveToFirst()) {
            while (!insCur.isAfterLast()) {
                list = new ArrayList<String>();
                String name = insCur.getString(insCur.getColumnIndex("title"));
                String Query = "select title From standard , [sAssortment-standard]" +
                        " where standard.standardId = [sAssortment-standard].standardId and " +
                        "[sAssortment-standard].assortId ="+insCur.getInt(0);;
                Cursor ins2Cur = gdb.rawQuery(Query, null);
                if (ins2Cur.moveToFirst())
                    while (!ins2Cur.isAfterLast())
                    {
                        String title = ins2Cur.getString(ins2Cur.getColumnIndex("title"));
                        list.add(title);
                        ins2Cur.moveToNext();
                    }

                expandableListDetail.put(name , list);
                insCur.moveToNext();


            }
        }

        return expandableListDetail;


    }


    public ArrayList getSimilarStandard (String stn_id){

        ArrayList list = new ArrayList<String>();
        SQLiteDatabase gdb = this.getReadableDatabase();
        String gQuery = "select assortId From [sAssortment-standard] where standardId ="+ Integer.parseInt(stn_id)  ;
        Cursor insCur = gdb.rawQuery(gQuery, null);
        insCur.moveToFirst();
        String Query = "select title From standard , [sAssortment-standard]" +
                " where standard.standardId = [sAssortment-standard].standardId and " +
                "[sAssortment-standard].assortId ="+insCur.getInt(0);;
        Cursor ins2Cur = gdb.rawQuery(Query, null);

        if (ins2Cur.moveToFirst()) {
            while (!ins2Cur.isAfterLast()) {
                String name = ins2Cur.getString(ins2Cur.getColumnIndex("title"));

                list.add(name);
                ins2Cur.moveToNext();
            }
        }

        return list;

    }

    public void insertVisit(visit visit) throws SQLiteException {

        Log.i("Zeinab", "Enter!");
        SQLiteDatabase gdb = this.getReadableDatabase();
        SQLiteDatabase idb = this.getWritableDatabase();
        ContentValues visitCv = new ContentValues();
        ContentValues imageCv = new ContentValues();
        ContentValues foriegnCv = new ContentValues();

        visitCv.put("Date", visit.date);
        visitCv.put("title", visit.title);
        visitCv.put("FileNum", visit.fileNum);
        visitCv.put("text", visit.text);
        visitCv.put("projectId", visit.projectId);

        if (visit.location != null)
            visitCv.put("location", visit.location);

        String Query = "SELECT visitId \n" +
                "    FROM    createVisit\n" +
                "    WHERE   visitId = (SELECT MAX(visitId)  FROM createVisit);";
        ;
        Cursor insCur = gdb.rawQuery(Query, null);
        insCur.moveToFirst();


        for (int i = 0; i < visit.images.size(); i++) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            visit.images.get(i).compress(Bitmap.CompressFormat.PNG, 0, stream);
            imageCv.put("image", stream.toByteArray());
            idb.insert("vImage", null, imageCv);

            String Query2 = "SELECT imageId \n" +
                    "    FROM    vImage\n" +
                    "    WHERE   imageId = (SELECT MAX(imageId)  FROM vImage);";
            ;
            Cursor ins2Cur = gdb.rawQuery(Query2, null);
            ins2Cur.moveToFirst();

            if (insCur.getCount() == 0)
                foriegnCv.put("visitId", 1);
            else
                foriegnCv.put("visitId", insCur.getInt(0) + 1);

            foriegnCv.put("imageId", ins2Cur.getInt(0));

            idb.insert("[createVisit-vImage]", null, foriegnCv);


        }

        for (int i = 0; i < visit.p_images.size(); i++) {

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            visit.p_images.get(i).compress(Bitmap.CompressFormat.PNG, 0, stream);
            imageCv.put("image", stream.toByteArray());

            if (insCur.getCount() == 0)
                imageCv.put("visitId", 1);
            else
                imageCv.put("visitId", insCur.getInt(0) + 1);

            idb.insert("v_Pimage", null, imageCv);

        }


        idb.insert("createVisit", null, visitCv);
        idb.close();
        Log.i("Zeinab", "insertProject!");
    }

    public ArrayList getAllvisits(String project_id) {

        ArrayList list = new ArrayList<String>();
        SQLiteDatabase gdb = this.getReadableDatabase();
        String gQuery = "select title From createVisit where projectId =" + project_id;
        Cursor insCur = gdb.rawQuery(gQuery, null);
        if (insCur.moveToFirst()) {
            while (!insCur.isAfterLast()) {
                String name = insCur.getString(insCur.getColumnIndex("title"));

                list.add(name);
                insCur.moveToNext();
            }
        }

        return list;

    }


    public int getVisitId(String title) {

        SQLiteDatabase gdb = this.getReadableDatabase();
        String gQuery = "select visitId From createVisit where title =\"" + title + "\"";
        Cursor insCur = gdb.rawQuery(gQuery, null);
        if (insCur.moveToFirst())
            return insCur.getInt(insCur.getColumnIndex("visitId"));
        else
            return -1;
    }

    public visit getVisit(Context context, String visitId) {
        byte[] image;

        Integer imgId;
        visit gIns = new visit();
        SQLiteDatabase gdb = this.getReadableDatabase();

        String gQuery = "select * From createVisit where visitId=\"" + visitId + "\"";
        Cursor insCur = gdb.rawQuery(gQuery, null);


        insCur.moveToFirst();
        Log.i("Zeinab", "if");
        gIns.projectId = insCur.getString(insCur.getColumnIndex("projectId"));
        gIns.date = insCur.getString(insCur.getColumnIndex("Date"));
        gIns.title = insCur.getString(insCur.getColumnIndex("title"));
        gIns.fileNum = insCur.getString(insCur.getColumnIndex("FileNum"));
        gIns.text = insCur.getString(insCur.getColumnIndex("text"));
        gIns.location = insCur.getString(insCur.getColumnIndex("location"));


        String imageIdQuery = "select imageId from [createVisit-vImage] where visitId =\"" + visitId + "\"";
        Cursor isCur = gdb.rawQuery(imageIdQuery, null);
        gIns.images = new ArrayList<Bitmap>();
        // gIns.byte_images = new ArrayList<byte[]>();
        isCur.moveToFirst();
        int i = 0;
        do {
            Log.i("sara", "while");
            imgId = isCur.getInt(isCur.getColumnIndex("imageId"));

            String imageNameQuery = "select image from vImage where imageId = " + imgId;
            Cursor isCur2 = gdb.rawQuery(imageNameQuery, null);
            isCur2.moveToFirst();

            image = isCur2.getBlob(isCur2.getColumnIndex("image"));
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;

            Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length , options );
            //gIns.byte_images.add(i , image);
            gIns.images.add(i, bmp);
            i++;
        }
        while (isCur.moveToNext()) ;


        String p_image_query = "select image from [v_Pimage] where visitId =\"" + visitId + "\"";
        Cursor pCur = gdb.rawQuery(p_image_query, null);
        gIns.p_images = new ArrayList<Bitmap>();
        pCur.moveToFirst();
        int j = 0;
        do {
            image = pCur.getBlob(pCur.getColumnIndex("image"));
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length , options );
            gIns.images.add(j, bmp);
            j++;
        }
        while (pCur.moveToNext()) ;

        return gIns;

    }



}