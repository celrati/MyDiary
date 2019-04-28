package com.example.achraf.mydiaries.DB;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.example.achraf.mydiaries.Diary;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class AccesData {

    public final static int VERSION = 9;
    public final static String DB_NAME = "diarie_db";

    public final static String TABLE_DIARIE = "table_diary";
    public final static String COLONNE_ID = "id";

    public final static String COLONNE_DATE = "date";
    public final static String COLONNE_STORY = "story";

    private static String authority = "diary.get";

    private ContentResolver contentResolver;


    public AccesData(Context context){
        contentResolver = context.getContentResolver();
    }

    public void insertDiary(String diary, String date){
        // to insert one Diary on the dataBase
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("content").authority(authority).appendPath("diary");
        Uri uri = builder.build();
        ContentValues values = new ContentValues();
        values.put(COLONNE_DATE,date);
        values.put(COLONNE_STORY,diary);
        uri = contentResolver.insert(uri,values);
    }


    public List<Diary> getDiaries(){
        // to get all the diaries
        Uri.Builder builder = new Uri.Builder();
        Uri uri = builder.scheme("content").authority(authority).appendPath("diary").build();
        Cursor cur = contentResolver.query(uri,null,null, null,null);
        int c = 0;
        List<Diary> ret = new ArrayList<>();
        while (cur.moveToNext()) {
            String date = cur.getString(cur.getColumnIndex(COLONNE_DATE));
            String story = cur.getString(cur.getColumnIndex(COLONNE_STORY));
            int id = cur.getInt(cur.getColumnIndex("_id"));
            ret.add(new Diary(id,date,story));

            // to debug
            Log.d("diary","id :"+id);
            Log.d("diary",date);
            Log.d("diary",story);
            Log.d("diary","______");
        }
        return ret;
    }
    public List<Diary> getDiariesWithCursor(Cursor cur){
        List<Diary> ret = new ArrayList<>();
        while (cur.moveToNext()) {
            String date = cur.getString(cur.getColumnIndex(COLONNE_DATE));
            String story = cur.getString(cur.getColumnIndex(COLONNE_STORY));
            int id = cur.getInt(cur.getColumnIndex("_id"));
            ret.add(new Diary(id,date,story));

            // to debug
            Log.d("diary","id :"+id);
            Log.d("diary",date);
            Log.d("diary",story);
            Log.d("diary","______");
        }
        return ret;
    }


    public void deleteAllDiaries(){
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("content").authority(authority).appendPath("deleteAll");
        Uri uri = builder.build();
        contentResolver.delete(uri,null,null);
    }
    public void deleteDiary(int id){
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("content").authority(authority).appendPath("delete").appendPath(String.valueOf(id));
        Uri uri = builder.build();
        contentResolver.delete(uri,null,null);
    }
    public void updateDiary(int id,String story){
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("content").authority(authority).appendPath("update").appendPath(String.valueOf(id));
        Uri uri = builder.build();
        ContentValues values = new ContentValues();
        values.put(COLONNE_STORY,story);
        contentResolver.update(uri,values,null,null);
    }


}