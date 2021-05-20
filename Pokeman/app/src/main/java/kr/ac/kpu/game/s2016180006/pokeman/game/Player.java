package kr.ac.kpu.game.s2016180006.pokeman.game;


import android.graphics.Canvas;

import kr.ac.kpu.game.s2016180006.pokeman.R;
import kr.ac.kpu.game.s2016180006.pokeman.framework.bitmap.IndexedAnimationGameBitmap;
import kr.ac.kpu.game.s2016180006.pokeman.framework.game.BaseGame;
import kr.ac.kpu.game.s2016180006.pokeman.framework.iface.GameObject;

public class Player implements GameObject {
    private static final String TAG = Player.class.getSimpleName();
    private static final float GRAVITY = 2000;
    private static final float JUMP_POWER = 1200;
    private final IndexedAnimationGameBitmap charBitmap;
    private final float ground_y;
    private float x, y;
    private int[] ANIM_INDICES_IDLE_L = {100, 101};
    private int[] ANIM_INDICES_IDLE_R = {102, 103};
    private int[] ANIM_INDICES_ATTACK_L = {0, 1};
    private int[] ANIM_INDICES_ATTACK_R = {2, 3};
    private float tx, ty;

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

    public Player(float x, float y) {
        this.x = x;
        this.y = y;
        this.ground_y = y;
        this.charBitmap = new IndexedAnimationGameBitmap(R.mipmap.pikaman, 4.5f, 0);
        setState(State.idlel);
    }

    public void moveTo(float x, float y) {
        this.tx = x;
        this.ty = this.y;
    }

    public void update() {
        BaseGame game = BaseGame.get();
    }

    public void draw(Canvas canvas) {
        charBitmap.draw(canvas,x,y);
    }

    public void attack(int direct) {

        if(direct == 1)
            this.state = state.attackl;
        else
            this.state = state.attackr;
    }
}