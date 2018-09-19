package com.avandrinov.stargame.screen;

import com.avandrinov.stargame.base.BaseScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class MenuScreen extends BaseScreen {
    private boolean buttonStart = false;
    private boolean saucerLuminescence = true;
    private int cycleLuminescence = 0;
    private int difficultyLevel = 4;
    private SpriteBatch batch;
    private TextureRegion regionButtonStart;
    private TextureRegion regionButtonStartPush;
    private Vector2 saucerVector;
    private ArrayList<TextureRegion> listSaucerLandedLuminescence = new ArrayList<TextureRegion>();
    private ArrayList<TextureRegion> listSaucerDifficultyLevel = new ArrayList<TextureRegion>();

    public MenuScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        batch = new SpriteBatch();
        regionButtonStart = new TextureRegion(
                new Texture(Gdx.files.internal("start.png")));
        regionButtonStartPush = new TextureRegion(
                new Texture(Gdx.files.internal("startPush.png")));
        saucerVector = new Vector2(200, 400);
        for (int i = 1; i <= 4; i++) {
            listSaucerLandedLuminescence.add(new TextureRegion(
                    new Texture(Gdx.files.internal(
                            "saucerLandedLuminescence" + i + ".png"))));
            listSaucerDifficultyLevel.add(new TextureRegion(
                    new Texture("saucerDifficultyLevel" + i + ".png")));
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        if (saucerVector.y < 200) {
            batch.draw(listSaucerDifficultyLevel.get(difficultyLevel - 1),
                    saucerVector.x, saucerVector.y, 200, 200);
        } else {
            cycleLuminescence++;
            if (cycleLuminescence > 3) {
                cycleLuminescence = 0;
            }
            batch.draw(listSaucerLandedLuminescence.get(cycleLuminescence),
                    saucerVector.x, saucerVector.y, 200, 200);
            saucerVector.add(0, -1);
        }
        if (buttonStart) {
            batch.draw(regionButtonStartPush, 150, 1, 300, 200);
        } else {
            batch.draw(regionButtonStart, 150, 1, 300, 200);
        }
        batch.end();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (screenX > 180 && screenX < 420 && screenY > 100 && screenY < 210)
            if (difficultyLevel == 4)
                difficultyLevel = 1;
            else
                difficultyLevel++;
        saucerLuminescence = false;
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        if (screenX > 180 && screenX < 420 && screenY > 285 && screenY < 340)
            buttonStart = true;
        else
            buttonStart = false;
        if (screenX > 180 && screenX < 420 && screenY > 100 && screenY < 210)
            saucerLuminescence = false;
        else
            saucerLuminescence = true;
        return super.mouseMoved(screenX, screenY);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        buttonStart = false;
        if (screenX > 180 && screenX < 420 && screenY > 285 && screenY < 340)
            game.setScreen(new History(game, 1, difficultyLevel));
        return super.touchUp(screenX, screenY, pointer, button);
    }

    @Override
    public void dispose() {
        batch.dispose();
        super.dispose();
    }
}