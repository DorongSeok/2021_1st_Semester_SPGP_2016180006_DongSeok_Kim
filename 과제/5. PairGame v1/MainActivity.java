package kr.ac.kpu.s2016180006.pairgame;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName(); // "MainActivity";
    private static final int[] buttonIds = {
            R.id.card_00, R.id.card_01, R.id.card_02, R.id.card_03,
            R.id.card_10, R.id.card_11, R.id.card_12, R.id.card_13,
            R.id.card_20, R.id.card_21, R.id.card_22, R.id.card_23,
            R.id.card_30, R.id.card_31, R.id.card_32, R.id.card_33,
    };

    private int[] cards = {
            R.mipmap.card_2c, R.mipmap.card_2c, R.mipmap.card_3d, R.mipmap.card_3d,
            R.mipmap.card_4h, R.mipmap.card_4h, R.mipmap.card_5s, R.mipmap.card_5s,
            R.mipmap.card_as, R.mipmap.card_as, R.mipmap.card_jc, R.mipmap.card_jc,
            R.mipmap.card_qh, R.mipmap.card_qh, R.mipmap.card_kd, R.mipmap.card_kd,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBtnCard(View view) {
        int buttonIndex = getButtonIndex(view.getId());
        Log.d(TAG,"onBtnCard() has been called. ID=" + view.getId() + " buttonIndex=" + buttonIndex);

        int card = cards[buttonIndex];
        ImageButton imageButton = (ImageButton)view;     // 타입 캐스팅 필요!! - onClick은 버튼뿐 아니라 여러곳에서 쓰기 때문에 인자가 view이다
        imageButton.setImageResource(card);
    }

    private int getButtonIndex(int resId) {
        for(int i = 0; i < buttonIds.length; ++i) {
            if(buttonIds[i] == resId) {
                return i;
            }
        }
        return -1;
    }

}