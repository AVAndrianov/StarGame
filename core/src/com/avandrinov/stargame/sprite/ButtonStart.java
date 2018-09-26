package com.avandrinov.stargame.sprite;

import com.avandrinov.stargame.base.Button;
import com.avandrinov.stargame.base.Sprite;
import com.avandrinov.stargame.math.Rect;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class ButtonStart extends Button {
    private TextureAtlas atlas;
    private ArrayList<TextureRegion> buttonStartList;
    private boolean pressed;
    private int pointer;

    public ButtonStart() {
        buttonStartList = new ArrayList<TextureRegion>();
        atlas = new TextureAtlas("textures/otherPack.txt");
        buttonStartList.add(
                new TextureRegion(
                        atlas.findRegion("start")));
        buttonStartList.add(
                new TextureRegion(
                        atlas.findRegion("startPush")));

        sprite(buttonStartList);
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(worldBounds.getHeight() / 2.4f);
        pos.set(worldBounds.pos).sub(0f, 0.3f);
    }

    @Override
    public boolean mouseMoved(Vector2 touch) {
        if (isMe(touch)) {
            frame = 1;
            pressed = true;
        } else
            frame = 0;
        return super.mouseMoved(touch);
    }

    @Override
    public boolean touchDragged(Vector2 touch, int pointer) {
        if (isMe(touch))
            frame = 1;
        else
            frame = 0;
        return super.touchDragged(touch, pointer);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {

        return super.touchDown(touch, pointer);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {

        return super.touchUp(touch, pointer);
    }
}