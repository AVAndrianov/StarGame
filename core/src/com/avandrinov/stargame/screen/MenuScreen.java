package com.avandrinov.stargame.screen;

import com.avandrinov.stargame.base.BaseScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import sun.net.www.content.image.png;

public class MenuScreen extends BaseScreen {
    boolean buttonStart = false;
    boolean saucerLuminescence = true;
    int cycleLuminescence = 0;
    int difficultie = 4;
    private SpriteBatch batch;
    private TextureRegion regionButtonStart;
    private TextureRegion regionButtonStartPush;
    private TextureRegion regionSaucer;
    private TextureRegion regionSaucerLanded;
    private Vector2 saucerVector;
    private ArrayList<TextureRegion> listSaucerLuminescence = new ArrayList<TextureRegion>();
    private ArrayList<TextureRegion> listSaucerLandedLuminescence = new ArrayList<TextureRegion>();
    private ArrayList<TextureRegion> listSaucerDifficultyLevel = new ArrayList<TextureRegion>();
    Sound sound;

    public MenuScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        batch = new SpriteBatch();
        regionButtonStart = new TextureRegion(new Texture(Gdx.files.internal("start.png")));
        regionButtonStartPush = new TextureRegion(new Texture(Gdx.files.internal("startPush.png")));
        regionSaucerLanded = new TextureRegion(new Texture(Gdx.files.internal("saucerLanded.png")));
        regionSaucer = new TextureRegion(new Texture(Gdx.files.internal("saucer.png")));
        saucerVector = new Vector2(200, 400);
        for (int i = 1; i <= 4; i++) {
            listSaucerLuminescence.add(new TextureRegion(
                    new Texture(Gdx.files.internal("saucerLuminescence" + i + ".png"))));
            listSaucerLandedLuminescence.add(new TextureRegion(
                    new Texture(Gdx.files.internal("saucerLandedLuminescence" + i + ".png"))));
            listSaucerDifficultyLevel.add(new TextureRegion(
                    new Texture("saucerDifficultyLevel" + i + ".png")));
        }
        sound = Gdx.audio.newSound(Gdx.files.internal("saucerLanded4.mp3"));
        sound.play(0.5f,1,0);

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        if (saucerVector.y < 200) {
            batch.draw(listSaucerDifficultyLevel.get(difficultie - 1),
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
            if (difficultie == 4)
                difficultie = 1;
            else
                difficultie++;
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
//            game.setScreen(new BoardOfTheShipPart2Screen(game));
            game.setScreen(new History(game, 1,difficultie));
        return super.touchUp(screenX, screenY, pointer, button);
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
