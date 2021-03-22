package kr.ac.kpu.game.s2016180006.ImageSwitcher;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mainTextView;
    private ImageView mainImageView;
    int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainTextView = findViewById(R.id.mainTextView);
        mainImageView = findViewById(R.id.mainImageView);

        Button prevButton = findViewById(R.id.preiousButton);
        Button nextButton = findViewById(R.id.NextButton);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void onBtnPrev(View view) {
        if(page > 1)
            page--;
        if (page == 1) {
            mainTextView.setText("1 / 5");
            mainImageView.setImageResource(R.drawable.cat1);
        }
        if (page == 2) {
            mainTextView.setText("2 / 5");
            mainImageView.setImageResource(R.drawable.cat2);
        }
        if (page == 3) {
            mainTextView.setText("3 / 5");
            mainImageView.setImageResource(R.drawable.cat3);
        }
        if (page == 4) {
            mainTextView.setText("4 / 5");
            mainImageView.setImageResource(R.drawable.cat4);
        }
        if (page == 5) {
            mainTextView.setText("5 / 5");
            mainImageView.setImageResource(R.drawable.cat5);
        }
    }

    public void onBtnNext(View view) {
        if(page < 5)
            page++;
        if (page == 1) {
            mainTextView.setText("1 / 5");
            mainImageView.setImageResource(R.drawable.cat1);
        }
        if (page == 2) {
            mainTextView.setText("2 / 5");
            mainImageView.setImageResource(R.drawable.cat2);
        }
        if (page == 3) {
            mainTextView.setText("3 / 5");
            mainImageView.setImageResource(R.drawable.cat3);
        }
        if (page == 4) {
            mainTextView.setText("4 / 5");
            mainImageView.setImageResource(R.drawable.cat4);
        }
        if (page == 5) {
            mainTextView.setText("5 / 5");
            mainImageView.setImageResource(R.drawable.cat5);
        }
    }
}