package kr.ac.kpu.game.s2016180006.pokeman;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import kr.ac.kpu.game.s2016180006.pokeman.game.MainGame;

public class MainActivity extends AppCompatActivity {

    private MainGame mainGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mainGame = new MainGame();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}