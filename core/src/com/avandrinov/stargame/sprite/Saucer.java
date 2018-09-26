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

public class Saucer extends Button {
    private float saucerSpeed = 0.005f;
    private float saucerSpeedArrows = 0.005f;
    private Vector2 buf;
    private Vector2 buf2;
    private float size = 10f;
    private float x = 0.1f;
    private float y = 0.42f;
    private TextureAtlas atlas;
    private ArrayList<TextureRegion> listSaucerLuminescence;
    private ArrayList<TextureRegion> listSaucerLandedLuminescence;
    private ArrayList<TextureRegion> listSaucerDifficultyLevel;
    private Rect worldBounds;
    private int difficultyLevel = 4;


    public Saucer() {
        buf = new Vector2(0f, 0f);
        buf2 = new Vector2(0f, 0f);
        worldBounds = new Rect();
        atlas = new TextureAtlas("textures/saucerPack.txt");
        listSaucerLuminescence = new ArrayList<TextureRegion>();
        listSaucerLandedLuminescence = new ArrayList<TextureRegion>();
        for (int i = 1; i <= 4; i++) {
            listSaucerLandedLuminescence.add(
                    new TextureRegion(
                            atlas.findRegion(
                                    "saucerLandedLuminescence" + i)));
            listSaucerLuminescence.add(
                    new TextureRegion(
                            atlas.findRegion(
                                    "saucerLuminescence" + i)));

        }
        sprite(listSaucerLuminescence, listSaucerLandedLuminescence);
    }

    public Saucer(float size, float x, float y) {
        this.size = size;
        this.x = x;
        this.y = y;
        buf = new Vector2(0f, 0f);
        buf2 = new Vector2(0f, 0f);
        worldBounds = new Rect();
        atlas = new TextureAtlas("textures/saucerPack.txt");
        listSaucerDifficultyLevel = new ArrayList<TextureRegion>();
        listSaucerLuminescence = new ArrayList<TextureRegion>();
        for (int i = 1; i <= 4; i++) {
            listSaucerDifficultyLevel.add(new TextureRegion(
                    atlas.findRegion("saucerDifficultyLevel" + i)));
            listSaucerLuminescence.add(
                    new TextureRegion(
                            atlas.findRegion(
                                    "saucerLuminescence" + i)));
        }
        sprite(listSaucerDifficultyLevel, listSaucerLuminescence);
        setQuantityFrame(4);
        frame = 4;

    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(worldBounds.getHeight() / size);
        pos.set(worldBounds.pos).sub(worldBounds.getHalfWidth() - 1f, y);
        this.worldBounds.set(worldBounds);
    }

    public String saucerLanded(Moon moon) {
        if (buf.equals(buf2)) {
            buf.set(moon.pos.cpy().sub(pos).setLength(0.001f));
        }
        System.out.println(moon.pos.cpy().sub(pos).len());
        if (moon.pos.cpy().sub(pos).len() > 0.005f)
            pos.add(buf);
        else
            return "LandedComplete";
        if (getHeight() > 0.06f) {
            setHeight(getHeight() - 0.0003f);
            setWidth(getWidth() - 0.0003f);
        }
        return "";
    }

    public void setSaucerSpeed(float saucerSpeed) {
        this.saucerSpeed = saucerSpeed;
    }

    public void saucerMovePosition(String direction) {
        if (direction.equals("left"))
            pos.add(saucerSpeedArrows * -1, 0f);
        if (direction.equals("right"))
            pos.add(saucerSpeedArrows, 0f);
        if (direction.equals("up"))
            pos.add(0f, saucerSpeedArrows);
        if (direction.equals("down"))
            pos.add(0f, saucerSpeedArrows * -1);
    }

    public int getDifficultyLevel() {
        return difficultyLevel;
    }

    public void saucerLandedMenu() {
        if (frame > 3) {
            if (pos.y > 0.1f) {
                saucerLandedLuminescence();
                pos.add(0, -0.01f);
            } else {
                frame = 3;
            }
        }
        difficultyLevel = frame + 1;

    }

    public void saucerMovePosition(Vector2 direction) {
        pos.add(direction);
    }

    public void saucerLandedLuminescence() {
        if (frame == 7)
            frame = 4;
        else
            frame++;
    }

    public void saucerLuminescence() {
        if (frame == 3)
            frame = 0;
        else
            frame++;
    }

    public boolean saveZone() {
        if (pos.x < worldBounds.getHalfWidth() * -1 + 0.3f && pos.y < -0.3f)
            return true;
        return false;
    }
}