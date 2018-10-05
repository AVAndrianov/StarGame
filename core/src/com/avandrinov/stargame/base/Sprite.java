package com.avandrinov.stargame.base;

import com.avandrinov.stargame.math.Rect;
import com.avandrinov.stargame.utils.Regions;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Sprite extends Rect {

    protected float angle;
    protected float scale = 1f;
    protected ArrayList<TextureRegion> regions;
    protected int frame = 0;
    protected int quantityFrame = 0;
    protected boolean isDestroyed;

    public Sprite() {

    }

    public Sprite(TextureRegion textureRegion) {
        regions.add(textureRegion);
    }

    public void sprite(ArrayList<TextureRegion> regions) {
        this.regions = new ArrayList<TextureRegion>();
        if (regions == null)
            throw new NullPointerException("region == null");
        quantityFrame = regions.size();
        this.regions = regions;
    }


    public void sprite(ArrayList<TextureRegion> regions1, ArrayList<TextureRegion> regions2) {
        this.regions = new ArrayList<TextureRegion>();

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
        this.regions = new ArrayList<TextureRegion>();
        if (regions == null)
            throw new NullPointerException("region == null");
        regions1.addAll(regions2);
        regions1.addAll(regions3);
        quantityFrame = regions1.size();
        this.regions = regions1;
    }
    public void sprite(TextureRegion region) {
        this.regions = new ArrayList<TextureRegion>();

        if (regions == null)
            throw new NullPointerException("region == null");
        this.regions.add(region);
        quantityFrame = 1;

    }

    public void setQuantityFrame(int quantityFrame) {
        this.quantityFrame = quantityFrame;
    }


    public Sprite(TextureRegion region, int rows, int cols, int frames) {
        this.regions = Regions.split(region, rows, cols, frames);
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

    public void update(float delta) {

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
    public void destroyed() {
        this.isDestroyed = true;
    }
    public void flushDestroyed() {
        this.isDestroyed = false;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }
}
