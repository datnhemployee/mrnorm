package com.example.smash;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class KeyTest extends AppCompatActivity implements View.OnKeyListener {
    StringBuilder builder = new StringBuilder();
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.textView = new TextView(this);
        this.textView.setText("Press keys (if you have some)!");
        this.textView.setOnKeyListener(this);
        this.textView.setFocusableInTouchMode(true);
        this.textView.requestFocus();
        setContentView(this.textView);
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        this.builder.setLength(0);
        switch (event.getAction()){
            case  KeyEvent.ACTION_DOWN:
                this.builder.append("down, ");
                break;
            case KeyEvent.ACTION_UP:
                this.builder.append("up, ");
                break;
        }
        this.builder.append(event.getKeyCode());
        this.builder.append(", ");
        this.builder.append((char) event.getUnicodeChar());

        String text = builder.toString();
        Log.d("KeyTest", text);
        this.textView.setText(text);

        return event.getKeyCode() != KeyEvent.KEYCODE_BACK;

        // | Book) Page 113)
        // If we were to return true in the case of the Back key, we’d mess with the activity life cycle a little.
        // The activity would not be closed, as we decided to consume the Back key ourselves.
//        return true;
    }
}
