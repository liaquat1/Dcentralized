package com.dcentralized.studywallet.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dcentralized.studywallet.R;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class TutorialActivity extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String color = "#F4AC3C";
        addSlide(AppIntroFragment.newInstance("Wallet", "Keep track of your coins.", R.drawable.tutorial_test, Color.parseColor(String.valueOf(color))));
        addSlide(AppIntroFragment.newInstance("Project Status", "Check the status of your projects.", R.drawable.tutorial_test, Color.parseColor(String.valueOf(color))));
        addSlide(AppIntroFragment.newInstance("Complete and Earn", "Earn coins by finishing projects.", R.drawable.tutorial_test, Color.parseColor(String.valueOf(color))));
        addSlide(AppIntroFragment.newInstance("Spend", "Spend your coins on amazing goodies.", R.drawable.tutorial_test, Color.parseColor(String.valueOf(color))));

        showStatusBar(false);
        setBarColor(Color.parseColor(color));
        setSeparatorColor(Color.parseColor(color));
    }

    @Override
    public void onDonePressed(){
        startMain();
    }

    @Override
    public void onSkipPressed(){
        startMain();
    }

    public void startMain(){
        startActivity(new Intent(this, MainActivity.class));
    }
}
