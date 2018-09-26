package com.avandrinov.stargame.sprite;

import com.avandrinov.stargame.base.Sprite;
import com.avandrinov.stargame.math.Rect;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Boom extends Sprite {
    private TextureRegion boom;
    private TextureAtlas atlas;

    public Boom() {
        atlas = new TextureAtlas("textures/otherPack.txt");
        boom = new TextureRegion(
                atlas.findRegion("boom"));
        sprite(boom);
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(worldBounds.getHeight() / 10f);
    }
}