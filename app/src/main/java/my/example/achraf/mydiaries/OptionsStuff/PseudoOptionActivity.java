package my.example.achraf.mydiaries.OptionsStuff;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import my.example.achraf.mydiaries.DB.AccesDataOption;
import my.example.achraf.mydiaries.MainActivity;
import my.example.achraf.mydiaries.R;

public class PseudoOptionActivity extends AppCompatActivity {

    private EditText pseudo ;
    private AccesDataOption accesDataOption;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pseudo_option);

        pseudo = findViewById(R.id.pseudo);
        accesDataOption = new AccesDataOption(this);
        //accesDataOption.insertOption("pseudo","owner");
        pseudo.setText(accesDataOption.getOption("pseudo"));

    }

    public void change(View view){
        accesDataOption.updateOption("pseudo",pseudo.getText().toString());
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        Toast t = Toast.makeText(this,"you changed your pseudo now ..",Toast.LENGTH_LONG);
        t.show();
    }
}
