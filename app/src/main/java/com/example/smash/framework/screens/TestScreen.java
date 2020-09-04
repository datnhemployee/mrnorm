package com.example.smash.framework.screens;

import com.example.smash.framework.core.Game;
import com.example.smash.framework.core.Graphics;
import com.example.smash.framework.core.Pixmap;
import com.example.smash.framework.core.Screen;

public class TestScreen extends Screen {
    Pixmap pixmap;
    int x;

    public TestScreen(Game game) {
        super(game);
        this.pixmap = game.getGraphics()
                .newPixmap(
                        "data/pic.png",
                        Graphics.PixmapFormat.RGB565
                );
        this.game.getStartScreen();

    }

    @Override
    public void update(float deltaTime) {
        this.x += (float)(Screen.FPS * deltaTime);
        this.x = Math.round(this.x);
        if (this.x > Screen.MAX_PIXELS){
            this.x = 0;
        }
    }

    @Override
    public void present(float deltaTime) {
        this.game.getGraphics().clear(0);
        this.game.getGraphics().drawPixmap(
                this.pixmap,
                this.x,
                0,
                0,
                0,
                this.pixmap.getWidth(),
                this.pixmap.getHeight()
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
        this.pixmap.dispose();
    }
}
