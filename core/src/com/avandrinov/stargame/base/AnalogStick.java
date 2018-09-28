package com.avandrinov.stargame.base;

import com.avandrinov.stargame.base.Sprite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;

public class AnalogStick extends Sprite {
    private boolean showStick = false;
    private boolean up = false;
    private boolean down = false;
    private boolean left = false;
    private boolean right = false;
    private Vector2 touchCopy;
    private float speed;
    private float touchJoyStick;
    private Vector2 direction;

    protected AnalogStick() {
        touchCopy = new Vector2();
        direction = new Vector2();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        pos.set(touch);
        showStick = true;
        return super.touchDown(touch, pointer);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        touchJoyStick = 0;
        direction.set(0,0);
        showStick = false;
        up = false;
        down = false;
        left = false;
        right = false;
        return super.touchUp(touch, pointer);
    }

    @Override
    public boolean touchDragged(Vector2 touch, int pointer) {
        touchCopy.set(touch);
        touchJoyStick = touchCopy.sub(pos).len();
        if (touchJoyStick < 0.16f) {
            touchCopy.set(touch);
            direction.set(touchCopy.sub(pos));
            direction.set(direction.x / 25, direction.y / 25);
        }
        return super.touchDragged(touch, pointer);
    }

    public boolean isShowStick() {
        return showStick;
    }

    public float getSpeed() {
        return speed;
    }

    public Vector2 getDirection() {
        return direction;
    }

    public float getTouchJoyStick() {
        return touchJoyStick;
    }
}