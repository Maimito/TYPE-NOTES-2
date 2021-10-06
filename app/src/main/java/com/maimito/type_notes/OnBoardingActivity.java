package com.maimito.type_notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.smarteist.autoimageslider.SliderViewAdapter;

public class OnBoardingActivity extends AppCompatActivity {
    private ViewPager viewpager;
    private Button skipTutorialButton;
    private LinearLayout dots;
    private Button nextBtn;
    private Button getStartedBtn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_on_boarding);

        viewpager = (ViewPager) findViewById(R.id.viewpager);
        skipTutorialButton = (Button) findViewById(R.id.skip_tutorial_button);
        dots = (LinearLayout) findViewById(R.id.dots);
        nextBtn = (Button) findViewById(R.id.next_btn);
        getStartedBtn = (Button) findViewById(R.id.get_started_btn);

        nextBtn.setOnClickListener(view -> {

        });
    }
}