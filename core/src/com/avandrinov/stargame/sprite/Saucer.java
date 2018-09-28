package com.avandrinov.stargame.sprite;

import com.avandrinov.stargame.base.ActionListener;
import com.avandrinov.stargame.base.AnalogStick;
import com.avandrinov.stargame.base.Button;
import com.avandrinov.stargame.base.Sprite;
import com.avandrinov.stargame.math.Rect;
import com.avandrinov.stargame.sounds.SaucerFly;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Saucer extends Button {
    private float saucerSpeed = 0.005f;
    private float saucerSpeedArrows = 0.3f;
    private Vector2 buf;
    private Vector2 buf2;
    private int rotate;
    private float size = 10f;
    private float x = -0.1f;
    private float y = 0.42f;
    private TextureAtlas atlas;
    private ArrayList<TextureRegion> listSaucerLuminescence;
    private ArrayList<TextureRegion> listSaucerLandedLuminescence;
    private ArrayList<TextureRegion> listSaucerDifficultyLevel;
    private Rect worldBounds;
    private int difficultyLevel = 4;
    private boolean menu;
    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;
    private Vector2 leftVector;
    private Vector2 rightVector;
    private Vector2 upVector;
    private Vector2 downVector;
    private Vector2 landedMenu;
    private Sound sound;
    long id;
    private boolean isPlaying;
    private SaucerFly saucerFly;

    public Saucer() {
        buf = new Vector2(0f, 0f);
        buf2 = new Vector2(0f, 0f);
        leftVector = new Vector2(saucerSpeedArrows * -1f, 0f);
        rightVector = new Vector2(saucerSpeedArrows, 0f);
        upVector = new Vector2(0f, saucerSpeedArrows);
        downVector = new Vector2(0f, saucerSpeedArrows * -1);
        worldBounds = new Rect();
        saucerFly = new SaucerFly();
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
        sound = Gdx.audio.newSound(Gdx.files.internal("sounds/saucerFly.ogg"));
        sprite(listSaucerLuminescence, listSaucerLandedLuminescence);
    }

    public Saucer(float size, ActionListener actionListener) {
        menu = true;
        this.size = size;
        worldBounds = new Rect();
        landedMenu = new Vector2(0, -0.01f);
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
        x = -0.67f;
        y = -0.6f;
        sprite(listSaucerDifficultyLevel, listSaucerLuminescence);
        setQuantityFrame(4);
        frame = 4;

    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(worldBounds.getHeight() / size);
        pos.set(worldBounds.pos).sub(worldBounds.getHalfWidth() + x, y);
        if (menu)
            setLeft(worldBounds.getLeft() + worldBounds.getHalfWidth() - 0.17f);
        this.worldBounds.set(worldBounds);
    }

    public void soundStart() {
        if (!isPlaying) {
            isPlaying = true;
            sound.play(0.01f);
        }
    }

    public void soundStop() {
        sound.stop();
        isPlaying = false;
    }

    public String saucerLanded(Moon moon) {
        left = false;
        right = false;
        up = false;
        down = false;
        if (buf.equals(buf2)) {
            buf.set(moon.pos.cpy().sub(pos).setLength(0.001f));
        }
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

    @Override
    public boolean keyDown(int keycode) {
        soundStart();
        switch (keycode) {
            case Input.Keys.UP:
                up = true;
                break;
            case Input.Keys.DOWN:
                down = true;
                break;
            case Input.Keys.LEFT:
                left = true;
                break;
            case Input.Keys.RIGHT:
                right = true;
        }
        return super.keyDown(keycode);
    }

    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
                up = false;
                break;
            case Input.Keys.DOWN:
                down = false;
                break;
            case Input.Keys.LEFT:
                left = false;
                break;
            case Input.Keys.RIGHT:
                right = false;

        }
        if (!up && !down && !left && !right)
            soundStop();

        return super.keyDown(keycode);
    }

    @Override
    public void update(float delta) {
        if (size == 3) {
            if (frame > 3) {
                if (pos.y > 0.1f) {
                    saucerLandedLuminescence();
                    pos.mulAdd(landedMenu, delta);
                } else {
                    frame = 3;
                }
            }
            difficultyLevel = frame + 1;
        } else {
            if (left)
                pos.mulAdd(leftVector, delta);
            if (right)
                pos.mulAdd(rightVector, delta);
            if (up)
                pos.mulAdd(upVector, delta);
            if (down)
                pos.mulAdd(downVector, delta);
        }
        super.update(delta);
    }

    public boolean saveZone() {
        if (pos.x < worldBounds.getHalfWidth() * -1 + 0.3f && pos.y < -0.3f)
            return true;
        return false;
    }

}