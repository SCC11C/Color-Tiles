package com.example.colortiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.nfc.Tag;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int darkColor;
    int brightColor;
    TextView touchCounter;
    View[][] tiles = new View[4][4];
    long touches = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setContentView(new MyView(this));
        Resources r = getResources();
        darkColor = r.getColor(R.color.dark);
        brightColor = r.getColor(R.color.bright);
        touchCounter = findViewById(R.id.count_text);
        tiles[0][0] = findViewById(R.id.t00);
        tiles[0][1] = findViewById(R.id.t01);
        tiles[0][2] = findViewById(R.id.t02);
        tiles[0][3] = findViewById(R.id.t03);
        tiles[1][0] = findViewById(R.id.t10);
        tiles[1][1] = findViewById(R.id.t11);
        tiles[1][2] = findViewById(R.id.t12);
        tiles[1][3] = findViewById(R.id.t13);
        tiles[2][0] = findViewById(R.id.t20);
        tiles[2][1] = findViewById(R.id.t21);
        tiles[2][2] = findViewById(R.id.t22);
        tiles[2][3] = findViewById(R.id.t23);
        tiles[3][0] = findViewById(R.id.t30);
        tiles[3][1] = findViewById(R.id.t31);
        tiles[3][2] = findViewById(R.id.t32);
        tiles[3][3] = findViewById(R.id.t33);
        randomColors();
    }

    private boolean isGameWin() {
        int darkTiles = 0;
        int brightTiles = 0;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                ColorDrawable background =  (ColorDrawable) tiles[i][j].getBackground();
                if (background.getColor() == darkColor) {
                    darkTiles++;
                }
                if (background.getColor() == brightColor) {
                    brightTiles++;
                }
            }
        }
        return darkTiles == 16 || brightTiles == 16;
    }

    private void randomColors() {
        Random random = new Random();
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                if (random.nextBoolean()) {
                    changeColor(tiles[i][j]);
                }
            }
        }
    }

    public void changeColor(View v) {
        ColorDrawable d = (ColorDrawable) v.getBackground();
        if (d.getColor() == brightColor) {
            v.setBackgroundColor(darkColor);
        } else {
            v.setBackgroundColor(brightColor);
        }
    }
    public void onClick(View v) {
        if (isGameWin()) {
            Toast.makeText(this, "Игра выиграна!", Toast.LENGTH_SHORT).show();
            return;
        }
        touches++;
        touchCounter.setText(String.format(Locale.getDefault(),"Кол-во нажатий: %d", touches));
        // получаем тэг на кнопке
        String tag = v.getTag().toString();
        int x = Integer.parseInt(tag.substring(0,1));
        int y = Integer.parseInt(tag.substring(1)); // координаты тайла и строки вида "00"
        // изменить цвет на самом тайле и всех тайлах
        // с таким же x и таким же y
        changeColor(v);
        for (int i = 0; i < 4; i++) {
            changeColor(tiles[x][i]);
            changeColor(tiles[i][y]);
        }

    }


    public void restartGame(View view) {
        touches = 0;
        touchCounter.setText(String.format(Locale.getDefault(),"Кол-во нажатий: %d", touches));
        randomColors();
    }
}
