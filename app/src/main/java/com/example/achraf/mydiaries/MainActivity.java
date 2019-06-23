package com.example.achraf.mydiaries;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.achraf.mydiaries.Checkers.PasswordCheckerActivity;
import com.example.achraf.mydiaries.DB.AccesData;
import com.example.achraf.mydiaries.DB.AccesDataOption;
import com.example.achraf.mydiaries.OptionsStuff.OptionsActivity;

public class MainActivity extends AppCompatActivity {

    private TextView welcome;
    private AccesDataOption accesDataOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accesDataOption = new AccesDataOption(this);
        welcome = findViewById(R.id.welcome);
        String welc = "Welcome dear "+accesDataOption.getOption("pseudo")+" to MyDiaries ...";
        welcome.setText(welc    );
        /*
            to test
         */


      //  startActivity(new Intent(this,NewDiarieActivity.class));
       AccesData accesData = new AccesData(this);
        //accesData.getDiaries();
        accesData.deleteAllDiaries();
        //accesData.deleteDiary(26);

    }

    public void open(View view){
        if(accesDataOption.getOption("secure").equals("OFF")){
            Intent intent = new Intent(this,DiaryPlayer.class);
            startActivity(intent);
        }else{
            Intent intent_password_checker = new Intent(this,PasswordCheckerActivity.class);
            intent_password_checker.putExtra("toRedirect","diaryPlayer");
            startActivity(intent_password_checker);
        }

    }


    public void getOption(View view){
        if(accesDataOption.getOption("secure").equals("OFF")){
            Intent intent = new Intent(this,OptionsActivity.class);
            startActivity(intent);
        }else{
            Intent intent_password_checker = new Intent(this,PasswordCheckerActivity.class);
            intent_password_checker.putExtra("toRedirect","options");
            startActivity(intent_password_checker);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        String welc = "Welcome dear " + accesDataOption.getOption("pseudo") + " to MyDiaries ...";
        welcome.setText(welc);
    }
}
