package kr.ac.kpu.game.s2016180006.pokeman.framework.game;

import android.graphics.Canvas;

import kr.ac.kpu.game.s2016180006.pokeman.R;
import kr.ac.kpu.game.s2016180006.pokeman.framework.bitmap.IndexedGameBitmap;
import kr.ac.kpu.game.s2016180006.pokeman.framework.iface.GameObject;
import kr.ac.kpu.game.s2016180006.pokeman.framework.view.GameView;

public class Enemy implements GameObject {
    private final IndexedGameBitmap ibmp;
    private float x;
    public float y;
    public float dstY;
    public boolean isFalling;
    public int type;
    private static float FALLING_SPEED = 1400;
    public int floor;
    private float xSpeed = 0;

    public Enemy(int index, float x, float y, int floor){
        this.xSpeed = 0;
        this.floor = floor;
        this.x = x;
        this.y = y;
        this.dstY = y + (20 * GameView.MULTIPLIER);
        this.isFalling = false;
        ibmp = new IndexedGameBitmap(R.mipmap.enemy, 99, 20,3,0,0);
        this.type = index;
        if(index == 0)
            ibmp.setIndex(0);
        else if(index == 1)
            ibmp.setIndex(2);
        else
            ibmp.setIndex(1);
    }

    public void setxSpeed(float xSpeed) {
        this.xSpeed = xSpeed;
    }

    @Override
    public void update() {
        BaseGame game = BaseGame.get();
        if(isFalling) {
            if(y >= 1800) {
                y += (FALLING_SPEED / 2) * game.frameTime;
            }else {
                y += FALLING_SPEED * game.frameTime;
            }
            if(y >= dstY && floor > 0) {
                y = dstY;
                isFalling = false;
                floor--;
                dstY += (20 * GameView.MULTIPLIER);
            }
        }
        if(y >= 1800) {
            x += xSpeed;
        }
        if(x <= 0 || x >= GameView.view.getWidth()) {
//            game.remove(this);
            this.xSpeed = 0;
            this.floor = 12;
            this.x = GameView.view.getWidth() / 2;
            this.y = -220;
            this.dstY = y + (20 * GameView.MULTIPLIER);
            this.isFalling = false;
//            this.type = index;
//            if(this.type == 0)
//                ibmp.setIndex(0);
//            else if(this.type == 1)
//                ibmp.setIndex(2);
//            else
//                ibmp.setIndex(1);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        ibmp.draw(canvas, x, y);
    }
}
