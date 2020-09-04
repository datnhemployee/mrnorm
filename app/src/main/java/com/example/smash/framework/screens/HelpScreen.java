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

public class HelpScreen extends Screen {
    public static final int BUTTON_NEXT_X = Constant.BACKGROUND_WIDTH -
            Constant.BUTTON_WIDTH;
    public static final int BUTTON_NEXT_Y = Constant.BACKGROUND_HEIGHT -
            Constant.BUTTON_HEIGHT;

    public static final int BACKGROUND_X = 0;
    public static final int BACKGROUND_Y = 0;

    public static final int HELP_X = Math.round((
            Constant.BACKGROUND_WIDTH - Constant.HELP_WIDTH) / 2);
    public static final int HELP_Y = Constant.GAP;

    public HelpScreen(Game game) {
        super(game);
    }

    public Pixmap getHelpAsset () {
        return Assets.help1;
    }

    public Screen getNextScreen () {
        return new HelpScreen2(this.game);
    }

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = this.game
                .getInput()
                .getTouchEvents();
        this.game.getInput().getKeyEvents();

        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if (event.type == Input.TouchEvent.TOUCH_UP) {
                if (event.x > HelpScreen.BUTTON_NEXT_X &&
                    event.y > HelpScreen.BUTTON_NEXT_Y) {
                    this.game.setScreen(this.getNextScreen());
                    if (Settings.soundEnabled) {
                        Assets.click.play(
                                Constant.SOUND_VOLUM_MAX
                        );
                        return;
                    }
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics graphics = this.game.getGraphics();
        graphics.drawPixmap(
                Assets.background,
                HelpScreen.BACKGROUND_X,
                HelpScreen.BACKGROUND_Y
        );
        graphics.drawPixmap(
                this.getHelpAsset(),
                HelpScreen.HELP_X,
                HelpScreen.HELP_Y
        );
        graphics.drawPixmap(
                Assets.buttons,
                HelpScreen.BUTTON_NEXT_X,
                HelpScreen.BUTTON_NEXT_Y,
                Constant.BUTTON_NEXT_SRC_X,
                Constant.BUTTON_NEXT_SRC_Y,
                Constant.BUTTON_WIDTH,
                Constant.BUTTON_HEIGHT
        );
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
