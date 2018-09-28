package com.avandrinov.stargame.base;

import com.avandrinov.stargame.math.Rect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Sprite extends Rect {

    protected float angle;
    protected float scale = 1f;
    protected ArrayList<TextureRegion> regions = new ArrayList<TextureRegion>();
    protected int frame = 0;
    protected int quantityFrame = 0;

    public Sprite() {

    }

    public void sprite(ArrayList<TextureRegion> regions) {
        if (regions == null)
            throw new NullPointerException("region == null");
        quantityFrame = regions.size();
        this.regions = regions;
    }

    public void sprite(ArrayList<TextureRegion> regions1, ArrayList<TextureRegion> regions2) {
        if (regions == null)
            throw new NullPointerException("region == null");
        regions1.addAll(regions2);
        quantityFrame = regions1.size();
        this.regions = regions1;
    }
    public void sprite(ArrayList<TextureRegion> regions1,
                       ArrayList<TextureRegion> regions2,
                       ArrayList<TextureRegion> regions3
    ) {
        if (regions == null)
            throw new NullPointerException("region == null");
        regions1.addAll(regions2);
        regions1.addAll(regions3);
        quantityFrame = regions1.size();
        this.regions = regions1;
    }

    public void setQuantityFrame(int quantityFrame) {
        this.quantityFrame = quantityFrame;
    }

    public void sprite(TextureRegion region) {
        if (regions == null)
            throw new NullPointerException("region == null");
        this.regions.add(region);
        quantityFrame = 1;

    }

    public void setHeightProportion(float height) {
        setHeight(height);
        float aspect = regions.get(frame).getRegionWidth() / (float) regions.get(frame).getRegionHeight();
        setWidth(height * aspect);
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public void resize(Rect worldBounds) {

    }

    protected void update(float delta) {

    }

    public boolean touchDown(Vector2 touch, int pointer) {
        return false;
    }

    public boolean touchUp(Vector2 touch, int pointer) {
        return false;
    }

    public boolean mouseMoved(Vector2 touch) {
        return false;
    }

    public boolean touchDragged(Vector2 touch, int pointer) {
        return false;
    }
    public boolean keyDown(int keycode) {
        return false;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(
                regions.get(frame),
                getLeft(), getBottom(),
                halfWidth, halfHeight,
                getWidth(), getHeight(),
                scale, scale,
                angle
        );
    }
}
