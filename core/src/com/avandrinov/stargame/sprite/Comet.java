package com.avandrinov.stargame.sprite;

import com.avandrinov.stargame.base.Sprite;
import com.avandrinov.stargame.math.Rect;
import com.avandrinov.stargame.math.Rnd;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Comet extends Sprite {
    private TextureRegion comet;
    private Vector2 destination;
    private Vector2 destinationCopy;
    private Vector2 moveVector;
    private Vector2 angleVector;
    private int startPosition;
    private float moveX;
    private float moveY;
    private Rect worldBounds;
    private float maxSpeed = 0.005f;
    private float minSpeed = 0.002f;

    public Comet(TextureRegion comet) {
        this.comet = comet;
        moveVector = new Vector2(0f, 0f);
        destinationCopy = new Vector2();
        angleVector = new Vector2();
        startPosition = (int) (Rnd.nextFloat(0, 4));
        moveX = Rnd.nextFloat(-1.1f, 1.1f);
        moveY = Rnd.nextFloat(-0.6f, 0.6f);
        sprite(this.comet);
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(worldBounds.getHeight() / 15);
        this.worldBounds = worldBounds;
//        pos.set(0,0);
//        pos.set(worldBounds.pos).sub(
//                worldBounds.getHalfWidth() * Rnd.nextFloat(-0.5f, 0.5f),
//                Rnd.nextFloat(-0.3f, 0.3f));
        moveX = Rnd.nextFloat(worldBounds.getHalfWidth()*-1+0.2f, worldBounds.getWidth());
        moveY = Rnd.nextFloat(worldBounds.getHalfHeight()*-1+0.2f, worldBounds.getHeight());
        cometStartPosition();
    }

    public void cometStartPosition() {
        switch (startPosition) {
            case 0:
                pos.set(worldBounds.getHalfWidth() * Rnd.nextFloat(-1.1f, 1.1f), moveY);
                destination = new Vector2(
                        worldBounds.getHalfWidth() * Rnd.nextFloat(-2.2f, 2.2f),
                        0.6f);
                moveVector();
                setAngle(225 - (35 * angle("vertical")));
                moveY = -0.6f;
                break;
            case 1:
                pos.set(worldBounds.getHalfWidth() * Rnd.nextFloat(-1.1f, 1.1f), moveY);
                destination = new Vector2(
                        worldBounds.getHalfWidth() * Rnd.nextFloat(-2.2f, 2.2f),
                        -0.6f);
                moveVector();
                setAngle(45 - (35 * angle("vertical")));
                moveY = 0.6f;
                break;
            case 2:
                pos.set(worldBounds.getHalfWidth() * moveX, Rnd.nextFloat(-0.6f, 0.6f));
                destination = new Vector2(
                        worldBounds.getHalfWidth() * 1.1f,
                        Rnd.nextFloat(-1.2f, 1.2f));
                moveVector();
                setAngle(135 + (35 * angle("horizontal")));
                moveX = -1.1f;
                break;
            case 3:
                pos.set(worldBounds.getHalfWidth() * moveX, Rnd.nextFloat(-0.6f, 0.6f));
                destination = new Vector2(
                        worldBounds.getHalfWidth() * -1.1f,
                        Rnd.nextFloat(-1.2f, 1.2f));
                moveVector();
                setAngle(315 + (35 * angle("horizontal")));
                moveX = 1.1f;
        }
    }

    public void cometMovePosition() {
        switch (startPosition) {
            case 0:
                destinationCopy.set(destination);
                if (destinationCopy.sub(pos).len() > 0.005f)
                    pos.add(moveVector);
                else
                    cometStartPosition();
                break;
            case 1:
                destinationCopy.set(destination);
                if (destinationCopy.sub(pos).len() > 0.005f)
                    pos.add(moveVector);
                else
                    cometStartPosition();
                break;
            case 2:
                destinationCopy.set(destination);
                if (destinationCopy.sub(pos).len() > 0.005f)
                    pos.add(moveVector);
                else
                    cometStartPosition();
                break;
            case 3:
                destinationCopy.set(destination);
                if (destinationCopy.sub(pos).len() > 0.005f)
                    pos.add(moveVector);
                else
                    cometStartPosition();
                break;
        }
    }

    private void moveVector() {
        destinationCopy.set(destination);
        moveVector.set(
                destinationCopy.sub(pos).setLength(Rnd.nextFloat(maxSpeed, minSpeed)));
    }

    private float angle(String direction) {
        destinationCopy.set(destination);
        angleVector.set(
                destinationCopy.sub(pos).setLength(300f));
        if (direction.equals("vertical"))
            return angleVector.x / angleVector.y;
        else if (direction.equals("horizontal"))
            return angleVector.y / angleVector.x;
        else
            return 0;
    }

    public void collision() {

    }

    public void saveZone() {
        if (pos.x < worldBounds.getHalfWidth() *-1 + 0.3f && pos.y < -0.3f)
            cometStartPosition();
    }
}