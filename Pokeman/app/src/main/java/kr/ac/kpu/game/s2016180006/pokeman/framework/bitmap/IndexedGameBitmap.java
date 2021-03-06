package kr.ac.kpu.game.s2016180006.pokeman.framework.bitmap;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016180006.pokeman.framework.view.GameView;

public class IndexedGameBitmap extends GameBitmap{
    private int width, xcount, height,  border,  spacing;
    public IndexedGameBitmap(int resId, int width, int height, int xcount, int border, int spacing){
        super(resId);
        this.width = width;
        this.height = height;
        this.xcount = xcount;
        this.border = border;
        this.spacing = spacing;
    }

    protected Rect srcRect = new Rect();
    public void setIndex(int index){
        int x = index % xcount;
        int y = index / xcount ;
        int l = border + x * (width + spacing);
        int t = border + y * (height + spacing);
        int r = l + width;
        int b = t + height;
        Rect rect = new Rect(l,t,r,b);
        srcRect = rect;
    }

    @Override
    public void draw(Canvas canvas, float x, float y) {
        float hw = (float)GameView.view.getWidth() / 2;
        float hh = (float)height / 2 * GameView.MULTIPLIER;

        dstRect.set(x - hw, y - hh, x + hw, y + hh);
        canvas.drawBitmap(bitmap, srcRect, dstRect, null);
    }

    public void draw(Canvas canvas, RectF dstRect){
        canvas.drawBitmap(bitmap, srcRect, dstRect,null);
    }
}
