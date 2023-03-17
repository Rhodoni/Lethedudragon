package com.ut3.lethedudragon.game;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.ut3.lethedudragon.viewholder.Opening;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private Context context;
    private GameThread thread;
    private int width,height;
    private CaptorActivity captorActivity;

    private SharedPreferences sharedPreferences;

    private int score;

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

    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }
    public void update(){

    }

    public void createEntity(){

    }

    public void cleanEntities(){

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
}
