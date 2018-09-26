package com.avandrinov.stargame.screen;

import com.avandrinov.stargame.base.BaseScreen;
import com.avandrinov.stargame.math.Rect;
import com.avandrinov.stargame.sprite.GameOver;
import com.avandrinov.stargame.sprite.HistoryPart;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class History extends BaseScreen {
    private int historyPartNumber;
    private int difficultyLevel;
    private GameOver gameOver;
    private HistoryPart historyPart;

    History(Game game, int historyPartNumber, int difficultyLevel) {
        super(game);
        this.historyPartNumber = historyPartNumber;
        this.difficultyLevel = difficultyLevel;
        gameOver = new GameOver();
        historyPart = new HistoryPart();
    }


    @Override
    public void show() {
        if (historyPartNumber > 0)
            historyPart.chosePart(historyPartNumber);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        if (historyPartNumber == 0)
            gameOver.draw(batch);

        else
            historyPart.draw(batch);
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
    public boolean touchDown(Vector2 touch, int pointer) {
        switch (historyPartNumber) {
            case 0:
                game.setScreen(new MenuScreen(game));
                break;
            case 1:
                break;
            case 2:
        }
        return super.touchDown(touch, pointer);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        switch (historyPartNumber) {
            case 0:
                break;
            case 1:
                game.setScreen(new TheRescuePart1Screen(game, difficultyLevel));
                break;
            case 2:
                game.setScreen(new BoardOfTheShipPart2Screen(game));
        }
        return super.touchUp(touch, pointer);
    }

    @Override
    public void resize(Rect worldBounds) {
        gameOver.resize(worldBounds);
        historyPart.resize(worldBounds);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
