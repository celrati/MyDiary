package com.example.achraf.mydiaries.Checkers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.achraf.mydiaries.DB.AccesDataOption;
import com.example.achraf.mydiaries.DiaryPlayer;
import com.example.achraf.mydiaries.OptionsStuff.OptionsActivity;
import com.example.achraf.mydiaries.R;

public class PasswordCheckerActivity extends AppCompatActivity {

    private EditText p1,p2,p3,p4;
    private AccesDataOption accesDataOption;

    private String toRedirect = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_checker);

        p1 = findViewById(R.id.new_1);
        p2 = findViewById(R.id.new_2);
        p3 = findViewById(R.id.new_3);
        p4 = findViewById(R.id.new_4);

        //accesDataOption = new AccesDataOption(this);
        //accesDataOption.updateOption("password","1111");


        Intent intent = getIntent();
        toRedirect = intent.getStringExtra("toRedirect");


        p1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                p1.setFocusable(false);
                p2.setSelection(0);
                p1.setFocusable(true);
            }

            @Override
            public void afterTextChanged(Editable s) {
                p1.setFocusable(false);
                p2.setSelection(0);
                p1.setFocusable(true);



            }
        });
        p2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                p2.setFocusable(false);
                p3.setSelection(0);
                p2.setFocusable(true);
            }

            @Override
            public void afterTextChanged(Editable s) {
                p2.setFocusable(false);
                p3.setSelection(0);
                p2.setFocusable(true);
            }
        });
        p3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                p3.setFocusable(false);
                p4.setSelection(0);
                p3.setFocusable(true);
            }

            @Override
            public void afterTextChanged(Editable s) {
                p3.setFocusable(false);
                p4.setSelection(0);
                p3.setFocusable(true);

            }
        });
        p4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                p4.setFocusable(false);
                p1.setSelection(0);
                p4.setFocusable(true);
            }

            @Override
            public void afterTextChanged(Editable s) {
                p4.setFocusable(false);
                p1.setSelection(0);
                p4.setFocusable(true);

            }
        });

    }
    public void check(View view){
        String passwordToCheck = p1.getText().toString() + p2.getText().toString() +
                p3.getText().toString() + p4.getText().toString();

        if(!accesDataOption.getOption("password").equals(passwordToCheck)){
           Toast t = Toast.makeText(this,"WRONG PASSWORD !!",Toast.LENGTH_LONG);
           freeCases();
           t.show();



           finish();
           return;
        }
        if(toRedirect.equals("diaryPlayer")){
            Intent intent = new Intent(this,DiaryPlayer.class);
            startActivity(intent);


            finish();
        }
        if(toRedirect.equals("options")){
            Intent intent = new Intent(this,OptionsActivity.class);
            startActivity(intent);

            finish();
        }


    }
    public void freeCases(){
        p1.setText("");
        p2.setText("");
        p4.setText("");
        p3.setText("");



    }
}
