package com.example.loginsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static  final String databaseName = "UserDetails.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "UserDetails.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE USERS(Email TEXT PRIMARY KEY, Password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS USERS");

    }

    public  Boolean checkEmail(String Email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM USERS WHERE Email = ?", new  String[]{Email});

        if(cursor.getCount() > 0){
            return  true;
        } else {
            return  false;
        }
    }

    public Boolean insertData(String Email, String Password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Email", Email);
        contentValues.put("Password", Password);
        long result = db.insert("USERS", null, contentValues);

        if(result == -1){
            return  false;
        }
        else{
            return  true;
        }
    }

    public Boolean checkEmailPword(String Email, String Password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM USERS WHERE Email = ? AND Password = ?", new String[]{Email, Password});

        if(cursor.getCount() > 0){
            return  true;
        }else{
            return  false;
        }

    }
}
