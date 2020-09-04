package com.example.smash.framework.core;

public interface Music {
    public void play();
    public void stop();
    public void pause();
    public void setLooping(boolean looping);
    void setVolume(float volume);

    public boolean isStopped();
    public boolean isPlaying();
    public boolean isLooping();
    public void dispose();
}
