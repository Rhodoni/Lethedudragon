package com.ut3.lethedudragon.entities;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import com.ut3.lethedudragon.R;

public class Leaf extends Entity {
    private double speedY = 10;
    private double speedX = 0;

    public boolean isCatched = false;

    public Leaf(double x, double y, Context context) {
        super(x, y,
                BitmapFactory.decodeResource(context.getResources(), R.drawable.leaf).getWidth(),
                BitmapFactory.decodeResource(context.getResources(), R.drawable.leaf).getHeight(),
                BitmapFactory.decodeResource(context.getResources(), R.drawable.leaf));
        paint.setColor(Color.GREEN);
    }

    @Override
    public void update(double difficulty) {
        updatePosition(difficulty);
    }

    public void catched() {
        this.isCatched = true;
    }

    private void updatePosition(double difficulty) {
        y += speedY * difficulty;
        x += speedX * difficulty;
    }
}
