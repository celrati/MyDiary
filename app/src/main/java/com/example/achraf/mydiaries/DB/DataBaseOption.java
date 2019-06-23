package com.example.achraf.mydiaries.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseOption extends SQLiteOpenHelper {

    private static DataBaseOption ourInstance;

    public final static int VERSION = 9;
    public final static String DB_NAME = "diarie_db_options";

    public final static String TABLE_OPTION = "table_options";

    public final static String COLONNE_ID = "id";

    public final static String COLONNE_OPTION_NAME = "option_name";
    public final static String COLONNE_OPTION_VALUE = "option_value";

    public final static String CREATE_TABLE_OPTION =  "create table " + TABLE_OPTION + "(" +
            COLONNE_ID + " integer primary key autoincrement not null, " +
            COLONNE_OPTION_NAME + " string, " +
            COLONNE_OPTION_VALUE + " string " +");";

    public final static String INIT_QUERIES_TABLE = "INSERT INTO table_options " +
            "(option_name,option_value ) VALUES " +
            "('pseudo','owner')," +
            "('password','0000')," +
            "('secure','OFF')"; // secure is for activate the password or not


    public static DataBaseOption getInstance(Context context) {
        if (ourInstance == null)
            ourInstance = new DataBaseOption(context);
        return ourInstance;
    }

    private DataBaseOption(Context context) {
        super(context, DB_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_OPTION);
        db.execSQL(INIT_QUERIES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL("drop table if exists " + TABLE_OPTION);
            onCreate(db);
        }
    }
}
