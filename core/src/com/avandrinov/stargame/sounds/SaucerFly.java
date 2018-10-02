package com.avandrinov.stargame.sounds;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;

public class SaucerFly {
    private AssetManager assetManager;
    private Music music;
    private boolean isPlaying;
    private boolean isLoadMusic;

    public SaucerFly() {
        assetManager = new AssetManager();
    }

    public void startMusic() {
        if (!isLoadMusic)
            if (assetManager.isLoaded("sounds/saucerFly.mp3")) {
                music = assetManager.get("sounds/saucerFly.mp3", Music.class);
                isLoadMusic = true;
                music.setVolume(1.5f);
                System.out.println("");
            } else {
                System.out.println("else");
                assetManager.load("sounds/saucerFly.mp3", Music.class);
                return;
            }
        if (!isPlaying && isLoadMusic) {
            isPlaying = true;
            music.play();
//            music.setLooping(true);
        }
    }

    public void stopMusic() {
        if (isLoadMusic) {
            isPlaying = false;
            music.stop();
        }
    }
}