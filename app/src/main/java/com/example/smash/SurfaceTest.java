package com.example.smash;

import android.os.Bundle;

import com.example.smash.components.FastRenderView;

import androidx.annotation.Nullable;

public class SurfaceTest extends FullScreenTest {
    FastRenderView renderView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.renderView = new FastRenderView(this);
        this.setContentView(this.renderView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.renderView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.renderView.pause();
    }
}
