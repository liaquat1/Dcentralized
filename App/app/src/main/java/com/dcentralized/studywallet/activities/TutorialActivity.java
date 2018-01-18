package com.dcentralized.studywallet.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.dcentralized.studywallet.R;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class TutorialActivity extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String color = "#F4AC3C";
        addSlide(AppIntroFragment.newInstance("Wallet", "Keep track of your coins.", R.drawable.tutorial_wallet, Color.parseColor(String.valueOf(color))));
        addSlide(AppIntroFragment.newInstance("Project Status", "Check the status of your projects.", R.drawable.tutorial_project_status, Color.parseColor(String.valueOf(color))));
        addSlide(AppIntroFragment.newInstance("Complete and Earn", "Earn coins by finishing projects.", R.drawable.tutorial_complete_and_earn, Color.parseColor(String.valueOf(color))));
        addSlide(AppIntroFragment.newInstance("Spend", "Spend your coins on amazing goodies.", R.drawable.tutorial_spend, Color.parseColor(String.valueOf(color))));

        showStatusBar(false);
        setBarColor(Color.parseColor(color));
        setSeparatorColor(Color.parseColor(color));
    }

    /**
     * Starts the main if the 'Done' button is pressed
     * @param fragment
     */
    @Override
    public void onDonePressed(Fragment fragment){
        startMain();
    }

    /**
     * Starts the main if the 'Skip' button is pressed
     * @param fragment
     */
    @Override
    public void onSkipPressed(Fragment fragment){
        startMain();
    }

    /**
     * Starts the main activity
     */
    public void startMain(){
        startActivity(new Intent(this, MainActivity.class));
    }
}
