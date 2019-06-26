package my.example.achraf.mydiaries.OptionsStuff;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import my.example.achraf.mydiaries.DB.AccesDataOption;
import my.example.achraf.mydiaries.DiaryPlayer;
import my.example.achraf.mydiaries.MainActivity;
import my.example.achraf.mydiaries.R;

public class PasswordOptionActivity extends AppCompatActivity {

    private RadioButton onPass,offPass;
    private EditText password;
    private AccesDataOption accesDataOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_option);

        accesDataOption = new AccesDataOption(this);

        onPass = findViewById(R.id.onPass);
        offPass = findViewById(R.id.offPass);
        password = findViewById(R.id.password);

        //accesDataOption.insertOption("password","0000");
        Log.d("dd",accesDataOption.getOption("password"));
        //accesDataOption.updateOption("password","1111");
        Log.d("dd",accesDataOption.getOption("password"));


    }



    public void change(View view){
        if(onPass.isChecked()){
            // on password
            accesDataOption.updateOption("secure","ON");
            String newPassword = password.getText().toString();
            accesDataOption.updateOption("password",newPassword);

            Log.d("ach",accesDataOption.getOption("password"));

            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);

            Toast t = Toast.makeText(this,"you just changed your password :)",Toast.LENGTH_LONG);
            t.show();


        }else if(offPass.isChecked()){
            // off password
            accesDataOption.updateOption("secure","OFF");
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            Toast t = Toast.makeText(this,"you disable the secure mode",Toast.LENGTH_LONG);
            t.show();
        }

    }

}
