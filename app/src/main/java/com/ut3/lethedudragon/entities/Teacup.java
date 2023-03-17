package com.ut3.lethedudragon.entities;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;

import com.ut3.lethedudragon.R;

public class Teacup extends Entity {
    private double angle;
    private Context context;
    private CollideBox teaHitBox;

    public Teacup(double x, double y, Context context) {
        super((x - BitmapFactory.decodeResource(context.getResources(), R.drawable.main).getWidth()),
                y - BitmapFactory.decodeResource(context.getResources(), R.drawable.main).getHeight(),
                BitmapFactory.decodeResource(context.getResources(), R.drawable.main).getWidth(),
                BitmapFactory.decodeResource(context.getResources(), R.drawable.main).getHeight(),
                null);
        this.context = context;
        this.hitBox = new CollideBox(0, 0, width, height);
        this.teaHitBox = new CollideBox(width / 2 - 10, 0, 20, 20);

        this.bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.main);
        paint.setColor(Color.GREEN);
    }

    @Override
    public void update(double difficulty) {

    }

    public void setX(double x){
        this.x = x - BitmapFactory.decodeResource(context.getResources(), R.drawable.main).getWidth();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect((float) (x + teaHitBox.x), (float) (y + teaHitBox.y), (float) (x + teaHitBox.x + teaHitBox.width), (float) (y + teaHitBox.y + teaHitBox.height), paint);
        canvas.drawRect((float) (x + hitBox.x), (float) (y + hitBox.y), (float) (x + hitBox.x + hitBox.width), (float) (y + hitBox.y + hitBox.height), paint);
        canvas.drawBitmap(bmp, (float) x, (float) y, paint);
    }

    @Override
    public void collision(Entity entity) {
        if (entity instanceof  Leaf) {
            Leaf leaf = (Leaf) entity;
        }
    }
}
