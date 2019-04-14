package com.example.projetdevmobile;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class activity_game extends AppCompatActivity {

    GameBoard board;
    GameView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        view = findViewById(R.id.gameview);
        view.setActivity(activity_game.this);
        board = view.getGameBoard();
    }
}
