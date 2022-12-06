package com.example.colortiles;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Random;

public class MyView  extends View {
    float x = 100;
    float y = 100;

    float height;
    float width;

    float tileHeight;
    float tileWight;
    int darkColor;
    int brightColor;

    final int TILE_COUNT = 5;

    public MyView(Context context) {
        super(context);
        Resources r = getResources();
        darkColor = r.getColor(R.color.dark);
        brightColor = r.getColor(R.color.bright);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = getWidth();
        height = getHeight();

        tileHeight = (float) (Math.sqrt((width * height) / 16));
        tileWight = (float) (Math.sqrt((width * height) / 16));
//sqrt((width*height)/n)
        Random r = new Random();
        Paint paint = new Paint();
        paint.setColor(darkColor);
        for(int i = 0;i<TILE_COUNT;i++) {
            for (int j = 0; j < TILE_COUNT; j++) {
                if (r.nextBoolean()) {
                    paint.setColor(brightColor);
                }
                canvas.drawRect(i*tileWight,j*tileHeight,(i + 1)*tileWight - 3,(j + 1)*tileHeight - 3, paint);
                paint.setColor(darkColor);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = event.getX();
        y = event.getY();
        invalidate();
        return super.onTouchEvent(event);

    }
}
