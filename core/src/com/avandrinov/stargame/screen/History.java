package com.avandrinov.stargame.screen;

import com.avandrinov.stargame.base.BaseScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class History extends BaseScreen {
    private int historyPartNumber;
    private TextureRegion historyPart;
    private TextureRegion gameOver;
    private SpriteBatch batch;
    private int difficultyLevel;

    History(Game game, int historyPartNumber, int difficultyLevel) {
        super(game);
        this.historyPartNumber = historyPartNumber;
        this.difficultyLevel = difficultyLevel;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        switch (historyPartNumber) {
            case 0:
                gameOver = new TextureRegion(new Texture("saucerCrashed.png"));
                break;
            case 1:
                historyPart = new TextureRegion(new Texture("historyPart1.png"));
                break;
            case 2:
                historyPart = new TextureRegion(new Texture("historyPart2.png"));
                break;
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        if (historyPartNumber == 0)
            batch.draw(gameOver, 60, -10);
        else
            batch.draw(historyPart, 60, -30);
        batch.end();
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == 62 && historyPartNumber == 0)
            game.setScreen(new MenuScreen(game));
        if (keycode == 66)
            switch (historyPartNumber) {
                case 0:
                    game.setScreen(new TheRescuePart1Screen(game, difficultyLevel));
                    break;
                case 1:
                    game.setScreen(new TheRescuePart1Screen(game, difficultyLevel));
                    break;
                case 2:
                    game.setScreen(new BoardOfTheShipPart2Screen(game));
                    break;
            }
        return super.keyUp(keycode);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        switch (historyPartNumber) {
            case 0:
                game.setScreen(new TheRescuePart1Screen(game, difficultyLevel));
                break;
            case 1:
                game.setScreen(new TheRescuePart1Screen(game, difficultyLevel));
                break;
            case 2:
                game.setScreen(new BoardOfTheShipPart2Screen(game));
        }
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public void dispose() {
        batch.dispose();
        super.dispose();
    }
}
