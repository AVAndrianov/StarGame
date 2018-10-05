package com.avandrinov.stargame.sprite;

import com.avandrinov.stargame.base.Ship;
import com.avandrinov.stargame.math.Rect;
import com.avandrinov.stargame.pool.BulletPool;
import com.avandrinov.stargame.sounds.SaucerFly;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Saucer extends Ship {
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
    private Rect worldBounds;
    private TextureRegion bulletRegion;
    private int difficultyLevel = 4;
    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;
    private Vector2 leftVector;
    private Vector2 rightVector;
    private Vector2 upVector;
    private Vector2 downVector;
    private Sound sound;
    long id;
    private boolean isPlaying;
    private SaucerFly saucerFly;
    private BulletPool bulletPool;
    private Vector2 bulletV = new Vector2(0, 0.5f);

    public Saucer(BulletPool bulletPool, TextureAtlas atlas) {
        super(atlas.findRegion("bullet"), 1, 2, 2, bulletPool);
        this.bulletPool = bulletPool;
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
        bulletRegion = atlas.findRegion("saucerDifficultyLevel4");

        sprite(listSaucerLuminescence, listSaucerLandedLuminescence);

    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(worldBounds.getHeight() / size);
        pos.set(worldBounds.pos).sub(worldBounds.getHalfWidth() + x, y);
        this.worldBounds.set(worldBounds);
    }

    public void soundStart() {
        saucerFly.startMusic();
        if (!isPlaying) {
            isPlaying = true;
//            sound.play(0.1f);
        }
    }

    public void soundStop() {
        saucerFly.stopMusic();
//        sound.stop();
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
            case Input.Keys.A:
                saucerShot();
        }
        return super.keyDown(keycode);
    }

    public void keyUp(int keycode) {
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

        super.keyDown(keycode);
    }

    @Override
    public void update(float delta) {
        if (left)
            pos.mulAdd(leftVector, delta);
        if (right)
            pos.mulAdd(rightVector, delta);
        if (up)
            pos.mulAdd(upVector, delta);
        if (down)
            pos.mulAdd(downVector, delta);
        super.update(delta);
    }

    public boolean saveZone() {
        if (pos.x < worldBounds.getHalfWidth() * -1 + 0.3f && pos.y < -0.3f)
            return true;
        return false;
    }

    public void saucerShot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, pos, bulletV, 0.03f, worldBounds,1);

    }
}