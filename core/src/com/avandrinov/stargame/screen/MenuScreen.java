package com.avandrinov.stargame.screen;

import com.avandrinov.stargame.base.BaseScreen;
import com.avandrinov.stargame.base.Sprite;
import com.avandrinov.stargame.math.MatrixUtils;
import com.avandrinov.stargame.math.Rect;
import com.avandrinov.stargame.sprite.ButtonStart;
import com.avandrinov.stargame.sprite.Saucer;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class MenuScreen extends BaseScreen {
    private ButtonStart buttonStart;
    private TextureAtlas atlas;
    private Saucer saucer;

    public MenuScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        atlas = new TextureAtlas("textures/saucerPack.txt");
        saucer = new Saucer(3, 0.9f, -0.6f);
        buttonStart = new ButtonStart();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        saucer.draw(batch);
        saucer.saucerLandedMenu();
        buttonStart.draw(batch);
        batch.end();
    }

    @Override
    protected void resize(Rect worldBounds) {
        saucer.resize(worldBounds);
        buttonStart.resize(worldBounds);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        if (buttonStart.touchUp(touch, pointer))
            game.setScreen(new History(game, 1,saucer.getDifficultyLevel()));
        return super.touchUp(touch, pointer);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        saucer.touchDown(touch,pointer);
        buttonStart.touchDown(touch, pointer);
        return super.touchDown(touch, pointer);
    }

    @Override
    public boolean mouseMoved(Vector2 touch) {
        buttonStart.mouseMoved(touch);
        return super.mouseMoved(touch);
    }

    @Override
    public boolean touchDragged(Vector2 touch, int pointer) {
        buttonStart.touchDragged(touch, pointer);
        return super.touchDragged(touch, pointer);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}