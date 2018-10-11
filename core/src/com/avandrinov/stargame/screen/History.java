package com.avandrinov.stargame.screen;

import com.avandrinov.stargame.base.BaseScreen;
import com.avandrinov.stargame.base.Font;
import com.avandrinov.stargame.math.Rect;
import com.avandrinov.stargame.sprite.GameOver;
import com.avandrinov.stargame.sprite.HistoryPart;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class History extends BaseScreen {
    private int historyPartNumber;
    private int difficultyLevel;
    private GameOver gameOver;
    private HistoryPart historyPart;
    private Font font;
    private StringBuilder history = new StringBuilder();
    private Rect worldBounds;


    History(Game game, int historyPartNumber, int difficultyLevel) {
        super(game);
        this.historyPartNumber = historyPartNumber;
        this.difficultyLevel = difficultyLevel;
        gameOver = new GameOver();
//        historyPart = new HistoryPart();
        font = new Font("font2/calibri.fnt", "font2/calibri.png");
        font.setColor(Color.BLACK);
        font.setFontSize(0.05f);
    }


    @Override
    public void show() {
//        if (historyPartNumber > 0)
//            historyPart.chosePart(historyPartNumber);
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
            printInfo();
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
//        historyPart.resize(worldBounds);
        this.worldBounds = worldBounds;
    }

    private void printInfo() {
        history.setLength(0);
        String patch = "history/part" + historyPartNumber + ".txt";
        font.draw(batch, history.append(readTextFromInputStream(patch)), 0, worldBounds.getTop() - 0.01f, Align.center);
    }

    static public String readTextFromInputStream(String in) {
        StringBuilder text = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(in)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = null;
        String newline = System.getProperty("line.separator");
        try {
            while ((line = reader.readLine()) != null) {
                text.append(line);
                text.append(newline);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text.toString();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
