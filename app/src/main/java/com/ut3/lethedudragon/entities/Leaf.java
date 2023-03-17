package com.ut3.lethedudragon.entities;

import android.graphics.Color;

public class Leaf extends Entity {
    private double speedY = 5;
    private double speedX = 0;

    public Leaf(double x, double y) {
        super(x, y, 200, 200, null);

        paint.setColor(Color.GREEN);
    }

    @Override
    public void update(double difficulty) {
        updatePosition(difficulty);
    }

    private void updatePosition(double difficulty) {
        y += speedY * difficulty;
        x += speedX * difficulty;
    }

    @Override
    public void collision(Entity entity) {

    }
}
