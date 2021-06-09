package kr.ac.kpu.game.s2016180006.pokeman.game;


import android.graphics.Canvas;
import android.util.Log;

import kr.ac.kpu.game.s2016180006.pokeman.R;
import kr.ac.kpu.game.s2016180006.pokeman.framework.bitmap.IndexedAnimationGameBitmap;
import kr.ac.kpu.game.s2016180006.pokeman.framework.game.BaseGame;
import kr.ac.kpu.game.s2016180006.pokeman.framework.iface.GameObject;
import kr.ac.kpu.game.s2016180006.pokeman.framework.view.GameView;

public class Player implements GameObject {
    private static final String TAG = Player.class.getSimpleName();
    private static final int ATTACK_TIME = 30;
    private final IndexedAnimationGameBitmap charBitmap;
    public float x, y;
    private int[] ANIM_INDICES_IDLE_L = {100, 101};
    private int[] ANIM_INDICES_IDLE_R = {102, 103};
    private int[] ANIM_INDICES_ATTACK_L = {0, 1};
    private int[] ANIM_INDICES_ATTACK_R = {2, 3};
    private float lx, rx;
    private int attackTimeCount = 0;

    private enum State{
        idlel, idler, attackl, attackr,
    }

    public void setState(State state) {
        this.state = state;
        int[] indices = ANIM_INDICES_IDLE_L;
        switch (state) {
            case idlel : indices = ANIM_INDICES_IDLE_L; break;
            case idler: indices = ANIM_INDICES_IDLE_R; break;
            case attackl: indices = ANIM_INDICES_ATTACK_L; break;
            case attackr: indices = ANIM_INDICES_ATTACK_R; break;
        }
        charBitmap.setIndices(indices);
    }

    private State state = State.idlel;

    public Player(float y) {
        float center = GameView.view.getWidth() / 2;
        this.lx = center - 350;
        this.rx = center + 350;
        this.y = y;
        this.x = lx;
        this.charBitmap = new IndexedAnimationGameBitmap(R.mipmap.seeman, 5.5f, 0);
        setState(State.idlel);
    }

    public void update() {
        Log.d(TAG, "Player Update, attackTimeCount: " + attackTimeCount);
        if(attackTimeCount > 0) {
            attackTimeCount--;
            if(attackTimeCount <= 0) {
                attackTimeCount = 0;
                float centerX = GameView.view.getWidth() / 2;
                if(x < centerX) {
                    setState(state.idlel);
                }
                else {
                    setState(state.idler);
                }
            }
        }
        BaseGame game = BaseGame.get();
    }

    public void draw(Canvas canvas) {
        charBitmap.draw(canvas,x,y);
    }

    public void attack(float x) {
        float centerX = GameView.view.getWidth() / 2;
        attackTimeCount = ATTACK_TIME;
        if(x < centerX) {
            setState(state.attackl);
            this.x = lx;
        }
        else {
            setState(state.attackr);
            this.x = rx;
        }
    }
}