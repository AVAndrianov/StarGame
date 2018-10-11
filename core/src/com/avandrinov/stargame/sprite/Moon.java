package com.avandrinov.stargame.sprite;

import com.avandrinov.stargame.base.Sprite;
import com.avandrinov.stargame.math.Rect;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Moon extends Sprite {
    private TextureAtlas atlas;
    private TextureRegion moon;

    public Moon(TextureAtlas atlas) {
        moon = new TextureRegion(
                atlas.findRegion("moon"));
        sprite(moon);

    }

    public void resize(Rect worldBounds) {
        setHeightProportion(worldBounds.getHeight() / 3f);
        pos.set(worldBounds.pos).sub(worldBounds.getHalfWidth() * -1 + 0.2f, -0.3f);
    }
}