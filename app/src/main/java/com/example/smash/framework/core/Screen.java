package com.example.smash.framework.core;

public abstract class Screen {
    public static final int FPS = 60;
    public static final int MAX_PIXELS = 100;
    protected final Game game;

    public Screen(Game game){
        this.game = game;
    }

    public abstract void update(float deltaTime);

    public abstract void present(float deltaTime);

    public abstract void pause();

    public abstract void resume();

    public abstract void dispose();
}
