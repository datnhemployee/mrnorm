package com.example.smash.components;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class FastRenderView
        extends SurfaceView
        implements Runnable {
    Thread renderThread = null;
    SurfaceHolder holder;
    volatile boolean running = false;

    public FastRenderView(Context context) {
        super(context);
        this.holder = this.getHolder();
    }

    public void resume () {
        this.running = true;
        this.renderThread = new Thread(this);
        this.renderThread.start();
    }

    @Override
    public void run() {
        while(this.running) {
            boolean isCreated = this.holder.getSurface().isValid();
            if (!isCreated) {
                continue;
            }
            Canvas canvas = this.holder.lockCanvas();
            canvas.drawRGB(255,0,0);
            this.holder.unlockCanvasAndPost(canvas);
        }
    }

    public void pause (){
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
