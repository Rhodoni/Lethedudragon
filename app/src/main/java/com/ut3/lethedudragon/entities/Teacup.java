package com.ut3.lethedudragon.entities;

import android.graphics.Canvas;
import android.graphics.Color;

public class Teacup extends Entity {
    private double angle = 0.1;
    private double acceleration = 0.01;
    private double rotationSpeed;
    private double maxRotationSpeed = 10;
    private double temperature;
    private int nbLeaves = 0;
    private double stickSize;

    private CollideBox teaHitBox;
    private CollideBox stickBox;

    public Teacup(double x, double y) {
        super(x, y, 50, 200, null);

        this.hitBox = new CollideBox(0, 0, width, height);
        this.teaHitBox = new CollideBox(width / 2 - 10, 0, 20, 20);

        paint.setColor(Color.BLUE);
    }

    @Override
    public void update(double difficulty) {
        updateRotationSpeed();
        updateAngle(difficulty);
    }

    private void updateAngle(double difficulty) {
        angle = (angle + rotationSpeed * difficulty) % 90;
    }

    private void updateRotationSpeed() {
        rotationSpeed = Math.min(rotationSpeed + angle * acceleration, maxRotationSpeed);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        canvas.rotate((float) angle, (float) x, (float) y);
        canvas.drawRect((float) (x + teaHitBox.x), (float) (y + teaHitBox.y), (float) (x + teaHitBox.x + teaHitBox.width), (float) (y + teaHitBox.y + teaHitBox.height), paint);
        canvas.drawRect((float) (x + hitBox.x), (float) (y + hitBox.y), (float) (x + hitBox.x + hitBox.width), (float) (y + hitBox.y + hitBox.height), paint);
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

    private Point pointRotation(Point point, double angle) {
        double x = point.x;
        double y = point.y;
        return new Point(x, y);
    }
}
