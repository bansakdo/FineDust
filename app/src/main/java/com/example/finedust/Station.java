package com.example.finedust;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import javax.xml.namespace.QName;

@Entity(tableName = "station")
public class Station {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "name")
    private String sName;
    @ColumnInfo(name = "addr")
    private String sAddr;
    @ColumnInfo(name = "code")
    private int sCode;
    private boolean sFavo ;
    private String sSido;


    public Station(@NonNull String name) {
        sName = name;
    }

    public Station(@NonNull String name, String addr) {
        sName = name;
        sAddr = addr;
    }

    public Station(@NonNull String name, String addr, int code) {
        sName = name;
        sAddr = addr;
        sCode = code;
    }
    public Station(@NonNull String name, String sido, String addr, int code) {
        sName = name;
        sAddr = addr;
        sCode = code;
        sSido = sido;
    }


    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsAddr() {
        return sAddr;
    }

    public void setsAddr(String sAddr) {
        this.sAddr = sAddr;
    }

    public int getsCode() {
        return sCode;
    }

    public void setsCode(int sCode) {
        this.sCode = sCode;
    }

    public boolean issFavo() {
        return sFavo;
    }

    public void setsFavo(boolean sFavo) {
        this.sFavo = sFavo;
    }


    public String getsSido() {
        return sSido;
    }

    public void setsSido(String sSido) {
        this.sSido = sSido;
    }
}
