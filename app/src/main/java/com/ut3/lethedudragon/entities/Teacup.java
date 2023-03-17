package com.ut3.lethedudragon.entities;

import android.graphics.Canvas;
import android.graphics.Color;

public class Teacup extends Entity {
    private double angle;
    private CollideBox teaHitBox;

    public Teacup(double x, double y) {
        super(x, y, 50, 200, null);

        this.hitBox = new CollideBox(0, 0, width, height);
        this.teaHitBox = new CollideBox(width / 2 - 10, 0, 20, 20);

        paint.setColor(Color.BLUE);
    }

    @Override
    public void update(double difficulty) {

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect((float) (x + teaHitBox.x), (float) (y + teaHitBox.y), (float) (x + teaHitBox.x + teaHitBox.width), (float) (y + teaHitBox.y + teaHitBox.height), paint);
        canvas.drawRect((float) (x + hitBox.x), (float) (y + hitBox.y), (float) (x + hitBox.x + hitBox.width), (float) (y + hitBox.y + hitBox.height), paint);
    }

    @Override
    public void collision(Entity entity) {
        if (entity instanceof  Leaf) {
            Leaf leaf = (Leaf) entity;
        }
    }
}
