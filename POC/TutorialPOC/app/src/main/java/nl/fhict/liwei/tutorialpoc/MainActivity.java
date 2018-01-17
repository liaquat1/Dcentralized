package nl.fhict.liwei.tutorialpoc;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class MainActivity extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addSlide(AppIntroFragment.newInstance("Wallet", "Keep track of your coins.", R.drawable.testerino, Color.parseColor("#F4AC3C")));
        addSlide(AppIntroFragment.newInstance("Project Status", "Check the status of your projects.", R.drawable.testerino, Color.parseColor("#F4AC3C")));
        addSlide(AppIntroFragment.newInstance("Complete and Earn", "Earn coins by finishing projects.", R.drawable.testerino, Color.parseColor("#F4AC3C")));
        addSlide(AppIntroFragment.newInstance("Spend", "Spend your coins on amazing goodies.", R.drawable.testerino, Color.parseColor("#F4AC3C")));

        showStatusBar(false);
        setBarColor(Color.parseColor("#F4AC3C"));
        setSeparatorColor(Color.parseColor("#F4AC3C"));
    }
}
