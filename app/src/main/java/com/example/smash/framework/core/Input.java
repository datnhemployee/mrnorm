package com.example.smash.framework.core;

import java.util.List;

public interface Input {
    // Luckily for us,
    // the android.view.KeyEvent.KEYCODE_XXX constants
    // (which encode the key codes)
    // are all between 0 and 127
    public static final int MAX_NUM_KEY_CODES = 127;
    public static final int MIN_NUM_KEY_CODES = 0;

    public class KeyEvent {
        public static final int KEY_DOWN = 0;
        public static final int KEY_UP = 1;

        public int type;
        public int keyCode;
        public char keyChar;
    }

    public static class TouchEvent {
        public static final int TOUCH_DOWN = 0;
        public static final int TOUCH_UP = 1;
        public static final int TOUCH_DRAGGED = 2;

        public int type;
        public int x, y;
        public int pointer;
    }

    public boolean isKeyPressed(int keyCode);
    public boolean isTouchDown(int pointer);

    public int getTouchX(int pointer);
    public int getTouchY(int pointer);

    public List<KeyEvent> getKeyEvents();
    public List<TouchEvent> getTouchEvents();
}
