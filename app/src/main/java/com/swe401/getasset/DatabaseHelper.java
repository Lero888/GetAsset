package com.swe401.getasset;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {


//    private DatabaseHelper db;

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
    public static final String FK_IQID_IID = "itemID";

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

    //table Classrooms
    public static final String TABLE_CLASSROOMS = "CLASSROOMS";
    public static final String CID = "CID";
    public static final String ROOM_NAME ="roomName";

    //table ClassroomsBorrow
    public static final String TABLE_ROOM_BORROW ="ROOM_BORROW";
    public static final String CBID ="CBID";
    public static final String ROOM_USAGE = "roomUsage";
    public static final String ROOM_TEL_NO = "roomTelNo";
    public static final String FK_CBID_UID = "userID";
    public static final String FK_CBID_TID = "timeID";

    //table Time
    public static final String TABLE_TIME = "TIME";
    public static final String TID = "TID";
    public static final String ROOM_DATE = "roomDate";
    public static final String ROOM_TIME = "roomTime";
    public static final String STATUS = "status";
    public static final String FK_TID_CID = "roomID";


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
                    + " FOREIGN KEY (" + FK_IQID_IID + ") REFERENCES " + TABLE_ITEM + "(" + IID + "));");


            db.execSQL("CREATE TABLE "+ TABLE_ITEM_BORROW + "( " + IBID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + BORROW_DATE + " INTEGER, "
                    + BORROW_QUANTITY + " INTEGER, "
                    + BORROW_STATUS + " TEXT, "
                    + TEL_NO + " TEXT, "
                    + BORROW_USAGE + " TEXT, "
                    + " FOREIGN KEY (" + FK_IBID_IID + ") REFERENCES " + TABLE_ITEM + "(" + IID + "), "
                    + " FOREIGN KEY (" + FK_IBID_UID + ") REFERENCES " + TABLE_USER + "(" + UID + "));");

            db.execSQL("CREATE TABLE "+ TABLE_CLASSROOMS + "( " + CID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ROOM_NAME + "TEXT);");

            db.execSQL("CREATE TABLE "+ TABLE_TIME + "( " + TID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ROOM_DATE + "TEXT, "
                    + ROOM_TIME + "TEXT, "
                    + STATUS + "TEXT, "
                    + " FOREIGN KEY (" + FK_TID_CID + ") REFERENCES " + TABLE_CLASSROOMS + "(" + CID + "));");


            db.execSQL("CREATE TABLE "+ TABLE_ROOM_BORROW+ "( " + CBID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ROOM_USAGE + "TEXT, "
                    + ROOM_TEL_NO + "TEXT, "
                    +  " FOREIGN KEY (" + FK_CBID_UID + ") REFERENCES " + TABLE_USER + "(" + UID + "), "
                    + " FOREIGN KEY (" + FK_CBID_TID + ") REFERENCES " + TABLE_TIME + "(" + TID + "));");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEM_QUANTITY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEM_BORROW);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLASSROOMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROOM_BORROW);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TIME);

        onCreate(db);


    }

//    public void closeDatabase(DatabaseHelper db) {
//        if (db != null && db.isOpen()) {
//            db.close();
//        }
//    }

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

    public boolean insertClassroomData (String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();

        contentValue.put(ROOM_NAME,name);
        long res = db.insert(TABLE_CLASSROOMS, null, contentValue);

        if (res == -1) return false;
        else return true;
    }

    public boolean insertTimeData (String date, String time, String status, String roomID){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();

        contentValue.put(ROOM_DATE,date);
        contentValue.put(ROOM_TIME,time);
        contentValue.put(STATUS,status);
        contentValue.put(FK_TID_CID,roomID);
        long res = db.insert(TABLE_TIME, null, contentValue);

        if (res == -1) return false;
        else return true;
    }

    public Cursor fetchItemData(String item) {

        SQLiteDatabase db = this.getReadableDatabase();
//        List <Object> list = new ArrayList<>();
//        Cursor cursor = this.database.query(SQLiteHelper.TABLE_NAME_STUDENT, new String[]{SQLiteHelper._ID, SQLiteHelper.NAME, SQLiteHelper.AGE}, null, null, null, null, null);
        String queryString = "SELECT * FROM " + TABLE_ITEM + "WHERE " + ITEM_NAME + "= " + item;
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor != null) {

            cursor.moveToFirst();
        }

        return cursor;
    }

    public int getRoomID (String name){
        int roomID=0;
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT " + CID + " FROM " + TABLE_CLASSROOMS + "WHERE " + ROOM_NAME + "= " + name;
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            roomID=cursor.getInt(1);

        }
        return roomID;
    }

    public boolean checkRoomStatus (String date, String time, String name){
        int roomID = getRoomID(name);
        boolean status = true;
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT "+ STATUS + " FROM " + TABLE_TIME + "WHERE " + ROOM_DATE + "= " + date + "AND"
                + ROOM_TIME + "=" + time +  FK_TID_CID + "=" + roomID;
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            if (cursor.getString(1).equals("available")){
                status=true;
            }else{
                status =false;
            }
        }

        return status;
    }



}

