package kr.ac.kpu.game.s2016180006.pokeman.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016180006.pokeman.R;
import kr.ac.kpu.game.s2016180006.pokeman.framework.bitmap.GameBitmap;
import kr.ac.kpu.game.s2016180006.pokeman.framework.iface.GameObject;

public class Score implements GameObject {
    private final Bitmap bitmap;
    private final int right;
    private final int top;

    public void setScore(int score) {
        this.score = score;
        this.displayScore = score;
    }
    public int getScore() {
        return score;
    }

    public void addScore(int amount) {
        this.score += amount;
    }

    private int score, displayScore;
    Rect src = new Rect();
    RectF dst = new RectF();

    public Score(int right, int top){
        bitmap = GameBitmap.load(R.mipmap.number_24x32);
        this.right = right;
        this.top = top;
    }

    @Override
    public void update() {
        if(displayScore < score){
            displayScore++;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        int value = this.displayScore;
        int nw = bitmap.getWidth() / 10;
        int nh = bitmap.getHeight();
        int dw = (int)(nw * 3);
        int dh = (int)(nh * 3);
        int x = right + dw;
        while(value > 0){
            int digit = value % 10;
            src.set(digit * nw,0,(digit + 1)*nw,nh);
            x -= dw;
            dst.set(x, top, x + dw, top + dh);
            canvas.drawBitmap(bitmap, src, dst,null);

            value /= 10;
        }
    }
}
