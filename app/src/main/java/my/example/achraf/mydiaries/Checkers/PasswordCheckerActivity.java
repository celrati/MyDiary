package my.example.achraf.mydiaries.Checkers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import my.example.achraf.mydiaries.DB.AccesDataOption;
import my.example.achraf.mydiaries.DiaryPlayer;
import my.example.achraf.mydiaries.OptionsStuff.OptionsActivity;
import my.example.achraf.mydiaries.R;

public class PasswordCheckerActivity extends AppCompatActivity {

    private Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b0;
    private EditText t1,t2,t3,t4;
    private AccesDataOption accesDataOption;

    private String toRedirect = "";
    private String password = "";

    private TextView passwordToShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_checker);

        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        b4 = findViewById(R.id.b4);
        b5 = findViewById(R.id.b5);
        b6 = findViewById(R.id.b6);
        b7 = findViewById(R.id.b7);
        b8 = findViewById(R.id.b8);
        b9 = findViewById(R.id.b9);
        b0 = findViewById(R.id.b0);


        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        t3 = findViewById(R.id.t3);
        t4 = findViewById(R.id.t4);

        accesDataOption = new AccesDataOption(this);
        //accesDataOption.updateOption("password","1111");




        Intent intent = getIntent();
        toRedirect = intent.getStringExtra("toRedirect");


    }


    public void reset(View view) {
        password = "";
        t1.setText("*");
        t2.setText("*");t3.setText("*");t4.setText("*");
    }

    public void go(View view) {
        if(password.equals(accesDataOption.getOption("password"))){

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
        }else{
            password = "";
            Toast t = Toast.makeText(this,"WRONG PASSWORD !!",Toast.LENGTH_LONG);
            t.show();
            t1.setText("*");
            t2.setText("*");t3.setText("*");t4.setText("*");
        }
    }

    public void update(View view) {
        if(password.length() >= 4){
            Toast t = Toast.makeText(this,"4 CHARS IS THE LIMIT !!",Toast.LENGTH_LONG);
            t.show();
            password = "";
            t1.setText("*");
            t2.setText("*");t3.setText("*");t4.setText("*");
            return;

        }
        if(view == b1){
            password += "1";
        }
        if(view == b2) password += "2";
        if(view == b3) password += "3";
        if(view == b4) password += "4";
        if(view == b5) password += "5";
        if(view == b6) password += "6";
        if(view == b7) password += "7";
        if(view == b8) password += "8";
        if(view == b9) password += "9";
        if(view == b0) password += "0";


        if(password.length() == 1){
            t1.setText(password.charAt(0)+"");
            t2.setText("*");
            t3.setText("*");
            t4.setText("*");


        }
        if(password.length() == 2){
            t1.setText(password.charAt(0)+"");
            t2.setText(password.charAt(1)+"");
            t3.setText("*");
            t4.setText("*");
        }
        if(password.length() == 3){
            t1.setText(password.charAt(0)+"");
            t2.setText(password.charAt(1)+"");
            t3.setText(password.charAt(2)+"");
            t4.setText("*");
        }
        if(password.length() == 4){
            t1.setText(password.charAt(0)+"");
            t2.setText(password.charAt(1)+"");
            t3.setText(password.charAt(2)+"");
            t4.setText(password.charAt(3)+"");

        }




    }
}
