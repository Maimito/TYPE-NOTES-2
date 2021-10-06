package com.maimito.type_notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.maimito.type_notes.adapter.OnboardingAdapter;
import com.maimito.type_notes.adapter.SliderLoginAdapter;
import com.maimito.type_notes.model.OnboardingList;
import com.maimito.type_notes.model.SliderList;
import com.smarteist.autoimageslider.SliderView;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class OnBoardingActivity extends AppCompatActivity {
    private ViewPager viewpager;
    private Button skipTutorialButton;
    private LinearLayout dots;
    private Button nextBtn;
    private Button getStartedBtn;

    String[] title = {"Welcome", "Create and edit notes"};
    String[] desc = {"Welcome to TYPE-NOTES", "Start create your own notes!"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_on_boarding);
        nextBtn = findViewById(R.id.skip_btn);
        SliderView onboardingSlider = findViewById(R.id.slider_onboarding);
        List daftar = Data();
        onboardingSlider.setSliderAdapter(new OnboardingAdapter(getApplicationContext(), daftar));

        nextBtn.setOnClickListener(view -> {
            Intent intent = new Intent(OnBoardingActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private List Data(){
        ArrayList arrayList = new ArrayList<>();
        for(int i = 0; i < title.length; i++){
            OnboardingList onboardingList = new OnboardingList();
            onboardingList.setTitle(title[i]);
            onboardingList.setDesc(desc[i]);
            arrayList.add(onboardingList);
        }
        return arrayList;
    }
}