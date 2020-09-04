package com.example.smash;

import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SingleTouchTest
        extends AppCompatActivity
        implements View.OnTouchListener {
    StringBuilder builder = new StringBuilder();
    TextView textView;

    int direction;
    Point pointTouchStart = new Point();

    public static final int TOUCH_EVENT_FLOOD_BARRIER = 16;

    public interface Movement {
        public static final int FORWARD = 1;
        public static final int BACKWARD = 2;
        public static final int LEFT = 4;
        public static final int RIGHT = 8;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.textView = new TextView(this);
        this.textView.setText("Touch and drag (one finger only)!");
        this.textView.setOnTouchListener(this);

        setContentView(textView);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        try {
            Thread.sleep(this.TOUCH_EVENT_FLOOD_BARRIER);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.builder.setLength(0);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                this.pointTouchStart.set(
                        (int) event.getX(),
                        (int) event.getY());
                this.builder.append("down, ");
                break;
            case MotionEvent.ACTION_MOVE:
                this.builder.append("move, ");
                break;
            case MotionEvent.ACTION_CANCEL:
                this.builder.append("cancel, ");
                break;
            case MotionEvent.ACTION_UP:
                int x2 = (int) event.getX();
                int y2 = (int) event.getY();
                int deltaY = y2 - this.pointTouchStart.y;
                int deltaX = x2 - this.pointTouchStart.x;

                if (Math.abs(deltaX) <= Math.abs(deltaY)) {
                    if (deltaY >= 0){
                        this.direction = Movement.BACKWARD;
                    } else {
                        this.direction = Movement.FORWARD;
                    }
                } else {
                    if (deltaX >= 0){
                        this.direction = Movement.RIGHT;
                    } else {
                        this.direction = Movement.LEFT;
                    }
                }
                this.builder.append("up, ");
                break;
        }
        this.builder.append(event.getX());
        this.builder.append(", ");
        this.builder.append(event.getY());
        this.builder.append(", ");

        String text = builder.toString();
        if (this.direction == Movement.BACKWARD){
            text += "BACKWARD.";
        } else if (this.direction == Movement.FORWARD){
            text += "FORWARD.";
        } else if (this.direction == Movement.LEFT){
            text += "LEFT.";
        } else {
            text += "RIGHT.";
        }

        Log.d("TouchTest", text);
        this.textView.setText(text);

        return true;
    }

}
