package com.ut3.lethedudragon.entities;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;

import com.ut3.lethedudragon.R;

public class Teacup extends Entity {
    private double angle = -0.1;
    private double acceleration = 0.0;
    private double gravity = 0.01;
    private double rotationAcceleration = 0;
    private double rotationSpeed;
    private double maxRotationSpeed = 5;
    private double temperature;
    private int nbLeaves = 0;
    private double stickSize;
    private Point pivot;

    private Context context;
    private CollideBox teaHitBox;
    private CollideBox stickBox;

    public Teacup(double x, double y, Context context) {
        super(x, y,
                BitmapFactory.decodeResource(context.getResources(), R.drawable.main).getWidth(),
                BitmapFactory.decodeResource(context.getResources(), R.drawable.main).getHeight(),
                BitmapFactory.decodeResource(context.getResources(), R.drawable.main));
        this.context = context;
        this.hitBox = new CollideBox(width / 4, 0, width / 2, 100);
        this.teaHitBox = new CollideBox(width / 2 - 10, 0, 20, 20);

        this.pivot = new Point(width / 2, height * 4 / 5);

        paint.setColor(Color.GREEN);
    }

    @Override
    public void update(double difficulty) {
        updateRotationAcceleration();
        updateRotationSpeed();
        updateAngle(difficulty);
    }

    private void updateAngle(double difficulty) {
        angle = (angle + rotationSpeed * difficulty) % 45;
    }

    private void updateRotationSpeed() {
        rotationSpeed = Math.max(Math.min(rotationSpeed + rotationAcceleration, maxRotationSpeed), -maxRotationSpeed);
    }

    private void updateRotationAcceleration() {
        rotationAcceleration = angle * gravity + acceleration;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        canvas.rotate((float) angle, (float) (x + pivot.x), (float) (y + pivot.y));
        canvas.drawRect((float) (x + teaHitBox.x), (float) (y + teaHitBox.y), (float) (x + teaHitBox.x + teaHitBox.width), (float) (y + teaHitBox.y + teaHitBox.height), paint);
        canvas.drawRect((float) (x + hitBox.x), (float) (y + hitBox.y), (float) (x + hitBox.x + hitBox.width), (float) (y + hitBox.y + hitBox.height), paint);
        canvas.drawBitmap(bmp, (float) x, (float) y, paint);

        canvas.restore();
    }

    public void checkCollision(Leaf leaf) {
        if (x + teaHitBox.x < leaf.x + leaf.hitBox.x + leaf.hitBox.width &&
                x + teaHitBox.x + teaHitBox.width > leaf.x + leaf.hitBox.x &&
                y + teaHitBox.y < leaf.y + leaf.hitBox.y + leaf.hitBox.height &&
                y + teaHitBox.y + teaHitBox.height > leaf.y + leaf.hitBox.y)
        {
            collision(leaf);
        }
    }

    public void collision(Leaf leaf) {
        nbLeaves++;
    }

    public void heat() {

    }

    public void moveBottom(double x) {

        // TODO Move Top
    }

    public void moveTop(double force) {

    }

    public void fall() {

    }

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    private Point pointRotation(Point point, double angle) {
        double x = point.x;
        double y = point.y;
        return new Point(x, y);
    }
}
