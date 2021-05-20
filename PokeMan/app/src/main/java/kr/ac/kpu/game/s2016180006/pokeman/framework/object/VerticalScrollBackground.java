package kr.ac.kpu.game.s2016180006.pokeman.framework.object;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016180006.pokeman.framework.bitmap.GameBitmap;
import kr.ac.kpu.game.s2016180006.pokeman.framework.game.BaseGame;
import kr.ac.kpu.game.s2016180006.pokeman.framework.iface.GameObject;
import kr.ac.kpu.game.s2016180006.pokeman.framework.view.GameView;

public class VerticalScrollBackground implements GameObject {
    private final Bitmap bitmap;
    private final float speed;
    private Rect srcRect = new Rect();
    private RectF dstRect = new RectF();
    private float scroll;

    public VerticalScrollBackground(int resId, int speed){
        this.speed = speed * GameView.MULTIPLIER;
        bitmap = GameBitmap.load(resId);
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        srcRect.set(0,0,w,h);
        float l = 0;// x - w / 2 * GameView.MULTIPLIER;
        float t = 0;// - h / 2 * GameView.MULTIPLIER;
        float r = GameView.view.getWidth();
        float b = r * h / w;
        dstRect.set(l,t,r,b);
    }

    @Override
    public void update() {
        BaseGame game = BaseGame.get();
        float amount = speed * game.frameTime;
        scroll += amount;
    }

    @Override
    public void draw(Canvas canvas) {
        int vw = GameView.view.getWidth();
        int vh = GameView.view.getHeight();
        int iw = bitmap.getWidth();
        int ih = bitmap.getHeight();
        int dh = vw * ih / iw;
        int curr = (int)(scroll % dh);
        if(curr > 0) curr -= dh;

        while(curr < vh){
            dstRect.set(0,curr,vw,curr+dh);
            canvas.drawBitmap(bitmap,srcRect,dstRect, null);
            curr += dh;
        }
    }
}