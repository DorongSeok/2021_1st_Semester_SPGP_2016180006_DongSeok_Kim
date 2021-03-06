package kr.ac.kpu.game.s2016180006.pokeman.framework.bitmap;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;

import java.util.HashMap;

import kr.ac.kpu.game.s2016180006.pokeman.framework.view.GameView;


public class GameBitmap {
    private static HashMap<Integer, Bitmap> bitmaps = new HashMap<Integer, Bitmap>();

    public static Bitmap load(int resId) {
        Bitmap bitmap = bitmaps.get(resId);
        if (bitmap == null) {
            Resources res = GameView.view.getResources();
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inScaled = false;
            bitmap = BitmapFactory.decodeResource(res, resId, opts);
            bitmaps.put(resId, bitmap);
        }
        return bitmap;
    }

    protected Bitmap bitmap;
    protected RectF dstRect = new RectF();

    public GameBitmap(int resId){
        bitmap = load(resId);
    }

    public void changeBitmap(int resId){
        bitmap = load(resId);
    }

    public void draw(Canvas canvas, float x, float y){
        int hw = getWidth() / 2;
        int hh = getHeight() / 2;

        float dl = x - hw * 11;
        float dt = y - hh * GameView.MULTIPLIER;;
        float dr = x + hw * 11;
        float db = y + hh * GameView.MULTIPLIER;;
        dstRect.set(dl,dt,dr,db);
        canvas.drawBitmap(bitmap, null,dstRect, null);
    }

    public int getHeight(){
        return bitmap.getHeight();
    }

    public int getWidth(){
        return bitmap.getWidth();
    }
}
