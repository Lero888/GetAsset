package com.swe401.getasset;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // name of the database
    public static final String DB_NAME = "getAsset.db";

    // version of the database
    private static final int DB_VERSION = 1;

    // table USERS
    public static final String TABLE_USER = "USERS";
    public static final String UID = "UID";
    public static final String USER_NAME = "userName";
    public static final String USER_PW = "userPassword";
    public static final String USER_EMAIL = "userEmail";

    // table ITEMS
    public static final String TABLE_ITEM = "ITEMS";
    public static final String IID = "IID";
    public static final String ITEM_NAME = "itemName";
    public static final String ITEM_DEPARTMENT = "itemDepartment";
    public static final String ITEM_QUANTITY = "itemQuantity";
    public static final String ITEM_DESC = "itemDesc";
    public static final String ITEM_CATEGORY = "itemCategory";


    // table ItemQuantity
    public static final String TABLE_ITEM_QUANTITY = "ITEM_QUANTITY";
    public static final String IQID = "IQID";
    public static final String QUANTITY_LEFT = "quantityLeft";
    public static final String QUANTITY_DATE = "quantityDate";
    public static final String FK_IQIQ_IID = "itemID";

    // table ItemBorrow
    public static final String TABLE_ITEM_BORROW= "ITEM_BORROW";
    public static final String IBID = "IBID";
    public static final String BORROW_DATE = "borrowDate";
    public static final String BORROW_QUANTITY = "borrowQuantity";
    public static final String BORROW_STATUS = "borrowStatus";
    public static final String TEL_NO = "telNo";
    public static final String BORROW_USAGE = "borrowUsage";
    public static final String FK_IBID_IID = "itemID";
    public static final String FK_IBID_UID = "userID";


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
//        SQLiteDatabase db = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            db.execSQL("CREATE TABLE "+ TABLE_USER + "( " + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + USER_NAME + " TEXT, "
                    + USER_PW + " TEXT, "
                    + USER_EMAIL + " TEXT);");


            db.execSQL("CREATE TABLE "+ TABLE_ITEM+ "( " + IID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ITEM_NAME + " TEXT, "
                    + ITEM_DEPARTMENT + " TEXT, "
                    + ITEM_QUANTITY + " INTEGER, "
                    + ITEM_DESC + " TEXT, "
                    + ITEM_CATEGORY + " TEXT);");

            db.execSQL("CREATE TABLE "+ TABLE_ITEM_QUANTITY + "( " + IQID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + QUANTITY_LEFT + " INTEGER, "
                    + QUANTITY_DATE + " INTEGER, "
                    + FK_IQIQ_IID + " INTEGER);");

            db.execSQL("CREATE TABLE "+ TABLE_ITEM_BORROW + "( " + IBID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + BORROW_DATE + " INTEGER, "
                    + BORROW_QUANTITY + " INTEGER, "
                    + BORROW_STATUS + " TEXT, "
                    + TEL_NO + " TEXT, "
                    + BORROW_USAGE + " TEXT, "
                    + FK_IBID_IID + " INTEGER, "
                    + FK_IBID_UID + " INTEGER);");

            insertUserData("LEE ROU", "hello", "swe1704655@xmu.edu.my");
            insertUserData("LIM CAROL", "hello", "swe1704205@xmu.edu.my");

            insertItemData("Table", "Asset", 100, "Classroom Table", "Table");
            insertItemData("Chair", "Asset", 200, "Plastic Chair", "Chair");
            insertItemData("Microphone", "IT", 5, "Wireless Microphone (requires confirmation letter)", "IT Equipment");
            insertItemData("Speaker", "IT", 2, "Portable Speaker (with microphone)", "IT Equipment");


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEM);

        onCreate(db);


    }

    public boolean insertUserData(String name, String password, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put(USER_NAME, name);
        contentValue.put(USER_PW, password);
        contentValue.put(USER_EMAIL, email);
        long res = db.insert(TABLE_USER, null, contentValue);

        if (res == -1) return false;
        else return true;
    }

    public boolean insertItemData(String name, String department, Integer quantity, String desc, String category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();

        contentValue.put(ITEM_NAME, name);
        contentValue.put(ITEM_DEPARTMENT, department);
        contentValue.put(ITEM_QUANTITY, quantity);
        contentValue.put(ITEM_DESC, desc);
        contentValue.put(ITEM_CATEGORY, category);

        long res = db.insert(TABLE_ITEM, null, contentValue);

        if (res == -1) return false;
        else return true;

    }


}

