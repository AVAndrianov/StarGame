package com.avandrinov.stargame.screen;

import com.avandrinov.stargame.Comet;
import com.avandrinov.stargame.base.BaseScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class TheRescuePart1Screen extends BaseScreen {

    private SpriteBatch batch;
    private TextureRegion regionSaucer;
    private TextureRegion regionMoon;
    private TextureRegion regionBoom;
    private Vector2 pos;
    private int cycleLuminescence = 0;
    private int cycleLended = 0;
    private int height;
    private int width;
    private int widthSaucer = 50;
    private int heightSaucer = 50;
    private boolean saucerOnTheMoon = false;
    private boolean saucerLandedOnTheMoon = false;
    private int quantityComet = 10;
    private int difficultyLevel;
    private ArrayList<Comet> cometList = new ArrayList<Comet>();
    private ArrayList<TextureRegion> listSaucerLuminescence = new ArrayList<TextureRegion>();
    private ArrayList<TextureRegion> listSaucerLandedLuminescence = new ArrayList<TextureRegion>();

    TheRescuePart1Screen(Game game, int difficultyLevel) {
        super(game);
        this.difficultyLevel = difficultyLevel;
        quantityComet = quantityComet * difficultyLevel;
    }

    @Override
    public void show() {
        super.show();
        batch = new SpriteBatch();
        height = Gdx.graphics.getHeight();
        width = Gdx.graphics.getWidth();
        regionSaucer = new TextureRegion(new Texture("saucer.png"));
        for (int i = 1; i <= 4; i++) {
            listSaucerLandedLuminescence.add(new TextureRegion(
                    new Texture(Gdx.files.internal("saucerLandedLuminescence" + i + ".png"))));
            listSaucerLuminescence.add(new TextureRegion(
                    new Texture(Gdx.files.internal("saucerLuminescence" + i + ".png"))));
        }
        regionBoom = new TextureRegion(new Texture(Gdx.files.internal("boom.png")));
        regionMoon = new TextureRegion(new Texture(Gdx.files.internal("moon2.png")));
        pos = new Vector2(10f, 10f);
        for (int i = 0; i < quantityComet; i++) {
            cometList.add(new Comet(height, width));
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        if (cycleLuminescence == 3) {
            cycleLuminescence = 0;
        } else {
            cycleLuminescence++;
        }
        if (saucerOnTheMoon)
            batch.draw(listSaucerLuminescence.get(cycleLuminescence),
                    pos.x, pos.y, widthSaucer, heightSaucer);
        else if (saucerLandedOnTheMoon)
            batch.draw(regionSaucer, pos.x, pos.y, widthSaucer, heightSaucer);

        else
            batch.draw(listSaucerLandedLuminescence.get(cycleLuminescence),
                    pos.x, pos.y, 50, 50);
        batch.draw(regionMoon, 400, 250, 200, 200);
        for (int i = 0; i < quantityComet; i++) {
            Comet com = cometList.get(i);
            batch.draw(com.getRegionComet(), com.getPosComet().x, com.getPosComet().y, 15,
                    15, 30, 30, 1, 1, com.getRotationComet());
        }
        //Столкновение комет с луной
        for (int i = 0; i < quantityComet; i++) {
            Comet com = cometList.get(i);
            if (com.getPosComet().x > 400
                    && com.getPosComet().y > 250
                    && com.getPosComet().x < 580
                    && com.getPosComet().y < 430) {
                batch.draw(regionBoom, com.getPosComet().x, com.getPosComet().y,
                        50, 50);
                com.cometStartPosition();
            }
        }
        //Столкновение комет с кометами
        for (int i = 0; i < quantityComet - 1; i++) {
            Comet comI = cometList.get(i);
            for (int j = i + 1; j < quantityComet; j++) {
                Comet comJ = cometList.get(j);
                if (comI.getPosComet().x + 10 > comJ.getPosComet().x
                        && comI.getPosComet().y + 10 > comJ.getPosComet().y
                        && comI.getPosComet().x < comJ.getPosComet().x + 10
                        && comI.getPosComet().y < comJ.getPosComet().y + 10) {
                    batch.draw(regionBoom, comJ.getPosComet().x, comJ.getPosComet().y,
                            50, 50);
                    comJ.cometStartPosition();
                    comI.cometStartPosition();
                }
            }
        }
        //Стартовая область безопасности
        for (int i = 0; i < quantityComet; i++) {
            Comet com = cometList.get(i);
            if (com.getPosComet().x > -30 && com.getPosComet().y > -30
                    && com.getPosComet().x < 70 && com.getPosComet().y < 70) {
                com.cometStartPosition();
            }
        }
        batch.end();
        //Столкновение корабля с кометой
        for (int i = 0; i < quantityComet; i++) {
            Comet com = cometList.get(i);
            if (pos.x + 40 > com.getPosComet().x
                    && pos.y + 35 > com.getPosComet().y
                    && pos.x + 10 < com.getPosComet().x + 30
                    && pos.y + 25 < com.getPosComet().y + 30) {
                game.setScreen(new History(game, 0, 6));
            }
        }
        if (pos.x > 400 && pos.y > 250 && pos.x < 580 && pos.y < 430) {
            if (pos.x < 450)
                pos.add(1, 0);
            if (pos.y < 360)
                pos.add(0, 1);
            if (pos.x > 450)
                pos.add(-1, 0);
            if (pos.y > 360)
                pos.add(0, -1);
            if (pos.x == 450 && pos.y == 360) {
                saucerLandedOnTheMoon = true;
                saucerOnTheMoon = false;
                game.setScreen(new History(game, 2, difficultyLevel));
            } else
                saucerOnTheMoon = true;
            cycleLended++;
            if (widthSaucer > 30 && cycleLended % 3 == 0) {
                widthSaucer--;
                heightSaucer--;
            }
        } else {
            if (Gdx.input.isKeyPressed(20))
                pos.add(0, -1);
            if (Gdx.input.isKeyPressed(21))
                pos.add(-1, 0);
            if (Gdx.input.isKeyPressed(19))
                pos.add(0, 1);
            if (Gdx.input.isKeyPressed(22))
                pos.add(1, 0);
        }
        for (int i = 0; i < quantityComet; i++) {
            cometList.get(i).cometMovePosition();
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        super.dispose();
    }
}