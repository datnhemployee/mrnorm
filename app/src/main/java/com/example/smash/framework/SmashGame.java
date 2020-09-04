package com.example.smash.framework;

import com.example.smash.framework.core.Screen;
import com.example.smash.framework.screens.SplashScreen;

public class SmashGame extends AndroidGame {

    public Screen getStartScreen() {
        return new SplashScreen(this);
    }
}
