package kr.ac.kpu.game.s2016180006.cookierun.game.scenes.main;

import kr.ac.kpu.game.s2016180006.cookierun.framework.game.BaseGame;

public class MainGame extends BaseGame {
    private boolean initialized;

    public static MainGame get() {
        return (MainGame) instance;
    }


    @Override
    public boolean initResources() {
        if (initialized) {
            return false;
        }

        push(new MainScene());

        initialized = true;
        return true;

    }

}