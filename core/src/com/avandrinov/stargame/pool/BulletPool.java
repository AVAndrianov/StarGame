package com.avandrinov.stargame.pool;

import com.avandrinov.stargame.base.SpritesPool;
import com.avandrinov.stargame.sprite.Bullet;

public class BulletPool extends SpritesPool<Bullet> {



    @Override
    protected Bullet newObject() {
        return new Bullet();
    }

    @Override
    protected void log() {
        System.out.println("Bullet active/free: " + activeObject.size() + "/" + freeObject.size());
    }
}
