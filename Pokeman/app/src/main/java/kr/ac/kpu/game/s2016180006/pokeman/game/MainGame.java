package kr.ac.kpu.game.s2016180006.pokeman.game;

import android.view.MotionEvent;

import kr.ac.kpu.game.s2016180006.pokeman.R;
import kr.ac.kpu.game.s2016180006.pokeman.framework.game.BaseGame;
import kr.ac.kpu.game.s2016180006.pokeman.framework.iface.GameObject;
import kr.ac.kpu.game.s2016180006.pokeman.framework.object.Forwardground;
import kr.ac.kpu.game.s2016180006.pokeman.framework.object.VerticalScrollBackground;
import kr.ac.kpu.game.s2016180006.pokeman.framework.view.GameView;

public class MainGame extends BaseGame {
    private Player player;
    private Score score;
    private boolean initialized;

    public  enum Layer{
        bg, fg, player, ui, LAYER_COUNT
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

        player = new Player(200, h - 400);
        add(Layer.player, player);

        int margin = (int)(20*GameView.MULTIPLIER);
        score = new Score(w - margin, margin);
        score.setScore(0);
        add(Layer.ui, score);

        VerticalScrollBackground bg = new VerticalScrollBackground(R.mipmap.poke_bg, 50);
        add(Layer.bg, bg);

        Forwardground fg = new Forwardground(R.mipmap.poke_fg);
        add(Layer.fg, fg);

        initialized = true;
        return true;
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            player.attack(1);
            return true;
        }
        return false;
    }
}
