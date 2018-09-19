package com.avandrinov.stargame.screen;

import com.avandrinov.stargame.base.BaseScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BoardOfTheShipPart2Screen extends BaseScreen {
    private SpriteBatch batch;
    private TextureRegion toBeContinued;
    private TextureRegion landedSaucerInTheMoon;
    private float moonWidth = 180;
    private float moonHeight = 180;
    private float moonX = 430;
    private float moonY = 270;

    public BoardOfTheShipPart2Screen(Game game) {
        super(game);
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == 66)
            game.setScreen(new TheRescuePart1Screen(game, 3));
        if (keycode == 62)
            game.setScreen(new MenuScreen(game));
        return super.keyDown(keycode);
    }

    @Override
    public void show() {
        super.show();
        batch = new SpriteBatch();
        toBeContinued = new TextureRegion(new Texture("BoardOfTheShipPart2.png"));
        landedSaucerInTheMoon = new TextureRegion(new Texture("landedSaucerInTheMoon.png"));
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(toBeContinued, 60, -30);
        batch.end();

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void dispose() {
        batch.dispose();
        super.dispose();
    }
}
