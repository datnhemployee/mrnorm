package com.example.smash.framework.core;

public interface Game {

//    createWindowAndUIComponent();

//    Input  input  = new  Input();
    public Input getInput();
    public FileIO getFileIO();
//    Graphics graphics  = new  Graphics();
    public Graphics getGraphics();

//    Audio audio = new  Audio();
    public Audio getAudio();

//    Screen currentScreen  = new  MainMenu();
    public void setScreen(Screen screen);

    public Screen getCurrentScreen();
    public Screen getStartScreen();

//    float  lastFrameTime = currentTime();
//    while(  !userQuit() ) {
//        float deltaTime  = currentTime() – lastFrameTime;
//        lastFrameTime = currentTime();

//        currentScreen.updateState(input, deltaTime);
//        currentScreen.present(graphics, audio,  deltaTime);
//    }

//    cleanupResources();
}
