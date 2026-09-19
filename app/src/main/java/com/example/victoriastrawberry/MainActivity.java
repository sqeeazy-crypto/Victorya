package com.example.victoriastrawberry;

import android.app.Activity;
import android.os.Bundle;
import android.view.*;
import android.widget.ImageView;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.content.res.Resources;

public class MainActivity extends Activity {
    private ImageView image;
    private boolean second = false;

    @Override public void onCreate(Bundle b) {
        super.onCreate(b);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

        image = new ImageView(this);
        image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        image.setBackgroundColor(Color.BLACK);
        image.setImageResource(getDrawableId("palm_screen"));
        setContentView(image);

        image.setOnClickListener(v -> {
            if (!second) {
                second = true;
                image.setImageResource(getDrawableId("artem_screen"));
            }
        });
    }

    private int getDrawableId(String name) {
        return getResources().getIdentifier(name, "drawable", getPackageName());
    }
}
