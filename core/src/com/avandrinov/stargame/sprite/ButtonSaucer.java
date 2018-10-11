package com.avandrinov.stargame.sprite;

import com.avandrinov.stargame.base.ActionListener;
import com.avandrinov.stargame.base.Button;
import com.avandrinov.stargame.math.Rect;
import com.avandrinov.stargame.sounds.SaucerFly;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class ButtonSaucer extends Button {
    private float x = -0.1f;
    private float y = 0.42f;
    private float size = 3f;
    private TextureAtlas atlas;
    private ArrayList<TextureRegion> listSaucerLuminescence;
    private ArrayList<TextureRegion> listSaucerDifficultyLevel;
    private Rect worldBounds;
    private int difficultyLevel = 4;
    private boolean menu;
    private Vector2 landedMenu;



    public ButtonSaucer(TextureAtlas atlas, ActionListener actionListener) {
        menu = true;
        worldBounds = new Rect();
        landedMenu = new Vector2(0, -0.01f);
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

    public void saucerLandedLuminescence() {
        if (frame == 7)
            frame = 4;
        else
            frame++;
    }

    @Override
    public void update(float delta) {
//            saucerLandedMenu();
        super.update(delta);
    }
}