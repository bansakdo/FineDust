package com.example.finedust;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StationDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public StationDBHelper(Context context) {
        super(context,"finedustdb",null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table station "+
                "(_id integer primary key autoincrement,"+
                "sName text, sSido text, sAddr text, sCode integer, sFavo boolean default 'false')";
        db.execSQL(sql);

        // insert sample data
        db.execSQL("insert into station (sName, sSido, sAddr, sCode) values ('강남구', '서울', '서울 강남구 학동로 426', 111261)");
        db.execSQL("insert into station (sName, sSido, sAddr, sCode) values ('서초구', '서울', '서울 서초구 신반포로15길 16', 111262)");
        db.execSQL("insert into station (sName, sSido, sAddr, sCode) values ('송파구', '서울', '서울 송파구 백제고분로 236', 111273)");
        db.execSQL("insert into station (sName, sSido, sAddr, sCode) values ('용산구', '서울', '서울 용산구 한남대로 136', 111131)");
        db.execSQL("insert into station (sName, sSido, sAddr, sCode) values ('광진구', '서울', '서울특별시 광진구 광나루로 571', 111141)");
        db.execSQL("insert into station (sName, sSido, sAddr, sCode) values ('강동구', '서울', '서울 강동구 구천면로 42길 59', 111274)");
        db.execSQL("insert into station (sName, sSido, sAddr, sCode) values ('강북구', '서울', '서울 강북구 삼양로 139길 49', 111291)");
        db.execSQL("insert into station (sName, sSido, sAddr, sCode) values ('강서구', '서울', '서울 강서구 강서로 45 다길 71', 111212)");
        db.execSQL("insert into station (sName, sSido, sAddr, sCode) values ('관악구', '서울', '서울 관악구 신림동길 14', 111251)");
        db.execSQL("insert into station (sName, sSido, sAddr, sCode) values ('구로구', '서울', '서울 구로구 가마산로 27길 45', 111221)");
        db.execSQL("insert into station (sName, sSido, sAddr, sCode) values ('노원구', '서울', '서울 노원구 상계로 118', 111311)");
        db.execSQL("insert into station (sName, sSido, sAddr, sCode) values ('영등포구', '서울', '서울특별시 영등포구 당산로 123', 111231)");
        db.execSQL("insert into station (sName, sSido, sAddr, sCode) values ('종로구', '서울', '서울 종로구 종로35가길 19', 111123)");
        db.execSQL("insert into station (sName, sSido, sAddr, sCode) values ('마포구', '서울', '서울 마포구 포은로 6길 10', 111201)");
        db.execSQL("insert into station (sName, sSido, sAddr, sCode) values ('금천구', '서울', '서울 금천구 금하로21길 20', 111281)");
        db.execSQL("insert into station (sName, sSido, sAddr, sCode) values ('도봉구', '서울', '서울 도봉구 시루봉로2길 34', 111171)");
        db.execSQL("insert into station (sName, sSido, sAddr, sCode) values ('동대문구', '서울', '서울 동대문구 천호대로13길 43', 111152)");
        db.execSQL("insert into station (sName, sSido, sAddr, sCode) values ('동작구', '서울', '서울 동작구 사당로16아길 6', 111241)");
        db.execSQL("insert into station (sName, sSido, sAddr, sCode) values ('서대문구', '서울', '서울 서대문구 세검정로4길 32', 111191)");
        db.execSQL("insert into station (sName, sSido, sAddr, sCode) values ('성동구', '서울', '서울 성동구 뚝섬로3길 18', 111142)");
        db.execSQL("insert into station (sName, sSido, sAddr, sCode) values ('성북구', '서울', '서울 성북구 삼양로2길 70', 111161)");
        db.execSQL("insert into station (sName, sSido, sAddr, sCode) values ('양천구', '서울', '서울 양천구 중앙로52길 56', 111301)");
        db.execSQL("insert into station (sName, sSido, sAddr, sCode) values ('은평구', '서울', '서울 은평구 진흥로 215', 111181)");
        db.execSQL("insert into station (sName, sSido, sAddr, sCode) values ('중구', '서울', '서울 중구 덕수궁길 15', 111121)");
        db.execSQL("insert into station (sName, sSido, sAddr, sCode) values ('중랑구', '서울', '서울 중랑구 용마산로 369', 111151)");

        db.execSQL("insert into station (sName, sSido, sAddr, sCode) values ('개금동', '부산', '부산 부산진구 개금온정로17번길 51', 221283)");
        db.execSQL("insert into station (sName, sSido, sAddr, sCode) values ('광복동', '부산', '부산 중구 광복로 55번길 10', 221112)");
        db.execSQL("insert into station (sName, sSido, sAddr, sCode) values ('광안동', '부산', '부산 수영구 광안로21번가길 57', 221271)");
        db.execSQL("insert into station (sName, sSido, sAddr, sCode) values ('기장읍', '부산', '부산 기장군 기장읍 읍내로 69', 221231)");
        db.execSQL("insert into station (sName, sSido, sAddr, sCode) values ('녹산동', '부산', '부산 강서구 녹산산단 382로 49번길 39', 221212)");
        db.execSQL("insert into station (sName, sSido, sAddr, sCode) values ('당리동', '부산', '부산 사하구 제석로 41', 221284)");
        db.execSQL("insert into station (sName, sSido, sAddr, sCode) values ('대신동', '부산', '부산 서구 대신로 150', 221281)");
        db.execSQL("insert into station (sName, sSido, sAddr, sCode) values ('대연동', '부산', '부산 남구 수영로 196번길 80', 221141)");
        db.execSQL("insert into station (sName, sSido, sAddr, sCode) values ('대저동', '부산', '부산강서구 체육공원로 43', 221211)");
        db.execSQL("insert into station (sName, sSido, sAddr, sCode) values ('덕천동', '부산', '부산 북구 덕천2길 10', 221182)");
        db.execSQL("insert into station (sName, sSido, sAddr, sCode) values ('덕포동', '부산', '부산 사상구 삼덕로 83', 221282)");
        db.execSQL("insert into station (sName, sSido, sAddr, sCode) values ('명장동', '부산', '부산 동래구 명장로 32', 221163)");
        db.execSQL("insert into station (sName, sSido, sAddr, sCode) values ('부곡동', '부산', '부산 금정구 부곡로156번길 7', 221251)");
        db.execSQL("insert into station (sName, sSido, sAddr, sCode) values ('수정동', '부산', '부산 동구 구청로 1', 221141)");
        db.execSQL("insert into station (sName, sSido, sAddr, sCode) values ('연산동', '부산', '부산 연제구 중앙대로 1001', 221221)");
        db.execSQL("insert into station (sName, sSido, sAddr, sCode) values ('용수리', '부산', '부산 기장군 정관읍 용수로 4', 221233)");
        db.execSQL("insert into station (sName, sSido, sAddr, sCode) values ('장림동', '부산', '부산 사하구 장림로 161번길 2', 221202)");
        db.execSQL("insert into station (sName, sSido, sAddr, sCode) values ('재송동', '부산', '부산광역시 해운대구 센텀동로 191', 221193)");
        db.execSQL("insert into station (sName, sSido, sAddr, sCode) values ('전포동', '부산', '부산 진구 전포대로 209번길 26', 221152)");
        db.execSQL("insert into station (sName, sSido, sAddr, sCode) values ('좌동', '부산', '부산 해운대구 양운로 91', 221192)");
        db.execSQL("insert into station (sName, sSido, sAddr, sCode) values ('청룡동', '부산', '부산 금정구 청룡로 25', 221191)");
        db.execSQL("insert into station (sName, sSido, sAddr, sCode) values ('청학동', '부산', '부산광역시 영도구 청학남로13번길 18', 221142)");
        db.execSQL("insert into station (sName, sSido, sAddr, sCode) values ('태종대', '부산', '부산 영도구 전망로 24', 221141)");
        db.execSQL("insert into station (sName, sSido, sAddr, sCode) values ('학장동', '부산', '부산 사상구 대동로 205', 221181)");
        db.execSQL("insert into station (sName, sSido, sAddr, sCode) values ('화명동', '부산', '부산광역시 북구 용당로16번길 22', 221183)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion == DATABASE_VERSION) {
            db.execSQL("drop table if exists station");
            onCreate(db);
        }
    }
}
