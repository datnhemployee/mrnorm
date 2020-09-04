package com.example.smash.framework.screens;

import com.example.smash.framework.core.Assets;
import com.example.smash.framework.core.Constant;
import com.example.smash.framework.core.Game;
import com.example.smash.framework.core.Graphics;
import com.example.smash.framework.core.Screen;
import com.example.smash.framework.core.Settings;

public class LoadingScreen extends Screen {
    public LoadingScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {

        Graphics graphics = this.game.getGraphics();
        Assets.background = graphics.newPixmap(
                Constant.DEFAULT_IMAGE_PATH +
                Constant.PATH_IMAGE_BACKGROUND,
                Graphics.PixmapFormat.RGB565
        );
        Assets.logo = graphics.newPixmap(
                Constant.DEFAULT_IMAGE_PATH +
                Constant.PATH_IMAGE_LOGO,
                Graphics.PixmapFormat.ARGB4444
        );
        Assets.mainMenu = graphics.newPixmap(
                Constant.DEFAULT_IMAGE_PATH +
                        Constant.PATH_IMAGE_MAIN_MENU,
                Graphics.PixmapFormat.ARGB4444
        );
        Assets.buttons = graphics.newPixmap(
                Constant.DEFAULT_IMAGE_PATH +
                        Constant.PATH_IMAGE_BUTTONS,
                Graphics.PixmapFormat.ARGB4444
        );
        Assets.help1 = graphics.newPixmap(
                Constant.DEFAULT_IMAGE_PATH +
                        Constant.PATH_IMAGE_HELP1,
                Graphics.PixmapFormat.ARGB4444
        );
        Assets.help2 = graphics.newPixmap(
                Constant.DEFAULT_IMAGE_PATH +
                        Constant.PATH_IMAGE_HELP2,
                Graphics.PixmapFormat.ARGB4444
        );
        Assets.help3 = graphics.newPixmap(
                Constant.DEFAULT_IMAGE_PATH +
                        Constant.PATH_IMAGE_HELP3,
                Graphics.PixmapFormat.ARGB4444
        );
        Assets.numbers = graphics.newPixmap(
                Constant.DEFAULT_IMAGE_PATH +
                        Constant.PATH_IMAGE_NUMBERS,
                Graphics.PixmapFormat.ARGB4444
        );
        Assets.ready = graphics.newPixmap(
                Constant.DEFAULT_IMAGE_PATH +
                        Constant.PATH_IMAGE_READY,
                Graphics.PixmapFormat.ARGB4444
        );
        Assets.pause = graphics.newPixmap(
                Constant.DEFAULT_IMAGE_PATH +
                        Constant.PATH_IMAGE_PAUSE,
                Graphics.PixmapFormat.ARGB4444
        );
        Assets.gameOver = graphics.newPixmap(
                Constant.DEFAULT_IMAGE_PATH +
                        Constant.PATH_IMAGE_GAME_OVER,
                Graphics.PixmapFormat.ARGB4444
        );
        Assets.headUp = graphics.newPixmap(
                Constant.DEFAULT_IMAGE_PATH +
                        Constant.PATH_IMAGE_HEAD_UP,
                Graphics.PixmapFormat.ARGB4444
        );
        Assets.headDown = graphics.newPixmap(
                Constant.DEFAULT_IMAGE_PATH +
                        Constant.PATH_IMAGE_HEAD_DOWN,
                Graphics.PixmapFormat.ARGB4444
        );
        Assets.headLeft = graphics.newPixmap(
                Constant.DEFAULT_IMAGE_PATH +
                        Constant.PATH_IMAGE_HEAD_LEFT,
                Graphics.PixmapFormat.ARGB4444
        );
        Assets.headRight = graphics.newPixmap(
                Constant.DEFAULT_IMAGE_PATH +
                        Constant.PATH_IMAGE_HEAD_RIGHT,
                Graphics.PixmapFormat.ARGB4444
        );
        Assets.tail = graphics.newPixmap(
                Constant.DEFAULT_IMAGE_PATH +
                        Constant.PATH_IMAGE_TAIL,
                Graphics.PixmapFormat.ARGB4444
        );
        Assets.stain1 = graphics.newPixmap(
                Constant.DEFAULT_IMAGE_PATH +
                        Constant.PATH_IMAGE_STAIN1,
                Graphics.PixmapFormat.ARGB4444
        );
        Assets.stain2 = graphics.newPixmap(
                Constant.DEFAULT_IMAGE_PATH +
                        Constant.PATH_IMAGE_STAIN2,
                Graphics.PixmapFormat.ARGB4444
        );
        Assets.stain3 = graphics.newPixmap(
                Constant.DEFAULT_IMAGE_PATH +
                        Constant.PATH_IMAGE_STAIN3,
                Graphics.PixmapFormat.ARGB4444
        );

        Assets.click = this.game.getAudio().newSound(
                Constant.DEFAULT_SOUND_PATH +
                        Constant.PATH_SOUND_CLICK
        );
        Assets.eat = this.game.getAudio().newSound(
                Constant.DEFAULT_SOUND_PATH +
                        Constant.PATH_SOUND_EAT
        );
        Assets.bitten = this.game.getAudio().newSound(
                Constant.DEFAULT_SOUND_PATH +
                        Constant.PATH_SOUND_BITTEN
        );
        Settings.load(this.game.getFileIO());
        this.game.setScreen(new MainMenuScreen(this.game));
    }

    @Override
    public void present(float deltaTime) {

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
