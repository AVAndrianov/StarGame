package com.avandrinov.stargame;

import com.avandrinov.stargame.screen.MenuScreen;
import com.badlogic.gdx.Game;

public class StarGame extends Game {

    public void create() {
        setScreen(new MenuScreen(this));
    }
}
