package com.avandrinov.stargame.base;

import com.avandrinov.stargame.math.MatrixUtils;
import com.avandrinov.stargame.math.Rect;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;


public class BaseScreen implements Screen, InputProcessor {

    protected Game game;
    private Rect screenBounds; // границы области рисования в пикселях
    private Rect worldBounds; // граница проэкции мировых координат
    private Rect glBounds; // дефолтные границы OpenGl
    protected Matrix4 worldToGl;
    protected SpriteBatch batch;


    public BaseScreen(Game game) {
        this.game = game;
        Gdx.input.setInputProcessor(this);
        this.batch = new SpriteBatch();
        this.screenBounds = new Rect();
        this.worldBounds = new Rect();
        this.glBounds = new Rect(0, 0, 1f, 1f);
        this.worldToGl = new Matrix4();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {
//        screenBounds.setSize(width, height);
//        screenBounds.setLeft(0);
//        screenBounds.setBottom(0);
//
//        float aspect = width / (float) height;
//        worldBounds.setHeight(1f);
//        worldBounds.setWidth(1f * aspect);
//
//        MatrixUtils.calcTransitionMatrix(worldToGl, worldBounds, glBounds);
//        batch.setProjectionMatrix(worldToGl);
//
//        resize(worldBounds);

    }

    protected void resize(Rect worldBounds){
        System.out.println("resize width="
                + worldBounds.getWidth()
                + " height="
                + worldBounds.getHeight()
        );
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {


        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println(screenX+"  "+screenY);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
