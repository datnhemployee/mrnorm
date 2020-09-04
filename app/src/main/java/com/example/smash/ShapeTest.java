package com.example.smash;

import android.os.Bundle;

import com.example.smash.components.ShapeRenderView;

import androidx.annotation.Nullable;

public class ShapeTest extends FullScreenTest {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(new ShapeRenderView(this));
    }
}
