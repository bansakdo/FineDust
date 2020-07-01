package com.example.finedust;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

public class StationDAO {
    private final static String LOG_TAG = "StationDAO";
    private SQLiteDatabase db;
    private SQLiteOpenHelper dbHelper;

    public  StationDAO(Context ctx) {
        dbHelper = new StationDBHelper(ctx);
    }

    public static StationDAO open(Context ctx) {
        return new StationDAO(ctx);
    }

    public void addStation(Station station) {
        Log.i(LOG_TAG,"addStation() called");
        db = dbHelper.getWritableDatabase();

        ContentValues v = new ContentValues();
        v.put("sName", station.getsName());
        v.put("sAddr",station.getsAddr());
        v.put("sCode",station.getsCode());

        db.insert("station",null,v);
    }

    public List<Station> getAllStations() {
        List<Station> stations = new ArrayList<>();
        db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from station", null);

        while(cursor.moveToNext()) {
            Station station = new Station(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4));
            stations.add(station);

        }
        return stations;
    }


    public void delStation(String name) {
        db = dbHelper.getWritableDatabase();
        db.delete("station","sName=?",new String[]{name});
    }

    public Station getStation(String name) {
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from station where sName=?",new String[]{name});
        cursor.moveToNext();

        Station station = new Station(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4));

        return station;
    }

    public void updateStaion(Station station) {
        Log.i(LOG_TAG,"updateStation() called");
        db = dbHelper.getWritableDatabase();
        String sql = "UPDATE station SET sAddr = \'" + station.getsAddr() + "\', sCode = " + station.getsCode() + ", sFavo = \'" + station.issFavo() + "\' WHERE sName = \'" + station.getsName() + "\'";
//        String sql = "UPDATE station SET sAddr = ?, sCode = ?, sFavo = ? where sName = ?";

        db.execSQL(sql);
//        db.execSQL(sql, station.getsAddr(), station.getsCode(), station.issFavo(), station.getsName());
    }

}
