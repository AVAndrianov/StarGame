package com.avandrinov.stargame;

import com.avandrinov.stargame.screen.History;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Saucer {
    private TextureRegion regionSaucer;
    private Vector2 posSaucer;
    private int windowHeight;
    private int windowWidth;
    private float saucerHeight = 50;
    private float saucerWidth = 50;
    private int cycleLuminescence = 0;
    private int behindTheScreen = 30;
    private int startPosition;
    private int rotationSaucer;
    private float moveX;
    private float moveY;
    private Vector2 buf, buf2;
    private Vector2 v;
    private ArrayList<TextureRegion> listSaucerLuminescence = new ArrayList<TextureRegion>();
    private ArrayList<TextureRegion> listSaucerLandedLuminescence = new ArrayList<TextureRegion>();

    public Saucer(int windowHeight, int windowWidth) {
        this.windowHeight = windowHeight;
        this.windowWidth = windowWidth;
        buf = new Vector2(0f, 0f);
        buf2 = new Vector2(0f, 0f);
        v = new Vector2(450f, 360f);
        posSaucer = new Vector2(10f, 10f);
        listSaucerLuminescence = new ArrayList<TextureRegion>();
        listSaucerLandedLuminescence = new ArrayList<TextureRegion>();
        for (int i = 1; i <= 4; i++) {
            listSaucerLandedLuminescence.add(
                    new TextureRegion(
                            new Texture(Gdx.files.internal(
                                    "saucerLandedLuminescence" + i + ".png"))));
            listSaucerLuminescence.add(
                    new TextureRegion(
                            new Texture(Gdx.files.internal(
                                    "saucerLuminescence" + i + ".png"))));
        }
    }

    public void saucerStartPosition() {

    }

    public String saucerLanded() {
        if (buf.equals(buf2))
            buf.set(v.cpy().sub(this.getPosSaucer()).setLength(1f));
        if (v.cpy().sub(this.getPosSaucer()).len() > 0.5f)
            this.setPosSaucer((this.getPosSaucer().add(buf)));
        else
            return "LandedComplete";
        if (this.getSaucerWidth() > 30) {
            this.setSaucerWidth(this.getSaucerWidth() - 0.3f);
            this.setSaucerHeight(this.getSaucerHeight() - 0.3f);
        }
        return "";
    }

    public void saucerMovePosition(String direction) {
        if (direction.equals("left"))
            posSaucer.add(-1, 0);
        if (direction.equals("right"))
            posSaucer.add(1, 0);
        if (direction.equals("up"))
            posSaucer.add(0, 1);
        if (direction.equals("down"))
            posSaucer.add(0, -1);
    }

    public Vector2 getPosSaucer() {
        return posSaucer;
    }

    public TextureRegion getListSaucerLuminescence() {
        if (cycleLuminescence == 3)
            cycleLuminescence = 0;
        else
            cycleLuminescence++;
        return listSaucerLuminescence.get(cycleLuminescence);
    }

    public TextureRegion getListSaucerLandedLuminescence() {
        if (cycleLuminescence == 3)
            cycleLuminescence = 0;
        else
            cycleLuminescence++;
        return listSaucerLandedLuminescence.get(cycleLuminescence);
    }

    public float getSaucerHeight() {
        return saucerHeight;
    }

    public float getSaucerWidth() {
        return saucerWidth;
    }

    public void setSaucerHeight(float saucerHeight) {
        this.saucerHeight = saucerHeight;
    }

    public void setSaucerWidth(float saucerWidth) {
        this.saucerWidth = saucerWidth;
    }

    public void setPosSaucer(Vector2 posSaucer) {
        this.posSaucer = posSaucer;
    }
}
