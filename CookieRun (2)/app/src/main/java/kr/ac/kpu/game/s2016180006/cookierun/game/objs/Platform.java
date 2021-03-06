package kr.ac.kpu.game.s2016180006.cookierun.game.objs;

import android.util.Log;

import java.util.Random;

import kr.ac.kpu.game.s2016180006.cookierun.R;
import kr.ac.kpu.game.s2016180006.cookierun.framework.game.BaseGame;
import kr.ac.kpu.game.s2016180006.cookierun.framework.object.ImageObject;
import kr.ac.kpu.game.s2016180006.cookierun.framework.view.GameView;
import kr.ac.kpu.game.s2016180006.cookierun.game.ctrl.StageMap;
import kr.ac.kpu.game.s2016180006.cookierun.game.scenes.main.MainScene;

public class Platform extends ImageObject {
    private static final String TAG = Platform.class.getSimpleName();
    //public static int UNIT_SIZE = 70;
    public static int SPEED = 300;

    public enum Type {
        T_10x2, T_2x2, T_3x1, RANDOM;

        public float width() {
            int w = 1;
            switch (this) {
                case T_10x2: w = 10; break;
                case T_2x2: w = 2; break;
                case T_3x1: w = 3; break;
            }
            return w * StageMap.UNIT_SIZE * GameView.MULTIPLIER;
        }
        public float height() {
            int h = 1;
            switch (this) {
                case T_10x2: case T_2x2: h = 2; break;
                case T_3x1: h = 1; break;
            }
            return h * StageMap.UNIT_SIZE * GameView.MULTIPLIER;
        }
        public int resId() {
            switch (this) {
                case T_10x2:
                    return R.mipmap.cookierun_platform_480x48;
                case T_2x2:
                    return R.mipmap.cookierun_platform_124x120;
                case T_3x1:
                    return R.mipmap.cookierun_platform_120x40;
                default:
                    Log.e(TAG, "This may not be called !!!");
                    return 0;
            }
        }
    }
    public Platform(Type type, float x, float y) {
        if (type == Type.RANDOM) {
            Random r = new Random();
            type = r.nextInt(2) == 0 ? Type.T_10x2 : Type.T_2x2;
        }
        init(type.resId(), x, y);
        float w = type.width();
        float h = type.height();
        dstRect.set(x, y, x + w, y + h);
        //Log.d(TAG, "Platform(" + type + ") " + dstRect);
    }

    @Override
    public void update() {
        BaseGame game = BaseGame.get();
        float dx = SPEED * GameView.MULTIPLIER * game.frameTime;
        dstRect.offset(-dx, 0);
        if (getRight() < 0) {
            MainScene.scene.remove(this);
        }
    }

}