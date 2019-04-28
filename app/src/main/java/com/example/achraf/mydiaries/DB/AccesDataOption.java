package com.example.achraf.mydiaries.DB;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

public class AccesDataOption {
    private static String authority = "diary.option.get";
    public final static String DB_NAME = "diarie_db_options";

    public final static String TABLE_OPTION = "table_options";

    public final static String COLONNE_ID = "id";

    public final static String COLONNE_OPTION_NAME = "option_name";
    public final static String COLONNE_OPTION_VALUE = "option_value";

    private ContentResolver contentResolver;

    public AccesDataOption(Context context){
        contentResolver = context.getContentResolver();
    }

    public void insertOption(String nameOption, String valueOption){
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("content").authority(authority).appendPath("insert");
        Uri uri = builder.build();
        ContentValues values = new ContentValues();
        values.put(COLONNE_OPTION_NAME,nameOption);
        values.put(COLONNE_OPTION_VALUE,valueOption);
        uri = contentResolver.insert(uri,values);
    }
    public void updateOption(String nameOption, String valueOption){
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("content").authority(authority).appendPath("update").appendPath(String.valueOf(nameOption));
        Uri uri = builder.build();
        ContentValues values = new ContentValues();
        values.put(COLONNE_OPTION_VALUE,valueOption);
        contentResolver.update(uri,values,null,null);
    }
    public String getOption(String nameOption){
        Uri.Builder builder = new Uri.Builder();
        Uri uri = builder.scheme("content").authority(authority).appendPath("get").build();
        Cursor cur = contentResolver.query(uri,null,"option_name =  '"+nameOption+"' ",null,null);
        cur.moveToFirst();
        String ret = cur.getString(cur.getColumnIndex(COLONNE_OPTION_VALUE));
        Log.d("dd",ret);
        return ret;

    }

}
