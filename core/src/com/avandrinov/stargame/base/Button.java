package com.avandrinov.stargame.base;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Button extends Sprite {
    private boolean pressed;
    private int pointer;
    private ActionListener actionListener;

    public Button(TextureRegion textureRegion,ActionListener actionListener) {
        this.actionListener = actionListener;

    }
    public Button() {

    }

    @Override
    public boolean touchDown(Vector2 touch,  int pointer) {
        if (pressed || !isMe(touch)) {
//            this.frame = 0;
            return false;
        } else {
            this.pointer = pointer;
//            this.pressed = true;
            if (!this.pressed)
                if (frame + 1 < quantityFrame) {
                    frame++;
                } else
                    frame = 0;
        }
        return super.touchDown(touch, pointer);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        if (this.pointer != pointer || pressed)
            return false;
        if (isMe(touch)) {
//            actionListener.actionPerformed(this);
            return true;
        }
        return super.touchUp(touch, pointer);
    }

    @Override
    public boolean mouseMoved(Vector2 touch) {
        return super.mouseMoved(touch);
    }
}
