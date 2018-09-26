package com.avandrinov.stargame.screen;

import com.avandrinov.stargame.math.Rect;
import com.avandrinov.stargame.sprite.Boom;
import com.avandrinov.stargame.sprite.Comet;
import com.avandrinov.stargame.sprite.Moon;
import com.avandrinov.stargame.sprite.Saucer;
import com.avandrinov.stargame.base.BaseScreen;
import com.avandrinov.stargame.sprite.CircleSmall;
import com.avandrinov.stargame.sprite.JoyStick;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class TheRescuePart1Screen extends BaseScreen {
    private boolean saucerOnTheMoon = false;
    private boolean saucerLandedOnTheMoon = false;
    private int quantityComet = 12;
    private int difficultyLevel;
    private ArrayList<Comet> cometList;
    private Saucer saucer;
    private Moon moon;
    private Boom boom;
    private CircleSmall circleSmall;
    private Vector2 baseVector;
    private Vector2 baseVector2;
    private TextureRegion regionComet;
    private TextureAtlas atlasComet;
    private JoyStick joyStick;

    TheRescuePart1Screen(Game game, int difficultyLevel) {
        super(game);
        this.difficultyLevel = difficultyLevel;
        quantityComet = quantityComet * difficultyLevel;
    }

    @Override
    public void show() {
        super.show();
        cometList = new ArrayList<Comet>();
        joyStick = new JoyStick();
        circleSmall = new CircleSmall();
        joyStick = new JoyStick();
        saucer = new Saucer();
        moon = new Moon();
        boom = new Boom();
        baseVector2 = new Vector2();
        baseVector = new Vector2();
        loadTextures();
        for (int i = 0; i < quantityComet; i++) {
            cometList.add(new Comet(regionComet));
        }
    }

    private void loadTextures() {
        atlasComet = new TextureAtlas("textures/otherPack.txt");
        regionComet = new TextureRegion(
                atlasComet.findRegion("comet"));
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        //Отрисовка тарелки
        if (saucerOnTheMoon) {
            if (saucer.saucerLanded(moon).equals("LandedComplete"))
                saucerLandedOnTheMoon = true;
            else
                saucer.saucerLandedLuminescence();
            saucer.draw(batch);
        } else {
            //Управление тарелкой стрелками
            if (Gdx.input.isKeyPressed(20))
                saucer.saucerMovePosition("down");
            if (Gdx.input.isKeyPressed(21))
                saucer.saucerMovePosition("left");
            if (Gdx.input.isKeyPressed(19))
                saucer.saucerMovePosition("up");
            if (Gdx.input.isKeyPressed(22))
                saucer.saucerMovePosition("right");
            //Управление тарелкой стиком
            saucer.setSaucerSpeed(joyStick.getSpeed());
            saucer.saucerMovePosition(joyStick.getDirection());
            saucer.saucerLuminescence();
            saucer.draw(batch);
        }
        //Отрисовка Стика
        if (joyStick.isShowStick()) {
            joyStick.draw(batch);
        }
        //Отрисовка луны
        moon.draw(batch);
        //Отрисовка комет
        for (int i = 0; i < quantityComet; i++) {
            Comet com = cometList.get(i);
            com.draw(batch);
            com.cometMovePosition();
        }
        //Столкновение комет с луной
        for (int i = 0; i < quantityComet; i++) {
            Comet com = cometList.get(i);
            baseVector.set(com.pos);
            if (baseVector.sub(moon.pos).len() < 0.18f) {
                boom.pos.set(com.pos);
                com.cometStartPosition();
                boom.draw(batch);
            }
        }
        //Столкновение комет с кометами
        for (int i = 0; i < quantityComet - 1; i++) {
            Comet comI = cometList.get(i);
            baseVector.set(comI.pos);
            for (int j = i + 1; j < quantityComet; j++) {
                Comet comJ = cometList.get(j);
                baseVector2.set(comJ.pos);
                if (baseVector2.sub(baseVector).len() < 0.03f) {
                    boom.pos.set(comI.pos);
                    comI.cometStartPosition();
                    comJ.cometStartPosition();
                    boom.draw(batch);
                }
            }
        }
        batch.end();
        //Посадка на луну
        if (moon.pos.cpy().sub(saucer.pos).len() < 0.15f)
            saucerOnTheMoon = true;
        //Столкновение корабля с кометой
        for (int i = 0; i < quantityComet; i++) {
            Comet com = cometList.get(i);
            baseVector.set(com.pos);
            if (baseVector.sub(saucer.pos).len() < 0.03f) {
                batch.begin();
                boom.pos.set(saucer.pos);
                boom.draw(batch);
                batch.end();
                game.setScreen(new History(game, 0, 6));
            }
        }
        //Стартовая область безопасности
        if (saucer.saveZone())
            for (int i = 0; i < quantityComet; i++) {
                Comet com = cometList.get(i);
                com.saveZone();
            }
        //Посадка тарелки
        if (saucerLandedOnTheMoon)
            game.setScreen(new History(game, 2, difficultyLevel));
    }

    @Override
    protected void resize(Rect worldBounds) {
        saucer.resize(worldBounds);
        moon.resize(worldBounds);
        boom.resize(worldBounds);
        circleSmall.resize(worldBounds);
        joyStick.resize(worldBounds);
        for (int i = 0; i < quantityComet; i++) {
            cometList.get(i).resize(worldBounds);
        }
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        joyStick.touchDown(touch, pointer);
        return super.touchDown(touch, pointer);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        joyStick.touchUp(touch, pointer);
        return super.touchUp(touch, pointer);
    }

    @Override
    public boolean touchDragged(Vector2 touch, int pointer) {
        joyStick.touchDragged(touch, pointer);
        return super.touchDragged(touch, pointer);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}