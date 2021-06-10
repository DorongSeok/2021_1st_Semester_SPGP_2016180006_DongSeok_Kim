package kr.ac.kpu.game.s2016180006.pokeman.framework.game;

import android.graphics.Canvas;

import java.util.Random;

import kr.ac.kpu.game.s2016180006.pokeman.R;
import kr.ac.kpu.game.s2016180006.pokeman.framework.bitmap.IndexedGameBitmap;
import kr.ac.kpu.game.s2016180006.pokeman.framework.iface.GameObject;
import kr.ac.kpu.game.s2016180006.pokeman.framework.view.GameView;

public class Enemy implements GameObject {
    private final IndexedGameBitmap ibmp;
    private float x;
    public float y;
    public boolean isFalling;
    public int type;
    private static float FALLING_SPEED = 1400;
    public int floor;
    private float xSpeed;
    private float[] dstY = {1900.f, 1700.f, 1500.f, 1300.f, 1100.f, 900.f, 700.f, 500.f, 300.f, 100.f, -100.f, -300.f, -500.f, -700.f, -900.f, -1100.f};

    public Enemy(int index, float x, float y, int floor){
        this.xSpeed = 0;
        this.floor = floor;
        this.x = x;
        this.y = y;
        this.isFalling = true;
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
        if(this.xSpeed == 0) {
            this.xSpeed = xSpeed;
        }
    }

    @Override
    public void update() {
        BaseGame game = BaseGame.get();
        if(isFalling && floor >= 0) {
            y += FALLING_SPEED * game.frameTime;
            if(y >= dstY[floor]) {
                y = dstY[floor];
                isFalling = false;
                floor--;
                if(floor < 0){
                    Random r = new Random();
                    xSpeed = 0;
                    floor += 15;
                    x = GameView.view.getWidth() / 2;
                    y -= 3000;
                    isFalling = false;
                    type = r.nextInt(8);
                    if(this.type == 0)
                        ibmp.setIndex(0);
                    else if(this.type == 1)
                        ibmp.setIndex(2);
                    else
                        ibmp.setIndex(1);
                }
            }
        }
        if(xSpeed != 0) {
            x += xSpeed;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        ibmp.draw(canvas, x, y + 80);
    }
}
