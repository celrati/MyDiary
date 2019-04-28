package com.example.achraf.mydiaries.DB;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class MyContentProviderOptions extends ContentProvider {


    // just to update and insert data --
    private DataBaseOption base_diary_option = null;

    private static String authority = "diary.option.get";
    public final static String DB_NAME = "diarie_db_options";

    public final static String TABLE_OPTION = "table_options";

    public final static String COLONNE_ID = "id";

    public final static String COLONNE_OPTION_NAME = "option_name";
    public final static String COLONNE_OPTION_VALUE = "option_value";


    private static final int CODE_OPTION = 1;
    private static final int CODE_OPTION_UPDATE = 2;
    private static final int CODE_OPTION_VALUE = 3;

    private static final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        matcher.addURI(authority, "insert", CODE_OPTION);
        matcher.addURI(authority, "update/*", CODE_OPTION_UPDATE);
        matcher.addURI(authority, "get", CODE_OPTION_VALUE);




    }

    public MyContentProviderOptions() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = base_diary_option.getWritableDatabase();
        int code = matcher.match(uri);
        String path;
        long id;
        switch(code){
            case CODE_OPTION:
                id = db.insert(TABLE_OPTION,null,values);
                path = "insert";
                Log.d("ach","insert option item ");
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
        this.base_diary_option = DataBaseOption.getInstance(this.getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = base_diary_option.getReadableDatabase();
        int code = matcher.match(uri);
        Cursor cursor = null;
        switch (code) {
            case CODE_OPTION_VALUE:
                cursor = db.query(TABLE_OPTION,new String[]{"rowid as _id","*"}, selection, selectionArgs, null, null, sortOrder);
               Log.d("dd",cursor.getCount()+" "+selection);
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
        SQLiteDatabase db = base_diary_option.getWritableDatabase();
        int code = matcher.match(uri);
        int id = 0;
        switch(code){
            case CODE_OPTION_UPDATE:
                String toUpdate = uri.getLastPathSegment();
                id = db.update(TABLE_OPTION,values,"option_name = '"+toUpdate+"'",null);
                break;
            default:
                throw new UnsupportedOperationException("Not yet implemented");
        }


        return id;
    }
}
