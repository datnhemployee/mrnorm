package com.example.smash.framework.impl;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.smash.framework.core.Input;
import com.example.smash.framework.core.PoolObjectFactory;
import com.example.smash.framework.core.TouchHandler;

import java.util.ArrayList;
import java.util.List;

public class SingleTouchHandler implements TouchHandler {
    public static final int MAX_NUM_TOUCH = 100;
    public static final int TOUCH_EVENT_FLOOD_BARRIER = 16;

    boolean isTouched;
    int touchX;
    int touchY;
    Pool<Input.TouchEvent> touchEventPool;
    List<Input.TouchEvent> touchEvents = new ArrayList<Input.TouchEvent>();
    List<Input.TouchEvent> touchEventsBuffer = new ArrayList<Input.TouchEvent>();
    float scaleX;
    float scaleY;

    public SingleTouchHandler(
            View view,
            float scaleX,
            float scaleY) {
        PoolObjectFactory<Input.TouchEvent> factory = new PoolObjectFactory<Input.TouchEvent>() {
            @Override
            public Input.TouchEvent createObject() {
                return new Input.TouchEvent();
            }
        };
        this.touchEventPool = new Pool<Input.TouchEvent>(
                factory,
                SingleTouchHandler.MAX_NUM_TOUCH
                );
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        view.setOnTouchListener(this);
    }

    @Override
    public boolean isTouchDown(int pointer) {
        synchronized (this) {
            if (pointer == 0) {
                return this.isTouched;
            }
            return false;
        }
    }

    @Override
    public int getTouchX(int pointer) {
        synchronized (this) {
            return this.touchX;
        }
    }

    @Override
    public int getTouchY(int pointer) {
        synchronized (this) {
            return this.touchY;
        }
    }

    @Override
    public List<Input.TouchEvent> getTouchEvents() {
        synchronized (this) {
            int len = this.touchEvents.size();
            for ( int i = 0; i < len; i++) {
                this.touchEventPool.free(this.touchEvents.get(i));
            }
            this.touchEvents.clear();
            this.touchEvents.addAll(this.touchEventsBuffer);
            this.touchEventsBuffer.clear();
            return this.touchEvents;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        synchronized (this) {
            try {
                Thread.sleep(this.TOUCH_EVENT_FLOOD_BARRIER);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Input.TouchEvent touchEvent = this.touchEventPool.newObject();
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    touchEvent.type = Input.TouchEvent.TOUCH_DOWN;
                    this.isTouched = true;
                    break;
                case MotionEvent.ACTION_MOVE:
                    touchEvent.type = Input.TouchEvent.TOUCH_DRAGGED;
                    this.isTouched = true;
                    break;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    touchEvent.type = Input.TouchEvent.TOUCH_UP;
                    this.isTouched = false;
                    break;
            }
            touchEvent.x = this.touchX = (int) (event.getX() * this.scaleX);
            touchEvent.y = this.touchY = (int) (event.getY() * this.scaleY);
            this.touchEventsBuffer.add(touchEvent);
            return true;
        }
    }
}
