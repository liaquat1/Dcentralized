package nl.fhict.liwei.researchduo1_poc.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import nl.fhict.liwei.researchduo1_poc.R;

public class ProjectTestActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_test);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setTitle("Test");
        setSupportActionBar(toolbar);
    }

    public void onButtonAddProjectClick(View v){
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }
}
