package com.example.smash;

import android.os.Bundle;
import androidx.annotation.Nullable;

import com.example.smash.components.RenderView;

public class RenderViewTest extends FullScreenTest {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new RenderView(this));
    }


}
