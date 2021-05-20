package kr.ac.kpu.game.s2016180006.pokeman;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import kr.ac.kpu.game.s2016180006.pokeman.game.MainGame;

public class MainActivity extends AppCompatActivity {
    private MainGame maingame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}