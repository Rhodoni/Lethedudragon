package com.ut3.lethedudragon.entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class Entity {
    protected double x;
    protected double y;
    protected double width;
    protected double height;
    protected CollideBox hitBox;
    protected Paint paint;
    protected Bitmap bmp;

    public Entity(double x, double y, double width, double height, Bitmap bmp) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.bmp = bmp;

        this.hitBox = new CollideBox(0, 0, width, height);

        paint = new Paint();
    }

    public abstract void update(double difficulty);

    public void draw(Canvas canvas) {
        canvas.drawRect((float) (x + hitBox.x), (float) (y + hitBox.y), (float) (x + hitBox.x + hitBox.width), (float) (y + hitBox.y + hitBox.height), paint);
        // canvas.drawBitmap(bmp, (float) x, (float) y, paint);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public CollideBox getHitBox() {
        return hitBox;
    }
}
