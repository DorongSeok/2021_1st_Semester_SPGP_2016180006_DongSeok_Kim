package kr.ac.kpu.game.s2016180006.pokeman.game;

import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Random;

import kr.ac.kpu.game.s2016180006.pokeman.R;
import kr.ac.kpu.game.s2016180006.pokeman.framework.game.BaseGame;
import kr.ac.kpu.game.s2016180006.pokeman.framework.game.Enemy;
import kr.ac.kpu.game.s2016180006.pokeman.framework.iface.GameObject;
import kr.ac.kpu.game.s2016180006.pokeman.framework.object.Forwardground;
import kr.ac.kpu.game.s2016180006.pokeman.framework.object.VerticalScrollBackground;
import kr.ac.kpu.game.s2016180006.pokeman.framework.view.GameView;

public class MainGame extends BaseGame {
    private Player player;
    private Score score;
    private Health health;
    private boolean initialized;
    private static int MAX_ENEMY = 15;
    private static float attackPower = 55.f;
    private static float ADD_ENEMY_POSY = -1100;
    private static int level = 10;
    private static int healthCnt = 0;

    ArrayList<Enemy> Enemies = new ArrayList<>();

    public  enum Layer{
        bg, fg, enemy, player, ui, LAYER_COUNT
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

        player = new Player(h - 450);
        add(Layer.player, player);

        int center = GameView.view.getWidth() / 2;
        Random r = new Random();
        for(int i = 0; i < MAX_ENEMY; ++i){
            float x = center;
            float y = ADD_ENEMY_POSY + (i * 200);
            int type = r.nextInt(level);
            Enemy enemy = new Enemy(type, x, y, MAX_ENEMY - i);
            Enemies.add(i, enemy);
            add(Layer.enemy, enemy);
        }

        score = new Score(w / 2,  500);
        score.setScore(0);
        add(Layer.ui, score);

        health = new Health(w / 2, 300);
        health.setHealth(100);
        add(Layer.ui, health);

        VerticalScrollBackground bg = new VerticalScrollBackground(R.mipmap.poke_bg, 10);
        add(Layer.bg, bg);

        Forwardground fg = new Forwardground(R.mipmap.poke_fg);
        add(Layer.fg, fg);

        initialized = true;
        return true;
    }

    @Override
    public void update() {
        healthCnt++;
        if(healthCnt % level == 0){
            healthCnt = 0;
            health.addHealth(-2);
            if(health.getHealth() == 0){
                score.setScore(10000);
            }
        }
        player.update();
        super.update();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            for(Enemy e : Enemies)
            {
                e.isFalling = true;
                if(e.y >= 1500 && e.y <= 1700){
                    if(e.type == 0 && event.getX() < GameView.view.getWidth() / 2) {
                        score.setScore(0);
                    }
                    else if (e.type == 2 && event.getX() > GameView.view.getWidth() / 2) {
                        score.setScore(0);
                    }
                }
                if(e.y >= 1700) {
                    Random r = new Random();
                    if (event.getX() > GameView.view.getWidth() / 2) {
                        e.setxSpeed(-(attackPower + r.nextInt(10)));
                    } else {
                        e.setxSpeed(attackPower + r.nextInt(10));
                    }
                    health.addHealth(5);
                }
            }

            player.attack(event.getX());
            score.addScore(1);
            if(score.getScore() % 50 == 0){
                level --;
                if(level < 3){
                    level = 3;
                }
            }
            return true;
        }
        return false;
    }
}
