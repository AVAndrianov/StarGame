package com.avandrinov.stargame.screen;

import com.avandrinov.stargame.base.BaseScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BoardOfTheShipPart2Screen extends BaseScreen {
    private SpriteBatch batch;
    private TextureRegion toBeContinued;

    BoardOfTheShipPart2Screen(Game game) {
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
    public void dispose() {
        batch.dispose();
        super.dispose();
    }
}