package com.avandrinov.stargame.base;

import com.avandrinov.stargame.base.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public abstract class SpritesPool<T extends Sprite> {

    public List<T> activeObject = new ArrayList<T>();
    public List<T> freeObject = new ArrayList<T>();

    protected abstract T newObject();

    public T obtain() {
        T object;
        if (freeObject.isEmpty()) {
            object = newObject();
        } else {
            object = freeObject.remove(freeObject.size() - 1);
        }
        activeObject.add(object);
        log();
        return object;
    }

    public void updateActiveObject(float delta) {
        for (int i = 0; i < activeObject.size(); i++) {
            Sprite sprite = activeObject.get(i);
            if (!sprite.isDestroyed())
                sprite.update(delta);
        }
    }

    public void freeAllDestroyedActiveObject() {
        for (int i = 0; i < activeObject.size(); i++) {
            T sprite = activeObject.get(i);
            if (sprite.isDestroyed()) {
                free(sprite);
                i--;
                sprite.flushDestroyed();
            }
        }
    }

    private void free(T object) {
        if (activeObject.remove(object))
            freeObject.add(object);
        log();
    }

    public void drawActiveObject(SpriteBatch batch) {
        for (int i = 0; i < activeObject.size(); i++) {
            Sprite sprite = activeObject.get(i);
            if (!sprite.isDestroyed())
                sprite.draw(batch);
        }
    }

    public List<T> getActiveObject() {
        return activeObject;
    }

    public void dispose() {
        activeObject.clear();
        freeObject.clear();
    }

    protected void log() {

    }
}