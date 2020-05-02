package com.example.finalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class PostActivity extends AppCompatActivity {

    private Button postbtn;
    private EditText sport;
    private EditText desc;


    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    private String user_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        postbtn = (Button) findViewById(R.id.postblogbtn);
        sport = (EditText) findViewById(R.id.sport);
        desc = (EditText) findViewById(R.id.desc);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        user_id = firebaseAuth.getCurrentUser().getUid();

        postbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Desc = desc.getText().toString();
                String Sport = sport.getText().toString();

                if(!TextUtils.isEmpty(Desc) && !TextUtils.isEmpty(Sport)){

                    final String random = UUID.randomUUID().toString();

                    Map<String, Object> m = new HashMap<>();
                    m.put("desc", Desc);
                    m.put("sport", Sport);
                    m.put("timestamp", random);

                    firebaseFirestore.collection("Posts").add(m).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {

                            if(task.isSuccessful()){
                                Toast.makeText(PostActivity.this, "Post was added", Toast.LENGTH_LONG).show();
                                Intent main = new Intent(PostActivity.this, MainActivity.class);
                                startActivity(main);
                                finish();
                            }else{


                                Toast.makeText(PostActivity.this, "Error made while adding post", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });

    }


}
