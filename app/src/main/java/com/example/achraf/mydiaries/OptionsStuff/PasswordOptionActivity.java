package com.example.achraf.mydiaries.OptionsStuff;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.achraf.mydiaries.DB.AccesDataOption;
import com.example.achraf.mydiaries.R;

public class PasswordOptionActivity extends AppCompatActivity {

    private EditText o1,o2,o3,o4;
    private EditText n1,n2,n3,n4;
    private AccesDataOption accesDataOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_option);

        accesDataOption = new AccesDataOption(this);

        o1 = findViewById(R.id.old_1);
        o2 = findViewById(R.id.old_2);
        o3 = findViewById(R.id.old_3);
        o4 = findViewById(R.id.old_4);

        n1 = findViewById(R.id.new_1);
        n2 = findViewById(R.id.new_2);
        n3 = findViewById(R.id.new_3);
        n4 = findViewById(R.id.new_4);

        //accesDataOption.insertOption("password","0000");
        Log.d("dd",accesDataOption.getOption("password"));
        //accesDataOption.updateOption("password","1111");
        Log.d("dd",accesDataOption.getOption("password"));




    }



    public void change(View view){
        if(o1.getText().toString().equals("") ||
                o2.getText().toString().equals("") ||
                o3.getText().toString().equals("") ||
                o4.getText().toString().equals("")
                ){
            Toast t = Toast.makeText(this,"please type your old password correctly",Toast.LENGTH_LONG);
            t.show();
        }else if(n1.getText().toString().equals("") ||
                n2.getText().toString().equals("") ||
                n3.getText().toString().equals("") ||
                n4.getText().toString().equals("") ){
            Toast t = Toast.makeText(this,"please type your new password correctly",Toast.LENGTH_LONG);
            t.show();
        }else{
            String old_password = o1.getText().toString() +
                    o2.getText().toString() + o3.getText().toString() + o4.getText().toString();
            String new_password = n1.getText().toString() +
                    n2.getText().toString() + n3.getText().toString() + n4.getText().toString();
            Log.d("dd","new password requested is :"+new_password);

            if(new_password.equals("0000")){
                Toast t = Toast.makeText(this,"Week password 0000 chose another ;) ",Toast.LENGTH_LONG);
                t.show();
                freeCases();
                return;
            }

            if(!old_password.equals(accesDataOption.getOption("password"))){
                Toast t = Toast.makeText(this,"Wrong old password, please repeat !",Toast.LENGTH_LONG);
                t.show();
            }else{
                accesDataOption.updateOption("password",new_password);
                Toast t = Toast.makeText(this,"you just changed your password :)",Toast.LENGTH_LONG);
                t.show();
            }
        }
        freeCases();
    }
    public void freeCases(){
        o1.setText("");
        o2.setText("");
        o3.setText("");
        o4.setText("");
        n1.setText("");
        n2.setText("");
        n3.setText("");
        n4.setText("");

    }
}
