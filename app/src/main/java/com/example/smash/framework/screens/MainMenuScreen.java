package com.example.smash.framework.screens;

import com.example.smash.framework.core.Assets;
import com.example.smash.framework.core.Constant;
import com.example.smash.framework.core.Game;
import com.example.smash.framework.core.Graphics;
import com.example.smash.framework.core.Input;
import com.example.smash.framework.core.Screen;
import com.example.smash.framework.core.Settings;

import java.util.List;

public class MainMenuScreen extends Screen {


    public static final int BACKGROUND_X = 0;
    public static final int BACKGROUND_Y = 0;
    public static final int LOGO_X =
            Math.round((Constant.FRAME_BUFFER_WIDTH - Constant.LOGO_WIDTH) / 2);
    public static final int LOGO_Y = Constant.GAP;
    public static final int MAIN_MENU_X =
            Math.round((Constant.FRAME_BUFFER_WIDTH - Constant.MAIN_MENU_WIDTH) / 2);
    public static final int MAIN_MENU_Y =
            LOGO_Y + Constant.LOGO_HEIGHT + Constant.GAP;
    public static final int BUTTON_SOUND_X = Constant.GAP;
    public static final int BUTTON_SOUND_Y =
            Constant.FRAME_BUFFER_HEIGHT - Constant.BUTTON_HEIGHT;

    public static final int BUTTON_PLAY_X = MAIN_MENU_X;
    public static final int BUTTON_PLAY_Y = MAIN_MENU_Y;

    public static final int BUTTON_HIGH_SCORE_X = MainMenuScreen.BUTTON_PLAY_X;
    public static final int BUTTON_HIGH_SCORE_Y = MainMenuScreen.BUTTON_PLAY_Y +
            Constant.BUTTON_MAIN_MENU_HEIGHT;

    public static final int BUTTON_HELP_X = MainMenuScreen.BUTTON_PLAY_X;
    public static final int BUTTON_HELP_Y = MainMenuScreen.BUTTON_HIGH_SCORE_Y +
            Constant.BUTTON_MAIN_MENU_HEIGHT;

    public static final int BUTTON_SOUND_UNMUTE_SRC_Y = 0;
    public static final int BUTTON_SOUND_UNMUTE_SRC_X = 0;

    public static final int BUTTON_SOUND_MUTE_SRC_Y = 0;
    public static final int BUTTON_SOUND_MUTE_SRC_X = Constant.BUTTON_WIDTH;

    public MainMenuScreen(Game game) {
        super(game);
    }

    private boolean isTouchedSoundButton (
            Input.TouchEvent event,
            Graphics graphics
    ) {
        int x = 0;
        int width = Constant.BUTTON_WIDTH;
        int height = Constant.BUTTON_WIDTH;
        int y = graphics.getHeight() - height;

        boolean result = this.inBounds(
                event,
                x,
                y,
                width,
                height
        );
        return result;
    }

    private boolean isTouchedPlayButton (
            Input.TouchEvent event
    ) {
        int width = Constant.BUTTON_MAIN_MENU_WIDTH;
        int height = Constant.BUTTON_MAIN_MENU_HEIGHT;
        int x = MainMenuScreen.BUTTON_PLAY_X;
        int y = MainMenuScreen.BUTTON_PLAY_Y;

        boolean result = this.inBounds(
                event,
                x,
                y,
                width,
                height
        );
        return result;
    }

    private boolean isTouchedHighScoreButton (
            Input.TouchEvent event
    ) {
        int width = Constant.BUTTON_MAIN_MENU_WIDTH;
        int height = Constant.BUTTON_MAIN_MENU_HEIGHT;
        int x = MainMenuScreen.BUTTON_HIGH_SCORE_X;
        int y = MainMenuScreen.BUTTON_HIGH_SCORE_Y;

        boolean result = this.inBounds(
                event,
                x,
                y,
                width,
                height
        );
        return result;
    }

    private boolean isTouchedHelpButton (
            Input.TouchEvent event
    ) {
        int width = Constant.BUTTON_MAIN_MENU_WIDTH;
        int height = Constant.BUTTON_MAIN_MENU_HEIGHT;
        int x = MainMenuScreen.BUTTON_HELP_X;
        int y = MainMenuScreen.BUTTON_HELP_Y;

        boolean result = this.inBounds(
                event,
                x,
                y,
                width,
                height
        );
        return result;
    }

    private void playSoundButton() {
        if (Settings.soundEnabled) {
            Assets.click.play(
                    Constant.SOUND_VOLUM_MAX
            );
        }
    }

    @Override
    public void update(float deltaTime) {
        Graphics graphics = this.game.getGraphics();
        List<Input.TouchEvent> touchEvents = this.game.getInput().getTouchEvents();
        this.game.getInput().getKeyEvents();

        int len = touchEvents.size();

        for (int i = 0;i< len;i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if (event.type == Input.TouchEvent.TOUCH_UP) {
                if (this.isTouchedSoundButton(event, graphics)) {
                    Settings.soundEnabled = !Settings.soundEnabled;
                    this.playSoundButton();
                } else if (this.isTouchedPlayButton(event)) {
                    this.game.setScreen(new GameScreen(this.game));
                    this.playSoundButton();
                } else if (this.isTouchedHighScoreButton(event)) {
                    this.game.setScreen(new HighScoreScreen(this.game));
                    this.playSoundButton();
                } else if (this.isTouchedHelpButton(event)) {
                    this.game.setScreen(new HelpScreen(this.game));
                    this.playSoundButton();
                }
            }
        }
    }

    private boolean inBounds(
            Input.TouchEvent event,
            int x,
            int y,
            int width,
            int height
    ) {
        if (event.x > x && event.x < x + width - 1 &&
            event.y > y && event.y < y + height - 1) {
            return true;
        }
        return false;
    }

    @Override
    public void present(float deltaTime) {
        Graphics graphics = this.game.getGraphics();

        graphics.drawPixmap(
                Assets.background,
                MainMenuScreen.BACKGROUND_X,
                MainMenuScreen.BACKGROUND_Y);
        graphics.drawPixmap(
                Assets.logo,
                MainMenuScreen.LOGO_X,
                MainMenuScreen.LOGO_Y);
        graphics.drawPixmap(
                Assets.mainMenu,
                MainMenuScreen.MAIN_MENU_X,
                MainMenuScreen.MAIN_MENU_Y);
        if (Settings.soundEnabled) {
            graphics.drawPixmap(
                    Assets.buttons,
                    MainMenuScreen.BUTTON_SOUND_X,
                    MainMenuScreen.BUTTON_SOUND_Y,
                    MainMenuScreen.BUTTON_SOUND_UNMUTE_SRC_X,
                    MainMenuScreen.BUTTON_SOUND_UNMUTE_SRC_Y,
                    Constant.BUTTON_WIDTH,
                    Constant.BUTTON_HEIGHT
            );
        } else {
            graphics.drawPixmap(
                    Assets.buttons,
                    MainMenuScreen.BUTTON_SOUND_X,
                    MainMenuScreen.BUTTON_SOUND_Y,
                    MainMenuScreen.BUTTON_SOUND_MUTE_SRC_X,
                    MainMenuScreen.BUTTON_SOUND_MUTE_SRC_Y,
                    Constant.BUTTON_WIDTH,
                    Constant.BUTTON_HEIGHT
            );
        }
    }

    @Override
    public void pause() {
        Settings.save(this.game.getFileIO());
    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
