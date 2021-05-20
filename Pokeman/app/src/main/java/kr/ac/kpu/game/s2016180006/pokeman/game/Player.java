package kr.ac.kpu.game.s2016180006.pokeman.game;


import android.graphics.Canvas;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016180006.pokeman.R;
import kr.ac.kpu.game.s2016180006.pokeman.framework.iface.GameObject;
import kr.ac.kpu.game.s2016180006.pokeman.framework.bitmap.IndexedAnimationGameBitmap;
import kr.ac.kpu.game.s2016180006.pokeman.framework.game.BaseGame;

public class Player implements GameObject {
    private static final String TAG = Player.class.getSimpleName();
    private static final float GRAVITY = 2000;
    private static final float JUMP_POWER = 1200;
    private final IndexedAnimationGameBitmap charBitmap;
    private final float ground_y;
    private float x, y;
    private float vertSpeed;
    private int[] ANIM_INDICES_RUNNING = {100, 101, 102, 103};
    private int[] ANIM_INDICES_JUMP = {7,8};
    private int[] ANIM_INDICES_DOUBLE_JUMP = {1,2,3,4};

    private enum State{
        running, jump, doublejump,slide,hit,
    }

    public void setState(State state) {
        this.state = state;
        int[] indices = ANIM_INDICES_RUNNING;
        switch (state) {
            case running : indices = ANIM_INDICES_RUNNING;break;
            case jump: indices = ANIM_INDICES_JUMP;break;
            case doublejump: indices = ANIM_INDICES_DOUBLE_JUMP;break;
        }
        charBitmap.setIndices(indices);
    }

    private State state = State.running;

    public Player(float x, float y) {
        this.x = x;
        this.y = y;
        this.ground_y = y;
        this.charBitmap = new IndexedAnimationGameBitmap(R.mipmap.cookie, 4.5f, 0);
        setState(State.running);
    }

    public void moveTo(float x, float y) {
        this.tx = x;
        //this.ty = this.y;
    }

    public void update() {
        BaseGame game = BaseGame.get();
        if(state == State.jump || state == State.doublejump) {
            float y = this.y + vertSpeed * game.frameTime;
            vertSpeed += GRAVITY * game.frameTime;
            if(y >= ground_y){
                y = ground_y;
                setState(State.running);
            }
            this.y = y;
        }
    }

    public void draw(Canvas canvas) {
        charBitmap.draw(canvas,x,y);
    }

    public void jump() {
//        if(state != State.running && state != State.jump && state != State.slide){
//            return;
//        }
        if (state == State.running){
            setState(State.jump);
            vertSpeed = -JUMP_POWER;
        }
        else if (state == State.jump){
            setState(State.doublejump);
            vertSpeed = -JUMP_POWER;
        } else{
            return;
        }
    }
}