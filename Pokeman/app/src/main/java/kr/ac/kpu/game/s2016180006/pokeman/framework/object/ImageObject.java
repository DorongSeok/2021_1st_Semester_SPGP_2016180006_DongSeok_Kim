package kr.ac.kpu.game.s2016180006.pokeman.framework.object;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016180006.pokeman.framework.iface.GameObject;
import kr.ac.kpu.game.s2016180006.pokeman.framework.view.GameView;
import kr.ac.kpu.game.s2016180006.pokeman.framework.bitmap.GameBitmap;

public class ImageObject implements GameObject {
    protected Bitmap bitmap;

    protected Rect srcRect = new Rect();
    protected RectF dstRect = new RectF();
    protected ImageObject() {}
    public ImageObject(int resId, float x, float y) {
        init(resId,x,y);
    }
    protected void init(int resId, float x, float y){
        bitmap = GameBitmap.load(resId);
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        srcRect.set(0,0,w,h);
        float l = x - w / 2 * GameView.MULTIPLIER;
        float t = y - h / 2 * GameView.MULTIPLIER;
        float r = x + w / 2 * GameView.MULTIPLIER;
        float b = y + h / 2 * GameView.MULTIPLIER;
        dstRect.set(l,t,r,b);
    }

    public float getRight() {
        return dstRect.right;
    }
    public float getBottom() {
        return dstRect.bottom;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap,srcRect,dstRect, null);
    }

    public float getDstWidth() {
        return dstRect.width();
    }

    public float getDstHeight() {
        return dstRect.height();
    }
}
