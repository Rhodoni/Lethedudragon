package com.ut3.lethedudragon.viewholder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import com.ut3.lethedudragon.R;
import com.ut3.lethedudragon.game.GameView;

public class Opening extends AppCompatActivity {

    private Button btnGame;
    private Button btnScore;

    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.opening);

        btnGame = (Button)findViewById(R.id.btnGame);
        btnGame.setOnClickListener(view -> {
            setContentView(gameView = new GameView(this));
        });
        btnScore = (Button)findViewById(R.id.btnScore);
        btnScore.setOnClickListener(view -> {
            Intent scoreIntent = new Intent(view.getContext(), Score.class);
            startActivity(scoreIntent);
        });
    }
}