package com.example.smash;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SoundPoolTest extends AppCompatActivity implements View.OnTouchListener {
    public static final int DEFAULT_SOUND_ID = -1;
    public static final int DEFAULT_SOUND_CONCURRENT_SOUND = 20;
    public static final int DEFAULT_SOUND_SOURCE_QUALITY = 0;

    public static final String DEFAULT_EXPLOSION_FILE_NAME = "boom.mp4";

    public static final float DEFAULT_SOUND_VOLUMN_LEFT = 1.0f;
    public static final float DEFAULT_SOUND_VOLUMN_RIGHT = 1.0f;
    public static final int DEFAULT_SOUND_PRIORITY = 0;
    public static final int DEFAULT_SOUND_LOOP = 0;
    public static final int DEFAULT_SOUND_PLAYBACK_RATE = 1;

    SoundPool soundPool;
    int explosionId = SoundPoolTest.DEFAULT_SOUND_ID;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        textView.setOnTouchListener(this);
        this.setContentView(textView);

        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        this.soundPool = new SoundPool(
                SoundPoolTest.DEFAULT_SOUND_CONCURRENT_SOUND,
                AudioManager.STREAM_MUSIC,
                SoundPoolTest.DEFAULT_SOUND_SOURCE_QUALITY);

        try {
            AssetManager assetManager = this.getAssets();
            AssetFileDescriptor descriptor = assetManager.openFd(
                    "sounds/" + SoundPoolTest.DEFAULT_EXPLOSION_FILE_NAME);
            this.explosionId = this.soundPool.load(descriptor, 1);
        } catch (IOException e) {
            textView.setText("Couldn't load sound effect from asset, "
                + e.getMessage());
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (this.explosionId != -1) {
                this.soundPool.play(
                        this.explosionId,
                        SoundPoolTest.DEFAULT_SOUND_VOLUMN_LEFT,
                        SoundPoolTest.DEFAULT_SOUND_VOLUMN_RIGHT,
                        SoundPoolTest.DEFAULT_SOUND_PRIORITY,
                        SoundPoolTest.DEFAULT_SOUND_LOOP,
                        SoundPoolTest.DEFAULT_SOUND_PLAYBACK_RATE
                        );
            }
        }
        return true;
    }
}
