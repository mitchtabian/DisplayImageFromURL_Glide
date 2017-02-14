package com.tabian.downloadimagefromurlglide;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    //declare variables
    private ImageView image;
    private SeekBar mSeekBar;

    private final static int mStartWidth = 50;
    private final static int mStartLength = 50;
    private int progressWidth;
    private int progressLength;
    private View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = (ImageView) findViewById(R.id.imageView);
        mSeekBar = (SeekBar) findViewById(R.id.seekBar);
        mView = this.findViewById(R.id.activity_main);
        progressLength = mStartLength;
        progressWidth = mStartWidth;

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.d(TAG, "onProgressChanged: Changing size of image to: " + i);

                if(i > 0){
                    progressLength =  i;
                    progressWidth = i;
                    getImage(mView);
                }

        }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    /**
     * Button click for getting image
     * @param view
     */
    public void getImage(View view) {
        Log.d(TAG, "getImage: Getting image from url.");
        Glide.with(MainActivity.this)
                .load("https://goo.gl/zFCwHD")
                .asBitmap()
                .into(new SimpleTarget<Bitmap>(progressWidth, progressLength) {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        BitmapDrawable bm = new BitmapDrawable(getResources(), resource);
                        image.setBackground(bm);
                    }
                });
    }
}












