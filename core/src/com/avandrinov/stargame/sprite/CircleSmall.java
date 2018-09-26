package com.avandrinov.stargame.sprite;

import com.avandrinov.stargame.base.Sprite;
import com.avandrinov.stargame.math.Rect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

public class CircleSmall extends Sprite {
    private TextureAtlas atlas;
    private ArrayList<TextureRegion> stickList;

    public CircleSmall() {
        atlas = new TextureAtlas("textures/otherPack.txt");
        stickList = new ArrayList<TextureRegion>();
        stickList.add(new TextureRegion(
                atlas.findRegion("stick")));
        sprite(stickList);
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(worldBounds.getHeight()/6);
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.setColor(0f,0f,0f,0.1f);
        super.draw(batch);
        batch.setColor(-1);
    }
}