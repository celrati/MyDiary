package com.example.achraf.mydiaries.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

public class DataBase extends SQLiteOpenHelper {
    private static DataBase ourInstance;

    public final static int VERSION = 9;
    public final static String DB_NAME = "diarie_db";

    public final static String TABLE_DIARIE = "table_diary";
    public final static String COLONNE_ID = "id";

    public final static String COLONNE_DATE = "date";
    public final static String COLONNE_STORY = "story";

    public final static String CREATE_TABLE_DIARY =  "create table " + TABLE_DIARIE + "(" +
            COLONNE_ID + " integer primary key autoincrement not null, " +
            COLONNE_DATE + " string, " +
            COLONNE_STORY + " text " +");";

    public static DataBase getInstance(Context context) {
        if (ourInstance == null)
            ourInstance = new DataBase(context);
        return ourInstance;
    }

    private DataBase(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_DIARY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL("drop table if exists " + TABLE_DIARIE);
            onCreate(db);
        }
    }
}
