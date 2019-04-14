package com.example.projetdevmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

public class activity_save extends AppCompatActivity {
    private int score;
    private String nom;
    private Date date;
    private String dateToString;
    private TextView afficheScore;
    private EditText entrerNom;
    private MyDBAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);


        dbAdapter = new MyDBAdapter(this);

        Intent intent = getIntent();
        score = intent.getIntExtra("score", 0);

        afficheScore = (TextView)findViewById(R.id.lbScore);
        afficheScore.setText("Vous avez gagné en " + score + " coups");

        entrerNom = (EditText)findViewById(R.id.nom);


        Button retour=(Button)findViewById(R.id.menu);
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity_save.this,MainActivity.class);
                startActivity(intent);
            }
        });

        Button save=(Button)findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nom = entrerNom.getText().toString();
                date = new Date();
                dateToString = date.getDate()+"/"+date.getMonth()+"/"+date.getYear();

                dbAdapter.open();
                dbAdapter.insertScore(nom, score, dateToString);
                dbAdapter.close();

                Intent intent = new Intent(activity_save.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
