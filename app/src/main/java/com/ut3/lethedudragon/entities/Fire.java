package com.ut3.lethedudragon.entities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

import com.ut3.lethedudragon.R;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Fire extends Entity {
    private Context context;
    private boolean throwingFlames = false;
    private int gettingUp = 0;

    public Fire(double x, double y, Context context) {
        super(x - BitmapFactory.decodeResource(context.getResources(), R.drawable.ladaronneajerem).getWidth() / 2,
                y - BitmapFactory.decodeResource(context.getResources(), R.drawable.ladaronneajerem).getHeight(),
                BitmapFactory.decodeResource(context.getResources(), R.drawable.ladaronneajerem).getWidth(),
                BitmapFactory.decodeResource(context.getResources(), R.drawable.ladaronneajerem).getHeight(),
                null);

        this.context = context;
    }

    @Override
    public void update(double difficulty) {

    }

    public Bitmap getBmp() {
        return this.bmp;
    }

    public void throwflames() {
        if (throwingFlames == false) {
            Log.d("THROWING FLAMES", "throwflames ");
            throwingFlames = true;
            this.bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.ladaronneajerem);
            Executors.newSingleThreadScheduledExecutor()
                    .schedule(throwFlames, 2000, TimeUnit.MILLISECONDS);
        }

    }

    public Runnable throwFlames = new Runnable() {
        @Override
        public void run() {
            throwingFlames = false;
            bmp = null;
        }
    };
}
