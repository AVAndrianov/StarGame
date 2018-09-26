package com.avandrinov.stargame.screen;

import com.avandrinov.stargame.base.BaseScreen;
import com.avandrinov.stargame.math.Rect;
import com.avandrinov.stargame.sprite.BoardOfTheShipPart2;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class BoardOfTheShipPart2Screen extends BaseScreen {
    private TextureRegion toBeContinued;
    private BoardOfTheShipPart2 boardOfTheShipPart2;

    BoardOfTheShipPart2Screen(Game game) {
        super(game);
        boardOfTheShipPart2 = new BoardOfTheShipPart2();
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
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        boardOfTheShipPart2.draw(batch);
        batch.end();
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        game.setScreen(new MenuScreen(game));

        return super.touchUp(touch, pointer);
    }

    @Override
    protected void resize(Rect worldBounds) {
        boardOfTheShipPart2.resize(worldBounds);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}