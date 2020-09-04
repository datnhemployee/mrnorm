package com.example.smash.components;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.smash.framework.AndroidGame;
import com.example.smash.framework.core.Constant;

public class GameRenderView extends SurfaceView implements Runnable {

    AndroidGame game;
    Bitmap frameBuffer;
    Thread renderThread = null;
    SurfaceHolder holder;
    volatile boolean running = false;

    public GameRenderView(
            AndroidGame game,
            Bitmap frameBuffer) {
        super(game);
        this.game = game;
        this.frameBuffer = frameBuffer;
        this.holder = this.getHolder();
    }

    public void resume() {
        this.running = true;
        this.renderThread = new Thread(this);
        this.renderThread.start();
    }

    @Override
    public void run() {
        Rect dstRect = new Rect();
        long startTime = System.nanoTime();
        while (this.running) {
            if (!this.holder.getSurface().isValid()) {
                continue;
            }

            float deltaTime = (System.nanoTime()-startTime) /
                    Constant.SECOND_NANO;
            startTime = System.nanoTime();

            this.game.getCurrentScreen().update(deltaTime);
            this.game.getCurrentScreen().present(deltaTime);

            Canvas canvas = this.holder.lockCanvas();
            canvas.getClipBounds(dstRect);

//            Log.d("frameBuffer",
//                    "frameBuffer#height: "+ frameBuffer.getHeight() +
//                            "width: "+ frameBuffer.getWidth());
//            Log.d("dstRect",
//                    "dstRect#top: "+ dstRect.top +
//                    "left: "+ dstRect.left +
//                    "right: "+ dstRect.right+
//                    "bottom: "+ dstRect.bottom);

            canvas.drawBitmap(
                    this.frameBuffer,
                    null,
                    dstRect,
                    null);
            this.holder.unlockCanvasAndPost(canvas);
        }
    }

    public void pause() {
        this.running=false;
        while (true) {
            try {
                this.renderThread.join();
                return;
            } catch (InterruptedException e) {
                // retry
            }
        }
    }
}
