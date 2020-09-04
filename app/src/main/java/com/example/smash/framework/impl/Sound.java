package com.example.smash.framework.impl;

import android.media.SoundPool;

public class Sound implements com.example.smash.framework.core.Sound {
    int soundId;
    SoundPool soundPool;

    public static final int DEFAULT_SOUND_PRIORITY = 0;
    public static final int DEFAULT_SOUND_LOOP = 0;
    public static final int DEFAULT_SOUND_PLAYBACK_RATE = 1;

    public Sound(SoundPool soundPool, int soundId) {
        this.soundId = soundId;
        this.soundPool = soundPool;
    }

    @Override
    public void play(float volume) {
        this.soundPool.play(
                this.soundId,
                volume,
                volume,
                Sound.DEFAULT_SOUND_PRIORITY,
                Sound.DEFAULT_SOUND_LOOP,
                Sound.DEFAULT_SOUND_PLAYBACK_RATE
        );
    }

    @Override
    public void dispose() {
        this.soundPool.unload(this.soundId);
    }
}
