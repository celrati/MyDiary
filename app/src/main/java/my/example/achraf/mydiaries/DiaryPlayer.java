package my.example.achraf.mydiaries;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;

import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import my.example.achraf.mydiaries.DB.AccesData;

import com.google.android.gms.ads.AdView;

import java.util.List;

public class DiaryPlayer extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static String authority = "diary.get";

    private RecyclerView diaryListView = null;
    private List<Diary> diaries = null;
    private DiaryAdapter myAdapter = null;

    private AccesData accesData = null;
    private LoaderManager manager;

    private AdView adview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_player);

/*
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");

        adview = findViewById(R.id.banner);
    AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        adview.loadAd(adRequest);
*/
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.accesData = new AccesData(this);
        diaryListView = findViewById(R.id.diaryList);

        manager = getSupportLoaderManager();
        manager.initLoader(1, null, this);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.geomenu, menu);


        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menuAdd:
                Toast t = Toast.makeText(this,"adding new diary :)",Toast.LENGTH_LONG);
                t.show();
                startActivity(new Intent(this,NewDiarieActivity.class));
                //finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }



    // the Loader Manager

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
        Uri.Builder builder = new Uri.Builder();
        Uri uri = builder.scheme("content").authority(authority).appendPath("diary").build();
        return new CursorLoader(this, uri, null,null, null, null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
        switch (loader.getId()) {
            case 1:
                diaries = accesData.getDiariesWithCursor(cursor);
                this.diaryListView.setLayoutManager(new LinearLayoutManager(this));
                this.myAdapter = new DiaryAdapter(this, diaries, android.R.layout.simple_list_item_1);
                this.diaryListView.setAdapter(myAdapter);
                break;
        }

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }


    @Override
    protected void onResume() {
        super.onResume();
        getSupportLoaderManager().restartLoader(1,null,DiaryPlayer.this).forceLoad();

    }
}
