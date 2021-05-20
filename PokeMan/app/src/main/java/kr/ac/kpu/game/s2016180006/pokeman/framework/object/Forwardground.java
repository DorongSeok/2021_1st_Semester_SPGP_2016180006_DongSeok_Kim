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
        int gap;
        if(bitmap.getHeight() > GameView.view.getHeight())
            gap = bitmap.getHeight() - GameView.view.getHeight();
        else
            gap = GameView.view.getHeight() - bitmap.getHeight();
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        srcRect.set(0,0, w, h);
        float l = 0;
        float r = GameView.view.getWidth();
        float t = 0;
        float b = gap + bitmap.getHeight();
        dstRect.set(l, t, r, b);
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, srcRect, dstRect, null);
    }
}