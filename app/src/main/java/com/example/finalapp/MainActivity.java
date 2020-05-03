package com.example.finalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button logoutbtn;
    private Button settingsbtn;
    private Button postbtn;
    private Button homeButton;

    private FirebaseAuth mAuth;
    private FirebaseFirestore firebaseFirestore;

    //private String current_user_id;

    List<String> sports_list;
    List<String> desc_list;

    String[] sports;
    String[] descriptions;

    ListView myListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        logoutbtn = (Button) findViewById(R.id.logoutbtn);
        settingsbtn = (Button) findViewById(R.id.settings_mainbtn);
        postbtn = (Button) findViewById(R.id.postbtn);
        homeButton = (Button) findViewById(R.id.homeButton);

        sports_list = new ArrayList<String>();
        desc_list = new ArrayList<String>();
        myListView = (ListView) findViewById(R.id.myListView);




        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseFirestore.collection("Posts").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){

                            for(DocumentSnapshot doc : task.getResult()){
                                if(doc.get("desc") != null);
                                    desc_list.add(doc.getString("desc"));
                                if(doc.get("sport") != null)
                                    sports_list.add(doc.getString("sport"));
                            }

                        }else{
                            String error = task.getException().getMessage();
                            Toast.makeText(MainActivity.this, "Error Message : " + error, Toast.LENGTH_LONG).show();
                        }
                    }
                });

                sports = sports_list.toArray(new String[0]);
                descriptions = desc_list.toArray(new String[0]);

                ItemAdapter itemAdapter = new ItemAdapter(MainActivity.this, sports, descriptions);
                myListView.setAdapter(itemAdapter);

            }
        });

        postbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent post = new Intent(MainActivity.this, PostActivity.class);
                startActivity(post);
            }
        });

        settingsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent settings = new Intent(MainActivity.this, SetupActivity.class);
                startActivity(settings);
                finish();
            }
        });

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAuth.signOut();
                Intent login = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(login);
                finish();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser == null){

            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
