package com.example.smash.framework.impl;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import java.io.IOException;

public class Music
        implements com.example.smash.framework.core.Music,
        MediaPlayer.OnCompletionListener {
    MediaPlayer mediaPlayer;
    boolean isPrepared = false;

    public  Music(AssetFileDescriptor assetFileDescriptor) {
        this.mediaPlayer = new MediaPlayer();
        try {
            this.mediaPlayer.setDataSource(
                    assetFileDescriptor.getFileDescriptor(),
                    assetFileDescriptor.getStartOffset(),
                    assetFileDescriptor.getLength()
            );

            this.mediaPlayer.prepare();
            this.isPrepared = true;
            this.mediaPlayer.setOnCompletionListener(this);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load music");
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        synchronized (this) {
            this.isPrepared = false;
        }
    }

    @Override
    public void play() {
        if (this.mediaPlayer.isPlaying()) {
            return;
        }
        try {
          synchronized (this) {
              if (!this.isPrepared) {
                  this.mediaPlayer.prepare();
              }
              this.mediaPlayer.start();
          }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        this.mediaPlayer.stop();
        synchronized (this) {
            this.isPrepared = false;
        }
    }

    @Override
    public void pause() {
        if (this.mediaPlayer.isPlaying()) {
            this.mediaPlayer.pause();
        }
    }

    @Override
    public void setLooping(boolean looping) {
        this.mediaPlayer.setLooping(looping);
    }

    @Override
    public void setVolume(float volume) {
        this.mediaPlayer.setVolume(volume, volume);
    }

    @Override
    public boolean isStopped() {
        return !this.isPrepared;
    }

    @Override
    public boolean isLooping() {
        return this.mediaPlayer.isLooping();
    }

    @Override
    public boolean isPlaying() {
        return this.mediaPlayer.isPlaying();
    }

    @Override
    public void dispose() {
        if (this.mediaPlayer.isPlaying()) {
            this.mediaPlayer.stop();
        }
        this.mediaPlayer.release();
    }
}
