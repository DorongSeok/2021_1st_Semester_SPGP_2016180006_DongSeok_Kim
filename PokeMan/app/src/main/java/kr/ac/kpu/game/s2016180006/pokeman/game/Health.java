package kr.ac.kpu.game.s2016180006.pokeman.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016180006.pokeman.R;
import kr.ac.kpu.game.s2016180006.pokeman.framework.bitmap.GameBitmap;
import kr.ac.kpu.game.s2016180006.pokeman.framework.iface.GameObject;
import kr.ac.kpu.game.s2016180006.pokeman.framework.view.GameView;

public class Health implements GameObject {
    private final Bitmap bBitmap;
    private final Bitmap fBitmap;
    private final float x;
    private final float y;

    public void setHealth(int health) {
        this.health = health;
        this.displayHealth = health;
    }

    public int getHealth() {
        return health;
    }

    public void addHealth(int amount) {
        this.health += amount;
        if(health < 0)
            health = 0;
        if(health > 100)
            health = 100;
    }

    private int health, displayHealth;
    Rect src = new Rect();
    RectF dst = new RectF();

    public Health(float x, float y) {
        bBitmap = GameBitmap.load(R.mipmap.health_bar);
        fBitmap = GameBitmap.load(R.mipmap.health);
        this.x = x;
        this.y = y;
    }

    @Override
    public void update() {
        if(displayHealth < health){
            displayHealth += 3;
            if(displayHealth > health){
                displayHealth = health;
            }
        }
        else if(displayHealth > health) {
            displayHealth -= 3;
            if(displayHealth < health){
                displayHealth = health;
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        float bw = bBitmap.getWidth();
        float bh = bBitmap.getHeight();
        src.set(0,0, (int)bw, (int)bh);
        dst.set(x - (bw * GameView.MULTIPLIER) / 2, y - (bh * GameView.MULTIPLIER) / 2, x + (bw * GameView.MULTIPLIER) / 2, y + (bh * GameView.MULTIPLIER) / 2);
        canvas.drawBitmap(bBitmap, src, dst,null);

        float fw = fBitmap.getWidth();
        float fh = fBitmap.getHeight();

        float value = fw * this.displayHealth / 100 * GameView.MULTIPLIER;
        float left = x - (fw * GameView.MULTIPLIER) / 2;
        src.set(0,0, (int)fw, (int)fh);
        dst.set(left, y - (fh * GameView.MULTIPLIER) / 2, left + value, y + (fh * GameView.MULTIPLIER) / 2);
        canvas.drawBitmap(fBitmap, src, dst,null);
    }
}
