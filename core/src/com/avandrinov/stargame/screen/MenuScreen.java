package com.avandrinov.stargame.screen;

import com.avandrinov.stargame.base.BaseScreen;
import com.avandrinov.stargame.math.MatrixUtils;
import com.avandrinov.stargame.math.Rect;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class MenuScreen extends BaseScreen {
    private boolean buttonStart = false;
    private boolean saucerLuminescence = true;
    private int cycleLuminescence = 0;
    private int difficultyLevel = 4;
    private TextureRegion regionButtonStart;
    private TextureRegion regionButtonStartPush;
    private Vector2 saucerVector;
    private float yWindowCenter;
    private float xWindowCenter;
    private ArrayList<TextureRegion> listSaucerLuminescence = new ArrayList<TextureRegion>();
    private ArrayList<TextureRegion> listSaucerDifficultyLevel = new ArrayList<TextureRegion>();
    private Rect screenBounds; // границы области рисования в пикселях
    private Rect worldBounds; // граница проэкции мировых координат
    private Rect glBounds; // дефолтные границы OpenGl
    protected Matrix4 worldToGl;
    protected SpriteBatch batch;

    public MenuScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        this.batch = new SpriteBatch();
        this.screenBounds = new Rect();
        this.worldBounds = new Rect();
        this.glBounds = new Rect(0, 0, 1f, 1f);
        this.worldToGl = new Matrix4();
        yWindowCenter = 0.5f;
        xWindowCenter = -0.15f;
        regionButtonStart = new TextureRegion(
                new Texture(Gdx.files.internal("start.png")));
        regionButtonStartPush = new TextureRegion(
                new Texture(Gdx.files.internal("startPush.png")));
        saucerVector = new Vector2(xWindowCenter, yWindowCenter);
        for (int i = 1; i <= 4; i++) {
            listSaucerLuminescence.add(new TextureRegion(
                    new Texture(Gdx.files.internal(
                            "saucerLuminescence" + i + ".png"))));
            listSaucerDifficultyLevel.add(new TextureRegion(
                    new Texture("saucerDifficultyLevel" + i + ".png")));
        }
    }

    @Override
    public void resize(int width, int height) {
        screenBounds.setSize(width, height);
        screenBounds.setLeft(0);
        screenBounds.setBottom(0);

        float aspect = width / (float) height;
        worldBounds.setHeight(1f);
        worldBounds.setWidth(1f * aspect);

        MatrixUtils.calcTransitionMatrix(worldToGl, worldBounds, glBounds);
        batch.setProjectionMatrix(worldToGl);
        super.resize(width, height);

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        if (saucerVector.y < (-0.1f)) {
            batch.draw(listSaucerDifficultyLevel.get(difficultyLevel - 1),
                    saucerVector.x,
                    saucerVector.y,
                    0.3f, 0.3f);
        } else {
            cycleLuminescence++;
            if (cycleLuminescence > 3) {
                cycleLuminescence = 0;
            }
            batch.draw(listSaucerLuminescence.get(cycleLuminescence),
                    saucerVector.x,
                    saucerVector.y,
                    0.3f, 0.3f);
            saucerVector.add(0, -0.004f);
        }
        if (buttonStart) {
            batch.draw(regionButtonStartPush,
                    -0.23f,
                    -0.54f,
                    0.45f, 0.45f);
        } else {
            batch.draw(regionButtonStart,
                    -0.23f,
                    -0.54f,
                    0.45f, 0.45f);
        }
        batch.end();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (screenX > xWindowCenter - 120
                && screenX < xWindowCenter + 120
                && screenY > yWindowCenter - 55
                && screenY < yWindowCenter + 55)
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
        super.dispose();
    }
}