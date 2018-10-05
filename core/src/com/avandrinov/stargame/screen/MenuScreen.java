package com.avandrinov.stargame.screen;

import com.avandrinov.stargame.base.ActionListener;
import com.avandrinov.stargame.base.BaseScreen;
import com.avandrinov.stargame.base.Sprite;
import com.avandrinov.stargame.math.MatrixUtils;
import com.avandrinov.stargame.math.Rect;
import com.avandrinov.stargame.sprite.ButtonSaucer;
import com.avandrinov.stargame.sprite.ButtonStart;
import com.avandrinov.stargame.sprite.Saucer;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class MenuScreen extends BaseScreen implements ActionListener {
    private ButtonStart buttonStart;
    private ButtonSaucer buttonSaucer;
    private Sound sound;

    public MenuScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        sound = Gdx.audio.newSound(Gdx.files.internal("sounds/menu.mp3"));
        buttonSaucer = new ButtonSaucer(3,this);
        buttonStart = new ButtonStart();
        sound.play(0.05f);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);
        batch.begin();
//        saucer.saucerLandedMenu();
        buttonSaucer.draw(batch);
        buttonStart.draw(batch);
        buttonSaucer.saucerLandedMenu();

        batch.end();
    }
    public void update(float delta) {
//        for (int i = 0; i < star.length; i++) {
//            star[i].update(delta);
//        }
        buttonSaucer.update(delta);
//        bulletPool.updateActiveObjects(delta);
    }
    @Override
    public void resize(Rect worldBounds) {
        buttonSaucer.resize(worldBounds);
        buttonStart.resize(worldBounds);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        if (buttonStart.touchUp(touch, pointer))
            game.setScreen(new History(game, 1,buttonSaucer.getDifficultyLevel()));
        return super.touchUp(touch, pointer);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        buttonSaucer.touchDown(touch,pointer);
        buttonStart.touchDown(touch, pointer);
        return super.touchDown(touch, pointer);
    }

    @Override
    public boolean mouseMoved(Vector2 touch) {
        buttonStart.mouseMoved(touch);
        return super.mouseMoved(touch);
    }

    @Override
    public boolean touchDragged(Vector2 touch, int pointer) {
        buttonStart.touchDragged(touch, pointer);
        return super.touchDragged(touch, pointer);
    }

    @Override
    public void dispose() {
        super.dispose();
        sound.dispose();
    }

    @Override
    public void actionPerformed(Object src) {

    }
}