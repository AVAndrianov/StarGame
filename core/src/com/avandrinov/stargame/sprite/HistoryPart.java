package com.avandrinov.stargame.sprite;

import com.avandrinov.stargame.base.Sprite;
import com.avandrinov.stargame.math.Rect;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

public class HistoryPart extends Sprite {
    private TextureAtlas atlas;
    private ArrayList<TextureRegion> listOfHistory;

    public HistoryPart() {
        atlas = new TextureAtlas("textures/historyPack.txt");
        listOfHistory = new ArrayList<TextureRegion>();
        for (int i = 1; i < 3; i++)
            listOfHistory.add(new TextureRegion(
                    atlas.findRegion("historyPart" + i)));
        sprite(listOfHistory);
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(worldBounds.getHeight());
    }

    public void chosePart(int i) {
        frame = i - 1;
    }
}