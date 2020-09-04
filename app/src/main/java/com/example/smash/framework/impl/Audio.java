package com.example.smash.framework.impl;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import com.example.smash.SoundPoolTest;
import com.example.smash.framework.impl.Music;
import com.example.smash.framework.impl.Sound;

import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;

public class Audio implements com.example.smash.framework.core.Audio {
    AssetManager assetManager;
    SoundPool soundPool;

    public static final int DEFAULT_SOUND_CONCURRENT_SOUND = 20;
    public static final int DEFAULT_SOUND_SOURCE_QUALITY = 0;

    public static final int DEFAULT_SOUND_PRIORITY = 0;

    public Audio(AppCompatActivity activity) {
        activity.setVolumeControlStream(
                AudioManager.STREAM_MUSIC
        );
        this.assetManager = activity.getAssets();
        this.soundPool = new SoundPool(
                Audio.DEFAULT_SOUND_CONCURRENT_SOUND,
                AudioManager.STREAM_MUSIC,
                Audio.DEFAULT_SOUND_SOURCE_QUALITY);
    }

    @Override
    public Music newMusic(String filename) {
        try {
            AssetFileDescriptor descriptor = this.assetManager.openFd(filename);
            return new Music(descriptor);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load music '"+filename+"'");
        }
    }

    @Override
    public Sound newSound(String filename) {
        try {
            AssetFileDescriptor assetFileDescriptor = assetManager.openFd(filename);
            int soundId = soundPool.load(
                    assetFileDescriptor,
                    Audio.DEFAULT_SOUND_PRIORITY);
            return new Sound(soundPool,soundId);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load sound '"+filename+"'");
        }
    }
}
