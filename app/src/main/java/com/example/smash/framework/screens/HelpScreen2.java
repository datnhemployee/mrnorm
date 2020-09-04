package com.example.smash.framework.screens;

import com.example.smash.framework.core.Assets;
import com.example.smash.framework.core.Constant;
import com.example.smash.framework.core.Game;
import com.example.smash.framework.core.Graphics;
import com.example.smash.framework.core.Input;
import com.example.smash.framework.core.Pixmap;
import com.example.smash.framework.core.Screen;
import com.example.smash.framework.core.Settings;

import java.util.List;

public class HelpScreen2 extends HelpScreen {

    public HelpScreen2(Game game) {
        super(game);
    }

    @Override
    public Pixmap getHelpAsset() {
        return Assets.help2;
    }

    @Override
    public Screen getNextScreen() {
        return new HelpScreen3(this.game);
    }
}
