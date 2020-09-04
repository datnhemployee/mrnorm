package com.example.smash;


import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LifeCycleTest extends AppCompatActivity {
    StringBuilder builder = new StringBuilder();
    TextView textView;

    private void log(String text) {
        Log.d("LifeCycleTest", text);
        this.builder.append(text);
        this.builder.append("\n");
        this.textView.setText(this.builder.toString());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.textView = new TextView(this);
        this.textView.setText(this.builder.toString());
        setContentView(this.textView);
        this.log("created");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        this.log("resumed");
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.log("paused");

        if (isFinishing()) {
            log("finishing");
        }
    }
}
