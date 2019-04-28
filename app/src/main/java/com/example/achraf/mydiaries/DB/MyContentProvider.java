package com.example.achraf.mydiaries.DB;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class MyContentProvider extends ContentProvider {

    private DataBase base_diary = null;

    private static String authority = "diary.get";

    private static final int CODE_DIARY = 1;
    private static final int CODE_DELETE_ALL = 2;
    private static final int CODE_DELETE = 3;
    private static final int CODE_UPDATE = 4;



    public final static String TABLE_DIARIE = "table_diary";



    private static final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        matcher.addURI(authority, "diary", CODE_DIARY);
        matcher.addURI(authority, "deleteAll", CODE_DELETE_ALL);
        matcher.addURI(authority, "delete/#", CODE_DELETE);
        matcher.addURI(authority,"update/#",CODE_UPDATE);


    }

    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = base_diary.getWritableDatabase();
        int code = matcher.match(uri);
        String path;
        int id;
        switch(code){
            case CODE_DELETE_ALL:
                // delete all the diaries from the database
                id = db.delete(TABLE_DIARIE,null,null);
                break;
            case CODE_DELETE:
                String id_toRemove = uri.getLastPathSegment();
                id = db.delete(TABLE_DIARIE,"rowid = "+id_toRemove,null);
                break;
            default:
                throw new UnsupportedOperationException("Not yet implemented");
        }

        return id;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = base_diary.getWritableDatabase();
        int code = matcher.match(uri);
        String path;
        long id;
        Log.d("ach","calling insert()");
        switch(code){
            case CODE_DIARY:
                id = db.insert(TABLE_DIARIE,null,values);
                path = "diary";
                Log.d("ach","insert data right now ");
                break;
            default:
                throw new UnsupportedOperationException("Not yet implemented");
        }
        Uri.Builder builder = (new Uri.Builder())
                .authority(authority)
                .appendPath(path);

        return ContentUris.appendId(builder, id).build();
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.

        this.base_diary = DataBase.getInstance(this.getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = base_diary.getReadableDatabase();
        int code = matcher.match(uri);
        Cursor cursor = null;
        switch (code) {
            case CODE_DIARY:
                cursor = db.query(TABLE_DIARIE,new String[]{"rowid as _id","*"}, selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                Log.d("Uri provider =", uri.toString());
                throw new UnsupportedOperationException("QUERY, not yet implemented code(401)");
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        SQLiteDatabase db = base_diary.getWritableDatabase();
        int code = matcher.match(uri);
        int id = 0;
        switch(code){
            case CODE_UPDATE:
                String id_toUpdate = uri.getLastPathSegment();
                id = db.update(TABLE_DIARIE,values,"rowid = "+id_toUpdate,null);
                break;
            default:
                throw new UnsupportedOperationException("Not yet implemented");
        }


        return id;
    }
}
