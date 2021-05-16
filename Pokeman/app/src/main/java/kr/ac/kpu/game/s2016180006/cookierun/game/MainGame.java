package kr.ac.kpu.game.s2016180006.cookierun.game;

import android.view.MotionEvent;

import kr.ac.kpu.game.s2016180006.cookierun.R;
import kr.ac.kpu.game.s2016180006.cookierun.framework.game.BaseGame;
import kr.ac.kpu.game.s2016180006.cookierun.framework.game.Platform;
import kr.ac.kpu.game.s2016180006.cookierun.framework.iface.GameObject;
import kr.ac.kpu.game.s2016180006.cookierun.framework.object.HorizontalScrollBackground;
import kr.ac.kpu.game.s2016180006.cookierun.framework.view.GameView;

public class MainGame extends BaseGame {
    private Player player;
    private Score score;
    private boolean initialized;
    public  enum Layer{
        bg, platform, player,  ui, controller, LAYER_COUNT
    }

    public void add(Layer layer, GameObject obj){
        add(layer.ordinal(),obj);
    }
    @Override
    public boolean initResources() {
        if (initialized) {
            return false;
        }
        int w = GameView.view.getWidth();
        int h = GameView.view.getHeight();

        initLayers(Layer.LAYER_COUNT.ordinal());

        player = new Player(200, h - 300);
        //layers.get(Layer.player.ordinal()).add(player);
        add(Layer.player, player);
//        add(Layer.controller, new EnemyGenerator());

        int margin = (int)(20*GameView.MULTIPLIER);
        score = new Score(w - margin, margin);
        score.setScore(0);
        add(Layer.ui, score);

        add(Layer.bg, new HorizontalScrollBackground(R.mipmap.cookie_run_bg_1, -10));
        add(Layer.bg, new HorizontalScrollBackground(R.mipmap.cookie_run_bg_2, -20));
        add(Layer.bg, new HorizontalScrollBackground(R.mipmap.cookie_run_bg_3, -30));

        float tx = 100, ty = h - 500;
        while (tx < w) {
            Platform platform = new Platform(Platform.Type.T_10x2, tx, ty);
            add(Layer.platform, platform);
            tx = platform.getDstWidth();
        }

        initialized = true;
        return true;
    }

    @Override
    public void update() {
        super.update();

        // collision check
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
//        if (action == MotionEvent.ACTION_DOWN) {
        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE) {
            player.Jump();
            return true;
        }
        return false;
    }
}