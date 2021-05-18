package kr.ac.kpu.game.s2016180006.pokeman.framework.bitmap;

import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;

import kr.ac.kpu.game.s2016180006.pokeman.framework.view.GameView;

public class IndexedAnimationGameBitmap extends AnimationGameBitmap{
    private final int frameHeight;

    public IndexedAnimationGameBitmap(int resId, float framePerSecond, int frameCount){
        super(resId, framePerSecond, frameCount);
        this.frameWidth = 270;
        this.frameHeight = 270;
    }

    protected ArrayList<Rect> srcRects;
    public void setIndices(int... indices){
        srcRects = new ArrayList<>();
        for(int index : indices){
            int x = index % 100;
            int y = index / 100;
            int l = 2 + x * 272;
            int t = 2 + y * 272;
            int r = l + 270;
            int b = t + 270;
            Rect rect = new Rect(l, t, r, b);
            srcRects.add(rect);
        }
        frameCount = indices.length;
    }

    @Override
    public void draw(Canvas canvas, float x, float y) {
        int elapsed = (int)(System.currentTimeMillis() - createdOn);
        frameIndex = Math.round(elapsed * 0.001f * framesPerSecond) % frameCount;

        int fw = frameWidth;
        int h = frameHeight;
        float hw = fw / 2 * GameView.MULTIPLIER;
        float hh = h / 2 * GameView.MULTIPLIER;

        dstRect.set(x - hw, y - hh, x + hw, y + hh);
        canvas.drawBitmap(bitmap, srcRects.get(frameIndex), dstRect, null);
    }
}