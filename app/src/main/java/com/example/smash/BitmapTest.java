package com.example.smash;

import android.os.Bundle;

import com.example.smash.components.BitmapRenderView;

import androidx.annotation.Nullable;

public class BitmapTest extends FullScreenTest {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(new BitmapRenderView(this));
    }
}
