package com.example.smash.framework.screens;

import com.example.smash.framework.core.Assets;
import com.example.smash.framework.core.Game;
import com.example.smash.framework.core.Pixmap;
import com.example.smash.framework.core.Screen;

public class HelpScreen3 extends HelpScreen {

    public HelpScreen3(Game game) {
        super(game);
    }

    @Override
    public Pixmap getHelpAsset() {
        return Assets.help3;
    }

    @Override
    public Screen getNextScreen() {
        return new MainMenuScreen(this.game);
    }
}
