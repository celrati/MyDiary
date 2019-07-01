package my.example.achraf.mydiaries;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import my.example.achraf.mydiaries.R;

import my.example.achraf.mydiaries.DB.AccesData;

public class DiaryEditing extends AppCompatActivity {

    EditText story = null;
    AccesData accesData = null;
    TextView date = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_editing);

        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        story = findViewById(R.id.story);
        date = findViewById(R.id.date);
        accesData = new AccesData(this);

        Intent intent = getIntent();
        story.setText(intent.getStringExtra("story"));
        date.setText(intent.getStringExtra("date"));


    }

    public void save(View view){
        int id = getIntent().getIntExtra("id",-1);
        String story_1 = story.getText().toString();
        accesData.updateDiary(id,story_1);
        Toast t = Toast.makeText(this,"diary id edited",Toast.LENGTH_LONG);
        t.show();
        finish();
    }


}
