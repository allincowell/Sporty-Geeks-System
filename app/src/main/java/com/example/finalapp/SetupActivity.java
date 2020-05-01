package com.example.finalapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SetupActivity extends AppCompatActivity {

    private EditText name;
    private EditText hall;
    private Button savebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        name = (EditText) findViewById(R.id.nameSetup);
        hall = (EditText) findViewById(R.id.hall_setup);
        savebtn = (Button) findViewById(R.id.saveSetupbtn);

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(SetupActivity.this, MainActivity.class);
                startActivity(main);
                finish();
            }
        });


    }
}
