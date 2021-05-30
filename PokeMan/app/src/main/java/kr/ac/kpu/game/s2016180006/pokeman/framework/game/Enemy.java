package kr.ac.kpu.game.s2016180006.pokeman.framework.game;

import android.graphics.Canvas;

import kr.ac.kpu.game.s2016180006.pokeman.R;
import kr.ac.kpu.game.s2016180006.pokeman.framework.bitmap.IndexedGameBitmap;
import kr.ac.kpu.game.s2016180006.pokeman.framework.iface.GameObject;
import kr.ac.kpu.game.s2016180006.pokeman.framework.view.GameView;

public class Enemy implements GameObject {
    private final IndexedGameBitmap ibmp;
    private float x;
    private float y;
    public float dstY;
    public boolean isFalling;
    public int type;
    private static float FALLING_SPEED = 800;

    public Enemy(int index, float x, float y){
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
    @Override
    public void update() {
        BaseGame game = BaseGame.get();
        if(isFalling) {
            y += FALLING_SPEED * game.frameTime;
            if(y >= dstY) {
                y = dstY;
                isFalling = false;
                dstY += (20 * GameView.MULTIPLIER);
            }
        }
        if(y >= 1800) {
            game.remove(this);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        ibmp.draw(canvas, x, y);
    }
}
