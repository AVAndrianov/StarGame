package com.avandrinov.stargame.sprite;

import com.avandrinov.stargame.base.AnalogStick;
import com.avandrinov.stargame.math.Rect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

public class JoyStick extends AnalogStick {
    private ArrayList<TextureRegion> stickList;
    private TextureAtlas atlas;

    public JoyStick() {
        atlas = new TextureAtlas("textures/otherPack.txt");
        stickList = new ArrayList<TextureRegion>();
        stickList.add(new TextureRegion(
                atlas.findRegion("stickBig")));
        sprite(stickList);
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(worldBounds.getHeight() / 2);
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.setColor(0f, 0f, 0f, 0.1f);
        super.draw(batch);
        batch.setColor(-1);
    }
}