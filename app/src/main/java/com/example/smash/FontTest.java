package com.example.smash;

import android.os.Bundle;

import com.example.smash.components.TextRenderView;

import androidx.annotation.Nullable;

public class FontTest extends FullScreenTest {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(new TextRenderView(this));
    }
}
