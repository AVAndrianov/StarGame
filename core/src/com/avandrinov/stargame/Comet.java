package com.avandrinov.stargame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Comet {
    private TextureRegion regionComet;
    private Vector2 posComet;
    private int windowHeight;
    private int windowWidth;
    private int behindTheScreen = 30;
    private int startPosition;
    private int rotationComet;
    private float moveX;
    private float moveY;

    public Comet(int windowHeight, int windowWidth) {
        regionComet = new TextureRegion(new Texture(Gdx.files.internal("comet.png")));
        this.windowHeight = windowHeight;
        this.windowWidth = windowWidth;
        startPosition = (int) (Math.random() * 4);
        cometStartPosition();
        posComet.set((int) (Math.random() * (windowWidth - 80) + 40),
                (int) (Math.random() * (windowHeight - 80) + 40));
    }

    public int getRotationComet() {
        return rotationComet;
    }

    public void cometStartPosition() {
        switch (startPosition) {
            case 0:
                posComet = new Vector2((float) (Math.random() * windowWidth),
                        behindTheScreen * -1);
                moveX = (float) (Math.random() + Math.random() * -1);
                moveY = 1;
                rotationComet = (int) (90 + (90 * -moveX) + 180 - 45);
                break;
            case 1:
                posComet = new Vector2((float) (Math.random() * windowWidth),
                        windowHeight + behindTheScreen);
                moveX = (float) (Math.random() + Math.random() * -1);
                moveY = -1;
                rotationComet = (int) (90 + (90 * moveX) - 45);
                break;
            case 2:
                posComet = new Vector2(behindTheScreen * -1,
                        (float) (Math.random() * windowHeight));
                moveX = 1;
                moveY = (float) (Math.random() + Math.random() * -1);
                rotationComet = (int) (90 + (90 * moveY) + 90 - 45);
                break;
            case 3:
                posComet = new Vector2(windowWidth + behindTheScreen,
                        (float) (Math.random() * windowHeight));
                moveX = -1;
                moveY = (float) (Math.random() + Math.random() * -1);
                rotationComet = (int) (90 + (90 * -moveY) + 270 - 45);
        }
    }

    public void cometMovePosition() {
        switch (startPosition) {
            case 0:
                if (posComet.y < windowHeight + behindTheScreen
                        && posComet.x > behindTheScreen * -1
                        && posComet.x < windowWidth + behindTheScreen)
                    posComet.add(moveX * 2, moveY);
                else
                    cometStartPosition();
                break;
            case 1:
                if (posComet.y > behindTheScreen * -1
                        && posComet.x < windowWidth + behindTheScreen
                        && posComet.x > behindTheScreen * -1)
                    posComet.add(moveX * 2, moveY);
                else
                    cometStartPosition();
                break;
            case 2:
                if (posComet.x < windowWidth + behindTheScreen
                        && posComet.y > behindTheScreen * -1
                        && posComet.y < windowHeight + behindTheScreen)
                    posComet.add(moveX, moveY * 2);
                else
                    cometStartPosition();
                break;
            case 3:
                if (posComet.x > behindTheScreen * -1
                        && posComet.y < windowHeight + behindTheScreen
                        && posComet.y > behindTheScreen * -1)
                    posComet.add(moveX, moveY * 2);
                else
                    cometStartPosition();
                break;
        }
    }

    public TextureRegion getRegionComet() {
        return regionComet;
    }

    public Vector2 getPosComet() {
        return posComet;
    }

    public void setPosComet(Vector2 posComet) {
        this.posComet = posComet;
    }
}
