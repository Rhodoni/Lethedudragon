package com.ut3.lethedudragon.game;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.ut3.lethedudragon.entities.Leaf;
import com.ut3.lethedudragon.entities.Teacup;

import java.util.ArrayList;
import java.util.List;

import com.ut3.lethedudragon.viewholder.Opening;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private Context context;
    private GameThread thread;
    private int width,height;
    private CaptorActivity captorActivity;

    private SharedPreferences sharedPreferences;

    private double difficulty = 1;
    private int score = 0;

    private List<Leaf> leaves = new ArrayList<Leaf>();
    private Teacup teacup;

    public GameView(Context context) {
        super(context);
        this.context = context;
        thread = new GameThread(getHolder(), this);
        getHolder().addCallback(this);

        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;

        this.captorActivity = new CaptorActivity();
        this.captorActivity.setUpSensors(this.context);
        initialiseGame();
    }

    private void initialiseGame() {
        teacup = new Teacup(width/2, height/2);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        thread.setRunning(false);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            canvas.drawColor(Color.WHITE);
            leaves.forEach(leaf -> leaf.draw(canvas));
            teacup.draw(canvas);
        }
    }

    public void update(){
        if (Math.random() < 0.01) {
            createLeafs();
        }

        // Update
        teacup.update(difficulty);
        leaves.forEach(leaf -> leaf.update(difficulty));

        cleanEntities();
    }

    public void endGame(){
        //thread.setRunning(false);
        int tmpScore;
        int tmpPlace = 0;
        boolean isHighScore = false;

        SharedPreferences sharedp = context.getSharedPreferences("gameEnd",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedp.edit();
        for(int i = 4;i<=0;i--){
            tmpScore =  sharedp.getInt("score"+i,0);
            if(score>tmpScore){
                isHighScore = true;
                tmpPlace = i;
            }
        }
        if (isHighScore){
            editor.putInt("score"+tmpPlace,score).apply();
        }

        // ((Opening)context).endingGame();
    }

    private void createLeafs() {
        if (Math.random() < 0.1 || leaves.size() < 2) {
            leaves.add(new Leaf(Math.random() * width, height / 2));
        }
    }

    private void updateDifficulty() {
        // Difficulté est dépendante du score
        //double difficulty = (double) score / (score + 1000) * 20;
    }

    private void cleanEntities() {
        leaves.removeIf(leaf -> leaf.getY() > height);
    }
}
