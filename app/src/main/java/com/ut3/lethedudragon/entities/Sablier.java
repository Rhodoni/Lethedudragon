package com.ut3.lethedudragon.entities;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.ut3.lethedudragon.R;

public class Sablier extends Entity{


    public Sablier(double x, double y, Context context) {
        super(x, y, BitmapFactory.decodeResource(context.getResources(), R.drawable.sablier).getWidth(),
                BitmapFactory.decodeResource(context.getResources(), R.drawable.sablier).getHeight(),
                BitmapFactory.decodeResource(context.getResources(), R.drawable.sablier));
    }

    @Override
    public void update(double difficulty) {

    }
}
