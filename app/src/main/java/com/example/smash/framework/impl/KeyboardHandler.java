package com.example.smash.framework.impl;

import android.view.KeyEvent;
import android.view.View;

import com.example.smash.framework.core.Input;
import com.example.smash.framework.core.PoolObjectFactory;

import java.util.ArrayList;
import java.util.List;

public class KeyboardHandler implements View.OnKeyListener {
    public static final int KEY_EVENT_POOL_MAX_SIZE = 100;

    boolean[] pressedKeys = new boolean[Input.MAX_NUM_KEY_CODES + 1];
    Pool<Input.KeyEvent> keyEventPool;
    List<Input.KeyEvent> keyEventBuffer = new ArrayList<Input.KeyEvent>();
    List<Input.KeyEvent> keyEvents = new ArrayList<Input.KeyEvent>();

    public KeyboardHandler(View view) {
        PoolObjectFactory<Input.KeyEvent> factory = new PoolObjectFactory<Input.KeyEvent>() {
            @Override
            public Input.KeyEvent createObject() {
                return new Input.KeyEvent();
            }
        };

        this.keyEventPool = new Pool<Input.KeyEvent>(
                factory,
                KeyboardHandler.KEY_EVENT_POOL_MAX_SIZE
                );
        view.setOnKeyListener(this);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
    }

    @Override
    public boolean onKey(
            View v,
            int keyCode,
            KeyEvent event
    ) {
        if (event.getRepeatCount() > 1) {
            return false;
        }
        synchronized (this) {
            Input.KeyEvent keyEvent = this.keyEventPool.newObject();
            keyEvent.keyCode = keyCode;
            keyEvent.keyChar = (char) event.getUnicodeChar();

            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                keyEvent.type = Input.KeyEvent.KEY_DOWN;
                if (keyCode > Input.MIN_NUM_KEY_CODES &&
                    keyCode < Input.MAX_NUM_KEY_CODES) {
                    this.pressedKeys[keyCode] = true;
                }
            }
            if (event.getAction() == KeyEvent.ACTION_UP) {
                keyEvent.type = Input.KeyEvent.KEY_UP;
                if (keyCode > Input.MIN_NUM_KEY_CODES &&
                        keyCode < Input.MAX_NUM_KEY_CODES) {
                    this.pressedKeys[keyCode] = false;
                }
            }
            this.keyEventBuffer.add(keyEvent);
        }
        return false;
    }

    public boolean isKeyPressed(int keyCode) {
        if (keyCode < Input.MIN_NUM_KEY_CODES ||
                keyCode > Input.MAX_NUM_KEY_CODES) {
            return false;
        }
        return this.pressedKeys[keyCode];
    }

    public List<Input.KeyEvent> getKeyEvents() {
        synchronized (this) {
            int len = this.keyEvents.size();
            for (int i = 0;i < len;i++) {
                this.keyEventPool.free(this.keyEvents.get(i));
            }
            this.keyEvents.clear();
            this.keyEvents.addAll(this.keyEventBuffer);
            this.keyEventBuffer.clear();
            return this.keyEvents;
        }
    }
}
