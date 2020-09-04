package com.example.smash.framework.impl;

import android.view.View;

import com.example.smash.framework.core.TouchHandler;

import java.util.List;

import androidx.core.content.ContextCompat;

public class Input implements com.example.smash.framework.core.Input {
    KeyboardHandler keyboardHandler;
    TouchHandler touchHandler;

    public Input(
            View view,
            float scaleX,
            float scaleY
    ) {
        this.keyboardHandler = new KeyboardHandler(view);
        this.touchHandler = new SingleTouchHandler(view, scaleX, scaleY);
    }

    @Override
    public boolean isKeyPressed(int keyCode) {
        return this.keyboardHandler.isKeyPressed(keyCode);
    }

    @Override
    public boolean isTouchDown(int pointer) {
        return this.touchHandler.isTouchDown(pointer);
    }

    @Override
    public int getTouchX(int pointer) {
        return this.touchHandler.getTouchX(pointer);
    }

    @Override
    public int getTouchY(int pointer) {
        return this.touchHandler.getTouchY(pointer);
    }


    @Override
    public List<KeyEvent> getKeyEvents() {
        return this.keyboardHandler.getKeyEvents();
    }

    @Override
    public List<TouchEvent> getTouchEvents() {
        return this.touchHandler.getTouchEvents();
    }
}
