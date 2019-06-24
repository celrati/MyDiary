package my.example.achraf.mydiaries;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import my.example.achraf.mydiaries.DB.AccesData;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NewDiarieActivity extends AppCompatActivity {

    EditText story = null;
    TextView date = null;
    AccesData accesData = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_diarie);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        date = findViewById(R.id.date);
        story = (EditText)findViewById(R.id.story);
        accesData = new AccesData(this);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd/MM/yyyy");
        String dateX = mdformat.format(calendar.getTime());

        date.setText(getFormatDate(dateX));
    }







    public void save(View view){
        String story_1 = story.getText().toString();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd/MM/yyyy");
        String date = mdformat.format(calendar.getTime());

        accesData.insertDiary(story_1,date);
        Toast t = Toast.makeText(this,"new Diary added :)",Toast.LENGTH_LONG);
        t.show();
        //startActivity(new Intent(this,DiaryPlayer.class));
        finish();
    }

    public String getFormatDate(String date){
        String[] date_1 = date.split("/");
        String month = "";
        switch(date_1[1]){
            case "01":
                month = "January";
                break;
            case "02":
                month = "February";
                break;
            case "03":
                month = "March";
                break;
            case "04":
                month = "April";
                break;
            case "05":
                month = "May";
                break;
            case "06":
                month = "June";
                break;
            case "07":
                month = "July";
                break;
            case "08":
                month = "August";
                break;
            case "09":
                month = "September";
                break;
            case "10":
                month = "October";
                break;
            case "11":
                month = "November";
                break;
            case "12":
                month = "December";
                break;
        }
        String retDate = Integer.parseInt(date_1[0])+"th "+month+" "+date_1[2];

        return retDate;
    }


}
