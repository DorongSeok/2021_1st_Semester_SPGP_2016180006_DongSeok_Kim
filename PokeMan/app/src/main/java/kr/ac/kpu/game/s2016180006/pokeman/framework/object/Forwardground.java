package kr.ac.kpu.game.s2016180006.pokeman.framework.object;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016180006.pokeman.framework.bitmap.GameBitmap;
import kr.ac.kpu.game.s2016180006.pokeman.framework.iface.GameObject;
import kr.ac.kpu.game.s2016180006.pokeman.framework.view.GameView;

public class Forwardground implements GameObject {
    private final Bitmap bitmap;
    private Rect srcRect = new Rect();
    private RectF dstRect = new RectF();

    public Forwardground(int resId){
        bitmap = GameBitmap.load(resId);
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        srcRect.set(0,0,w,h);
        float l = 0;
        float t = GameView.view.getHeight() - bitmap.getWidth();
        float b = GameView.view.getHeight();
        float r = GameView.view.getWidth();
        dstRect.set(l,t,r,b);
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, srcRect, dstRect, null);
    }
}