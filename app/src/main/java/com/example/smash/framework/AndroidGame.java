package com.example.smash.framework;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.example.smash.components.GameRenderView;
import com.example.smash.framework.core.Audio;
import com.example.smash.framework.core.Constant;
import com.example.smash.framework.core.FileIO;
import com.example.smash.framework.core.Game;
import com.example.smash.framework.core.Graphics;
import com.example.smash.framework.core.Input;
import com.example.smash.framework.core.Screen;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AndroidGame extends AppCompatActivity implements Game {
    GameRenderView renderView;
    Graphics graphics;
    Audio audio;
    Input input;
    FileIO fileIO;
    Screen screen;

    private void setFullScreen() {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
    }

    private void setKeepScreenOn (boolean status) {
        if (status == true) {
            this.getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
            );
            return;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setFullScreen();
        this.setKeepScreenOn(Constant.SCREEN_ON);

        Bitmap frameBuffer = Bitmap.createBitmap(
                Constant.FRAME_BUFFER_WIDTH,
                Constant.FRAME_BUFFER_HEIGHT,
                Bitmap.Config.RGB_565
        );

        Log.d("abc", "width: " + this.getResources()
                .getDisplayMetrics()
                .widthPixels + " ,height: " + this.getResources()
                .getDisplayMetrics()
                .heightPixels);

        float scaleX = (float) Constant.FRAME_BUFFER_WIDTH
                / this.getResources()
                .getDisplayMetrics()
                .widthPixels;
        float scaleY = (float) Constant.FRAME_BUFFER_HEIGHT
                / this.getResources()
                .getDisplayMetrics()
                .heightPixels;

        this.renderView = new GameRenderView(
                this,
                frameBuffer
        );
        this.graphics = new com.example.smash.framework.impl.Graphics(
                this.getAssets(),
                frameBuffer
        );
        this.input = new com.example.smash.framework.impl.Input(
                this.renderView,
                scaleX,
                scaleY
        );

        this.fileIO = new com.example.smash.framework.impl.FileIO(
                this
        );
        this.audio = new com.example.smash.framework.impl.Audio(
                this
        );

        this.screen = this.getStartScreen();
        this.setContentView(this.renderView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.screen.resume();
        this.renderView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.renderView.pause();
        this.screen.pause();

        if (this.isFinishing()) {
            this.screen.dispose();
        }
    }

    @Override
    public Input getInput() {
        return this.input;
    }

    @Override
    public FileIO getFileIO() {
        return this.fileIO;
    }

    @Override
    public Graphics getGraphics() {
        return this.graphics;
    }

    @Override
    public Audio getAudio() {
        return this.audio;
    }

    @Override
    public void setScreen(Screen screen) {
        if (this.screen == null) {
            throw new IllegalArgumentException("Screen must not be null");
        }
        this.screen.pause();
        this.screen.dispose();
        screen.resume();
        screen.update(0);
        this.screen = screen;
    }

    @Override
    public Screen getCurrentScreen() {
        return this.screen;
    }

    @Override
    public Screen getStartScreen() {
        return null;
    }
}
