package com.example.smash.framework;

import com.example.smash.framework.core.Screen;
import com.example.smash.framework.screens.LoadingScreen;

public class MrNom extends AndroidGame {
    @Override
    public Screen getStartScreen() {
        return new LoadingScreen(this);
    }
}
