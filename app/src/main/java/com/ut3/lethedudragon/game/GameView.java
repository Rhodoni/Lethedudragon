package com.ut3.lethedudragon.game;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaRecorder;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.ut3.lethedudragon.entities.Chrono;
import com.ut3.lethedudragon.entities.Fire;
import com.ut3.lethedudragon.entities.Leaf;
import com.ut3.lethedudragon.entities.Sablier;
import com.ut3.lethedudragon.entities.Teacup;
import com.ut3.lethedudragon.viewholder.Opening;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private Context context;
    private GameThread thread;
    private int width, height;
    private CaptorActivity captorActivity;
    private long lastTime;

    private double difficulty = 1;
    private int score = 0;
    private int stopwatch = 30;
    private MediaRecorder _recorder; // recording object

    double pointX;

    private List<Leaf> leaves = new ArrayList<Leaf>();
    private Teacup teacup;
    private Chrono chrono;
    private Fire laDaronneAJerem;
    private Sablier sablier;

    public GameView(Context context) {
        super(context);
        this.context = context;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            _recorder = new MediaRecorder(context);
        }
        thread = new GameThread(getHolder(), this);
        getHolder().addCallback(this);

        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        width = displayMetrics.widthPixels;
        pointX = width * 0.5;
        height = displayMetrics.heightPixels;

        this.captorActivity = new CaptorActivity();
        this.captorActivity.setUpSensors(this.context);
        initialiseGame();

    }

    private void initialiseGame() {
        teacup = new Teacup(width / 2, height / 2, context);
        chrono = new Chrono(width / 2 - 120, height * 0.10, context);
        sablier = new Sablier(width / 2 - 120, height * 0.03, context);
        teacup = new Teacup(pointX, height, context);
        laDaronneAJerem = new Fire(width / 2, height, context);
        startRecorder();
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

            chrono.draw(canvas);
            sablier.draw(canvas);
            leaves.forEach(leaf -> leaf.draw(canvas));
            teacup.draw(canvas);
            if(laDaronneAJerem.getBmp()!=null){
                laDaronneAJerem.draw(canvas);
            }
        }

        // Draw score
        Paint paint = new Paint();
        paint.setTextSize(50);
        paint.setColor(Color.GREEN);
        canvas.drawText(String.valueOf(score), width / 2, (float) (height * 0.125), paint);
        paint.setColor(Color.RED);
        canvas.drawText(String.valueOf(stopwatch), width / 2, (float) (height * 0.05), paint);
    }

    private void updateTime() {
        long currentTime = System.currentTimeMillis();

        if (currentTime - lastTime > 1000) {
            score += 1;
            stopwatch -= 1;
            lastTime = currentTime;
        }
    }

    private void testEndGame() {
        if (stopwatch == 0) {
            endGame();
        }
    }

    public void update() {
        if (Math.random() < 0.03) {
            createLeafs();
        }

        long currentTime = System.currentTimeMillis();

        if (currentTime - lastTime > 1000) {
            score += 1;
            lastTime = currentTime;
        }
        leaves.forEach(leaf -> leaf.update(2));
        updateTime();
        testEndGame();
        teacup.moveBottom(pointX);
        if (getEnergy()) {
            laDaronneAJerem.throwflames();
        }
        // Update
        teacup.update(difficulty);
        leaves.forEach(leaf -> leaf.update(difficulty));

        cleanEntities();
    }

    public void endGame() {
        int tmpScore = 0;
        int tmpPlace = 0;
        boolean isHighScore = false;

        thread.setRunning(false);


        SharedPreferences sharedp = context.getSharedPreferences("gameEnd", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedp.edit();
        for (int i = 4; i <= 0; i--) {
            tmpScore = sharedp.getInt("score" + i, 0);
            if (score > tmpScore) {
                isHighScore = true;
                tmpPlace = i;
            }
        }
        if (isHighScore) {
            tmpScore = sharedp.getInt(("score" + tmpPlace), 0);
            editor.putInt(("score" + tmpPlace), score).apply();

            for (int i = tmpPlace + 1; i < 4; i++) {
                editor.putInt(("score" + i), tmpScore).apply();
                tmpScore = sharedp.getInt(("score" + i), 0);
            }
        }

        ((Opening) context).endingGame();
    }

    private void createLeafs() {
        if (Math.random() < 0.1 || leaves.size() < 2) {
            leaves.add(new Leaf(Math.random() * width, 0, context));
        }
    }

    private void updateDifficulty() {
        // Difficulté est dépendante du score
        double difficulty = (double) score / (score + 1000) * 20;
    }

    private void cleanEntities() {
        leaves.removeIf(leaf -> leaf.getY() > height);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        pointX = event.getX();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    private void startRecorder() {
        _recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        _recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        _recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        _recorder.setOutputFile(context.getCacheDir().getAbsolutePath() + "/audio.3gp");
        try {
            _recorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        _recorder.start();
    }

    private boolean getEnergy() {
        return _recorder.getMaxAmplitude() > 25000;
    }

}
