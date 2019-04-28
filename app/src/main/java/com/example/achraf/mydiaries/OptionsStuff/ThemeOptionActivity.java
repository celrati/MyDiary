package com.example.achraf.mydiaries.OptionsStuff;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.achraf.mydiaries.DB.AccesDataOption;
import com.example.achraf.mydiaries.R;

import java.util.ArrayList;

public class ThemeOptionActivity extends AppCompatActivity {

    private ListView lv = null;
    private ArrayList<String> themesList;
    private ArrayAdapter<String> adapter;
    private AccesDataOption accesDataOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_option);

        lv = findViewById(R.id.list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        accesDataOption = new AccesDataOption(this);
        //
        accesDataOption.insertOption("theme","lightHappyGreen");

        themesList = new ArrayList<>();
        themesList.add("lightHappyGreen");
        themesList.add("punkRock");
        themesList.add("skyBlue");
        themesList.add("classic");

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(id == 0) { // light happy green
                    accesDataOption.updateOption("theme","lightHappyGreen");
                }
                if(id == 1) { // light happy green
                    accesDataOption.updateOption("theme","punkRock");
                }
                if(id == 2) { // light happy green
                    accesDataOption.updateOption("theme","skyBlue");
                }
                if(id == 3) { // light happy green
                    accesDataOption.updateOption("theme","classic");
                }

            }
        });


        this.adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,themesList);
        lv.setAdapter(adapter);

    }


}
