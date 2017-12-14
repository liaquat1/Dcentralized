package nl.fhict.liwei.researchduo1_poc.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import nl.fhict.liwei.researchduo1_poc.R;
import nl.fhict.liwei.researchduo1_poc.models.CustomListViewAdapter;
import nl.fhict.liwei.researchduo1_poc.models.ListViewItem;

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
        ArrayList<ListViewItem> items = new ArrayList<>();
        items.add(new ListViewItem("C# Project", "+200"));
        items.add(new ListViewItem("PHP Project", "+150"));
        items.add(new ListViewItem("Websockets Website", "+475"));
        items.add(new ListViewItem("Broodje Gezond", "-225"));
        items.add(new ListViewItem("Proftaak Rails", "+360"));
        items.add(new ListViewItem("Space Invaders", "+100"));


        ListView lv = findViewById(R.id.listView);
        lv.setAdapter(new CustomListViewAdapter(this, R.layout.listview_item_1, items));
    }
}
