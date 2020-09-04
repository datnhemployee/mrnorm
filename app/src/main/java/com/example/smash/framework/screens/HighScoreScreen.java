package com.example.smash.framework.screens;

import com.example.smash.framework.core.Assets;
import com.example.smash.framework.core.Constant;
import com.example.smash.framework.core.Game;
import com.example.smash.framework.core.Graphics;
import com.example.smash.framework.core.Input;
import com.example.smash.framework.core.Screen;
import com.example.smash.framework.core.Settings;

import java.util.List;

public class HighScoreScreen extends Screen {
    String lines[] = new String[
            Constant.NUM_HIGH_SCORES
            ];

    public static final int BUTTON_BACK_X = 0;
    public static final int BUTTON_BACK_Y =
            Constant.BACKGROUND_HEIGHT - Constant.BUTTON_HEIGHT;

    public static final int HIGH_SCORE_X_START = Constant.GAP;
    public static final int HIGH_SCORE_Y_START = 150;

    public static final int BACKGROUND_X = 0;
    public static final int BACKGROUND_Y = 0;

    public HighScoreScreen(Game game) {
        super(game);

        for (int i=0;i < Constant.NUM_HIGH_SCORES;i++) {
            this.lines[i] = "" + (i+1) + ". " + Settings.highscores[i];
        }
    }

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = this.game.getInput().getTouchEvents();

        int len = touchEvents.size();
        for (int i=0;i<len;i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if (event.type == Input.TouchEvent.TOUCH_UP) {
                if (event.x < Constant.BUTTON_WIDTH &&
                    event.y > HighScoreScreen.BUTTON_BACK_Y) {
                    if (Settings.soundEnabled) {
                        Assets.click.play(Constant.SOUND_VOLUM_MAX);
                    }
                    this.game.setScreen(new MainMenuScreen(this.game));
                    return;
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics graphics = this.game.getGraphics();

        graphics.drawPixmap(
                Assets.background,
                HighScoreScreen.BACKGROUND_X,
                HighScoreScreen.BACKGROUND_Y
                );

        int y = HighScoreScreen.HIGH_SCORE_Y_START;
        for (int i = 0;i < Constant.NUM_HIGH_SCORES;i++) {
            this.drawText(
                    graphics,
                    lines[i],
                    HighScoreScreen.HIGH_SCORE_X_START,
                    y
            );
            y += Constant.IMAGE_NUMBER_HEIGHT + Constant.GAP;
        }
        graphics.drawPixmap(
                Assets.buttons,
                HighScoreScreen.BUTTON_BACK_X,
                HighScoreScreen.BUTTON_BACK_Y,
                Constant.BUTTON_BACK_SRC_X,
                Constant.BUTTON_BACK_SRC_Y,
                Constant.BUTTON_WIDTH,
                Constant.BUTTON_HEIGHT
        );
    }

    private void drawText(
            Graphics graphics,
            String line,
            int x,
            int y
    ) {
        int len = line.length();
        for (int i = 0;i < len;i++) {
            char character = line.charAt(i);

            if (character == ' ') {
                x += Constant.IMAGE_NUMBER_WIDTH;
                continue;
            }

            int srcX = 0;
            int srcWidth = 0;

            if (character == '.') {
                srcX = Constant.IMAGE_DOT_SRC_X;
                srcWidth = Constant.IMAGE_DOT_WIDTH;
            } else {
                srcX = (character - '0') * Constant.IMAGE_NUMBER_WIDTH;
                srcWidth = Constant.IMAGE_NUMBER_WIDTH;
            }

            graphics.drawPixmap(
                    Assets.numbers,
                    x,
                    y,
                    srcX,
                    0,
                    srcWidth,
                    Constant.IMAGE_NUMBER_HEIGHT
            );

            x += srcWidth;
        }
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
