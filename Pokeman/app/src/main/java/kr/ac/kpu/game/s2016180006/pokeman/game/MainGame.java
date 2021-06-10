package kr.ac.kpu.game.s2016180006.pokeman.game;

import android.media.MediaPlayer;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Random;

import kr.ac.kpu.game.s2016180006.pokeman.R;
import kr.ac.kpu.game.s2016180006.pokeman.framework.game.BaseGame;
import kr.ac.kpu.game.s2016180006.pokeman.framework.game.Enemy;
import kr.ac.kpu.game.s2016180006.pokeman.framework.iface.GameObject;
import kr.ac.kpu.game.s2016180006.pokeman.framework.object.Forwardground;
import kr.ac.kpu.game.s2016180006.pokeman.framework.object.VerticalScrollBackground;
import kr.ac.kpu.game.s2016180006.pokeman.framework.utils.Sound;
import kr.ac.kpu.game.s2016180006.pokeman.framework.view.GameView;

public class MainGame extends BaseGame {
    private static final String TAG = MainGame.class.getSimpleName();
    private Player player;
    private Score score;
    private Score bestScore;
    private int bestScoreCnt = 0;
    private Health health;
    private Forwardground lobby;
    private Forwardground gameOverS;
    private boolean initialized;
    private static int MAX_ENEMY = 15;
    private static float attackPower = 55.f;
    private static float ADD_ENEMY_POSY = -1100;
    private static int level = 10;
    private static int healthCnt = 0;
    private static MediaPlayer mp;

    private int lastType;

    private boolean lobbyScene;
    private boolean gameScene;
    private boolean gameOverScene;
    private boolean gamePause;

    ArrayList<Enemy> Enemies = new ArrayList<>();

    public  enum Layer{
        bg, fg, enemy, player, ui, lobby , score, LAYER_COUNT
    }

    public void add(Layer layer, GameObject obj){
        add(layer.ordinal(),obj);
    }
    @Override
    public boolean initResources() {
        lobbyScene = true;
        gamePause = true;
        gameScene = false;
        gameOverScene = false;

        if (initialized) {
            return false;
        }
        int w = GameView.view.getWidth();
        int h = GameView.view.getHeight();

        initLayers(Layer.LAYER_COUNT.ordinal());

        player = new Player(h - 450);
        add(Layer.player, player);

        int center = GameView.view.getWidth() / 2;
        for(int i = 0; i < MAX_ENEMY; ++i){
            float x = center;
            float y = ADD_ENEMY_POSY + (i * 200);
            int type = 2;
            Enemy enemy = new Enemy(type, x, y, MAX_ENEMY - i);
            Enemies.add(i, enemy);
            add(Layer.enemy, enemy);
        }
        score = new Score(w / 2,  500);
        score.setScore(0);
        add(Layer.score, score);

        VerticalScrollBackground bg = new VerticalScrollBackground(R.mipmap.poke_bg, 10);
        add(Layer.bg, bg);

        Forwardground fg = new Forwardground(R.mipmap.poke_fg);
        add(Layer.fg, fg);

        lobby = new Forwardground(R.mipmap.lobby_scene);
        add(Layer.lobby, lobby);

        initialized = true;
        return true;
    }

    @Override
    public void update() {
        if(!gamePause) {
            healthCnt++;
            if (healthCnt % (level * 2) == 0) {
                healthCnt = 0;
                health.addHealth(-2);
                if (health.getHealth() == 0) {
                    gameOver();
                }
            }
            player.update();
            super.update();
        }
    }

    public void gameOver() {
        gameScene = false;
        gameOverScene = true;
        gamePause = true;

        Sound.play(R.raw.death_sound);

        if(gameOverS == null) {
            gameOverS = new Forwardground(R.mipmap.gameover_scene);
            add(Layer.lobby, gameOverS);
        }
        else {
            gameOverS.notRemove();
        }

        if(bestScore == null) {
            bestScore = new Score(GameView.view.getWidth() / 2, 985);

            if (bestScoreCnt < score.getScore()) {
                bestScoreCnt = score.getScore();
            }
            bestScore.setScore(bestScoreCnt);
            add(Layer.score, bestScore);
        }
        else{
            if (bestScoreCnt < score.getScore()) {
                bestScoreCnt = score.getScore();
            }
            bestScore.setScore(bestScoreCnt);
            bestScore.moveToScore(985);
        }
        score.moveToScore(1375);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            if (lobbyScene) {
//                Log.d(TAG, "x: " + event.getX() + " y: " + event.getY());

                if(event.getX() >= GameView.view.getWidth() / 2 - 350 && event.getX() <= GameView.view.getWidth() / 2 + 350 &&
                event.getY() >= 1880 && event.getY() <= 2080) {
                    lobbyScene = false;
                    gamePause = false;
                    gameScene = true;
                    health = new Health(GameView.view.getWidth() / 2, 300);
                    health.setHealth(100);
                    add(Layer.ui, health);

                    lobby.remove();

                    return true;
                }
            }
            else if (gameScene) {
                for (Enemy e : Enemies) {
                    e.isFalling = true;
                    if ((e.y >= 1500 && e.y <= 1700) || e.floor == 1 ) {
                        if (e.type == 0 && event.getX() < GameView.view.getWidth() / 2) {
                            gameOver();
                        } else if (e.type == 1 && event.getX() > GameView.view.getWidth() / 2) {
                            gameOver();
                        }
                    }
                    if (e.y >= 1700) {
                        Sound.play(R.raw.attack_sound);
                        Random r = new Random();
                        if (event.getX() > GameView.view.getWidth() / 2) {
                            e.setxSpeed(-(attackPower + r.nextInt(10)));
                        } else {
                            e.setxSpeed(attackPower + r.nextInt(10));
                        }
                        health.addHealth(5);
                    }
                    if(e.floor == 14){
                        lastType = e.type;
                    }
                }
                for (Enemy e : Enemies) {
                    e.lastType = lastType;
                }

                player.attack(event.getX());
                score.addScore(1);

                if(score.getScore() == 70) {
                    player.changeBitmap(R.mipmap.seeman);
                }
                else if(score.getScore() == 140){
                    player.changeBitmap(R.mipmap.pieman);
                }
                else if(score.getScore() == 210){
                    player.changeBitmap(R.mipmap.gangman);
                }
                if(score.getScore() != 0 && score.getScore() % 50 == 0){
                    level --;
                    Log.d(TAG, "LEVEL: " + level);
                    if(level < 3) {
                        level = 3;
                    }
                    for (Enemy e : Enemies) {
                        e.level = level;
                    }
                }
                return true;
            }
            else if (gameOverScene) {
                if(event.getY() >= 1880 && event.getY() <= 2080) {
                    if (event.getX() <= GameView.view.getWidth() / 2) {
                        gamePause = false;
                        gameOverScene = false;
                        gameScene = true;
                        score.setScore(0);
                        health.setHealth(100);
                        level = 10;
                        healthCnt = 0;
                        for (Enemy e : Enemies) {
                            e.type = 2;
                            e.lastType = 2;
                            e.x = GameView.view.getWidth() / 2;
                            e.setxSpeed(0);
                            e.isFalling = true;
                            e.bmpReset();
                            e.level = level;
                        }
                        gameOverS.remove();
                        score.moveToScore(500);
                        bestScore.remove();
                        player.changeBitmap(R.mipmap.pikaman);
                    }
                    else{
                        android.os.Process.killProcess(android.os.Process.myPid());
                    }
                }
                return true;
            }
        }
        return false;
    }
}
