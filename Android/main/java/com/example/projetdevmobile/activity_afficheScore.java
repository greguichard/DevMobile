package com.example.projetdevmobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class activity_afficheScore extends AppCompatActivity {

    private MyDBAdapter dbAdapter;
    private ListView mListView;
    private MyArrayAdapter adapter;

    private ArrayList<ScoreData> scores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche_score);


        mListView = findViewById(R.id.listeScore);
        registerForContextMenu(mListView);

        dbAdapter = new MyDBAdapter(this);
        dbAdapter.open();

        scores = dbAdapter.getAllScores();

        adapter = new MyArrayAdapter(this, scores);
        mListView.setAdapter(adapter);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        ScoreData t = adapter.getItem(menuInfo.position);
        adapter.remove(t);
        dbAdapter.removeScore(t.getIdScore());

        return super.onContextItemSelected(item);
    }

    @Override
    public void onDestroy() {
        dbAdapter.close();
        super.onDestroy();
    }

}
