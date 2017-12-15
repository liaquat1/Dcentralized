package nl.fhict.liwei.researchduo1_poc.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import nl.fhict.liwei.researchduo1_poc.R;
import nl.fhict.liwei.researchduo1_poc.models.HomeScreenListAdapter;
import nl.fhict.liwei.researchduo1_poc.models.HomeScreenListItem;

public class HomeScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        mockList();
    }

    public void onButtonAddProjectClick(View v){
        Intent intent = new Intent(HomeScreen.this, ProjectTestActivity.class);
        startActivity(intent);
    }

    public void mockList(){
        ArrayList<HomeScreenListItem> items = new ArrayList<>();
        items.add(new HomeScreenListItem("C# Project", "+200"));
        items.add(new HomeScreenListItem("PHP Project", "+150"));
        items.add(new HomeScreenListItem("Websockets Website", "+475"));
        items.add(new HomeScreenListItem("Broodje Gezond", "-225"));
        items.add(new HomeScreenListItem("Proftaak Rails", "+360"));
        items.add(new HomeScreenListItem("Space Invaders", "+100"));
        items.add(new HomeScreenListItem("Appelflap", "-100"));
        items.add(new HomeScreenListItem("Appel", "-50"));


        ListView lv = findViewById(R.id.listView);
        lv.setAdapter(new HomeScreenListAdapter(this, R.layout.listview_item_1, items));
    }
}
