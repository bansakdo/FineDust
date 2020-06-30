package com.example.finedust;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context,"airdb",null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table airStation "+
                "(_id integer primary key autoincrement,"+
                "station text not null, sido text not null, pm10 integer, pm20 integer, addr text)";
        db.execSQL(sql);

        sql = "create table favorite "+
                "(_id integer primary key autoincrement,"+
                "station text not null, sido text not null, pm10 integer, pm20 integer)";
        db.execSQL(sql);

        db.execSQL("insert into airStation (station, sido) values ('강남구', '서울특별시')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertFavo(SQLiteDatabase db, String stationName) {
        String sql = "insert into favorite (station) values (stationName)";
        db.execSQL(sql);
    }
    public void removeFavo(SQLiteDatabase db, String stationName) {
        String sql = "delete from favorite where station = stationName";
        db.execSQL(sql);
    }
}
