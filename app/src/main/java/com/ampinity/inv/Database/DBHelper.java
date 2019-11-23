package com.ampinity.inv.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "InventrorySys.db";
    public static final String CONTACTS_TABLE_NAME = "registration";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 2);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table registration " +
                        "(id integer primary key AUTOINCREMENT, nameOfBusi text,nameOfComp text,addr1 text, addr2 text,phone1 text,phone2 text,contactName text,mobno text,email text,state text,city text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS registration");
        onCreate(db);
    }
    public boolean insertRegistration (String nameOfBusi, String nameOfComp, String addr1, String addr2,String phone1,String phone2,String contactName,String mobno,String email,String state,String city) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nameOfBusi", nameOfBusi);
        contentValues.put("nameOfComp", nameOfComp);
        contentValues.put("addr1", addr1);
        contentValues.put("addr2", addr2);
        contentValues.put("phone1", phone1);
        contentValues.put("phone2", phone2);
        contentValues.put("contactName", contactName);
        contentValues.put("mobno", mobno);
        contentValues.put("email", email);
        contentValues.put("state", state);
        contentValues.put("city", city);
        db.insert("registration", null, contentValues);
        db.setTransactionSuccessful();
        db.endTransaction();
        return true;
    }
}
