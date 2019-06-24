package my.example.achraf.mydiaries.OptionsStuff;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import my.example.achraf.mydiaries.R;

import java.util.ArrayList;

public class OptionsActivity extends AppCompatActivity {

    private ListView lv = null;
    private ArrayList<String> optionsList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        lv = findViewById(R.id.list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("options ...");

        optionsList = new ArrayList<>();
        optionsList.add("Pseudo");
        optionsList.add("Password");
        /*
        optionsList.add("Theme");
        optionsList.add("Backup");
        optionsList.add("TextSize");
        optionsList.add("TextColor");
        optionsList.add("Font");
        optionsList.add("export");
        optionsList.add("reset all data");
        optionsList.add("Language");
        optionsList.add("about");
        */

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(id == 0){ // pseudo
                    startActivity(new Intent(OptionsActivity.this,PseudoOptionActivity.class));
                }
                if(id == 1){ // pseudo
                    startActivity(new Intent(OptionsActivity.this,PasswordOptionActivity.class));
                }
                /*
                if(id == 2){ // pseudo
                    startActivity(new Intent(OptionsActivity.this,ThemeOptionActivity.class));
                }
                */

            }
        });


        this.adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,optionsList);
        lv.setAdapter(adapter);

    }

}
