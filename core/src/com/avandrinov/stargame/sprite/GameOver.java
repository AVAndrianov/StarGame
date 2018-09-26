package com.avandrinov.stargame.sprite;

import com.avandrinov.stargame.base.Sprite;
import com.avandrinov.stargame.math.Rect;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameOver extends Sprite {
    private TextureRegion gameOver;
    private TextureAtlas atlas;

    public GameOver() {
        atlas = new TextureAtlas("textures/historyPack.txt");
        gameOver = new TextureRegion(
                atlas.findRegion("saucerCrashed"));
        sprite(gameOver);
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(worldBounds.getHeight());
    }
}