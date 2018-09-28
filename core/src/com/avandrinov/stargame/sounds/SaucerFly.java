package com.avandrinov.stargame.sounds;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;

public class SaucerFly {
    private AssetManager assetManager;

    public SaucerFly() {
        assetManager = new AssetManager();
        assetManager.load("sounds/saucerFly.ogg", Music.class);
    }

    public boolean startMusic() {

//        if (assetManager.isLoaded("sounds/saucerFly.ogg")) {
            Music music = assetManager.get("sounds/saucerFly.ogg", Music.class);
            music.play();
            music.setLooping(true);
//        } else {
//            return false;
//        }
        return true;
    }
}
