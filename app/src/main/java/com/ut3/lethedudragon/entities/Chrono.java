package com.ut3.lethedudragon.entities;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.ut3.lethedudragon.R;

public class Chrono extends Entity{


    public Chrono(double x, double y, Context context) {
        super(x, y, BitmapFactory.decodeResource(context.getResources(), R.drawable.clock).getWidth(),
                BitmapFactory.decodeResource(context.getResources(), R.drawable.clock).getHeight(),
                BitmapFactory.decodeResource(context.getResources(), R.drawable.clock));
    }

    @Override
    public void update(double difficulty) {

    }
}
