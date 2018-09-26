package com.avandrinov.stargame.sprite;

import com.avandrinov.stargame.base.Sprite;
import com.avandrinov.stargame.math.Rect;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BoardOfTheShipPart2 extends Sprite {
    private TextureRegion toBeContinued;
    private TextureAtlas atlas;

    public BoardOfTheShipPart2() {
        atlas = new TextureAtlas("textures/historyPack.txt");
        toBeContinued = new TextureRegion(
                atlas.findRegion("BoardOfTheShipPart2"));
        sprite(toBeContinued);
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(worldBounds.getHeight());
    }
}