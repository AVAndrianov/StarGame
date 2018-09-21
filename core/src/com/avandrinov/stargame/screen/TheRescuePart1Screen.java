package com.avandrinov.stargame.screen;

import com.avandrinov.stargame.Comet;
import com.avandrinov.stargame.Saucer;
import com.avandrinov.stargame.base.BaseScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

public class TheRescuePart1Screen extends BaseScreen {

    private TextureRegion regionMoon;
    private TextureRegion regionBoom;
    private int height;
    private int width;
    private boolean saucerOnTheMoon = false;
    private int quantityComet = 8;
    private int difficultyLevel;
    private ArrayList<Comet> cometList = new ArrayList<Comet>();
    private Saucer saucer;

    TheRescuePart1Screen(Game game, int difficultyLevel) {
        super(game);
        this.difficultyLevel = difficultyLevel;
        quantityComet = quantityComet * difficultyLevel;
    }

    @Override
    public void show() {
        super.show();
        height = Gdx.graphics.getHeight();
        width = Gdx.graphics.getWidth();
        saucer = new Saucer(height, width);
        regionBoom = new TextureRegion(
                new Texture(Gdx.files.internal(
                        "boom.png")));
        regionMoon = new TextureRegion(
                new Texture(Gdx.files.internal(
                        "moon.png")));
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
        //Отрисовка тарелки
        if (saucerOnTheMoon)
            batch.draw(saucer.getListSaucerLandedLuminescence(),
                    saucer.getPosSaucer().x,
                    saucer.getPosSaucer().y,
                    saucer.getSaucerWidth(),
                    saucer.getSaucerHeight()
            );
        else
            batch.draw(saucer.getListSaucerLuminescence(),
                    saucer.getPosSaucer().x,
                    saucer.getPosSaucer().y,
                    saucer.getSaucerWidth(),
                    saucer.getSaucerHeight()
            );
        //Отрисовка луны
        batch.draw(regionMoon, 400, 250, 200, 200);
        //Отрисовка комет
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
        batch.end();
        //Столкновение корабля с кометой
        for (int i = 0; i < quantityComet; i++) {
            Comet com = cometList.get(i);
            if (saucer.getPosSaucer().x + 40 > com.getPosComet().x
                    && saucer.getPosSaucer().y + 35 > com.getPosComet().y
                    && saucer.getPosSaucer().x + 10 < com.getPosComet().x + 30
                    && saucer.getPosSaucer().y + 25 < com.getPosComet().y + 30) {
                game.setScreen(new History(game, 0, 6));
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
        if (saucer.getPosSaucer().x > 400
                && saucer.getPosSaucer().y > 250
                && saucer.getPosSaucer().x < 580
                && saucer.getPosSaucer().y < 430) {
            //Посадка тарелки
            if (saucer.saucerLanded().equals("LandedComplete"))
                game.setScreen(new History(game, 2, difficultyLevel));

        } else {
            //Управление тарелкой
            if (Gdx.input.isKeyPressed(20))
                saucer.saucerMovePosition("down");
            if (Gdx.input.isKeyPressed(21))
                saucer.saucerMovePosition("left");
            if (Gdx.input.isKeyPressed(19))
                saucer.saucerMovePosition("up");
            if (Gdx.input.isKeyPressed(22))
                saucer.saucerMovePosition("right");
        }
        //Движение комет
        for (int i = 0; i < quantityComet; i++) {
            cometList.get(i).cometMovePosition();
        }
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}