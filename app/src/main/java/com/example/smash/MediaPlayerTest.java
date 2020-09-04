package com.example.smash;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MediaPlayerTest extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    public static final String DEFAULT_PATH_MUSIC = "musics/";
    public static final String DEFAULT_BACKGROUND_MUSIC = "background_song.mp4";

    public static final boolean DEFAULT_BACKGROUND_LOOPING = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mediaPlayer = new MediaPlayer();
        TextView textView = new TextView(this);
        this.setContentView(textView);

        // Book) Page 133)
        // Remember to set the volume controls
        // to handle the music stream,
        // or else your players won’t be able
        // to adjust the volume of your game.
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        try {
            AssetManager manager = this.getAssets();
            AssetFileDescriptor descriptor = manager.openFd(
                    MediaPlayerTest.DEFAULT_PATH_MUSIC +
                            MediaPlayerTest.DEFAULT_BACKGROUND_MUSIC
            );
            this.mediaPlayer.setDataSource(
                    descriptor.getFileDescriptor(),
                    descriptor.getStartOffset(),
                    descriptor.getLength()
            );
            this.mediaPlayer.prepare();
            this.mediaPlayer.setLooping(
                    MediaPlayerTest.DEFAULT_BACKGROUND_LOOPING
            );
        } catch (IOException e) {
            textView.setText("Something went wrong! " + e.getMessage());
            this.mediaPlayer = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (this.mediaPlayer != null) {
            this.mediaPlayer.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (this.mediaPlayer !=null) {
            this.mediaPlayer.pause();
            if (this.isFinishing()) {
                this.mediaPlayer.stop();
                this.mediaPlayer.release();
            }
        }
    }
}
