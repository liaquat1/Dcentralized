package com.dcentralized.studywallet.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dcentralized.studywallet.R;
import com.dcentralized.studywallet.models.Project;

import java.text.SimpleDateFormat;

public class ProjectActivity extends AppCompatActivity {
    private Project project;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project2);
        project = (Project)getIntent().getSerializableExtra("project");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        ((TextView)findViewById(R.id.tvProjectDate)).setText(formatter.format(project.getTime()));
        ((TextView)findViewById(R.id.tvProjectCoins)).setText(String.valueOf(project.getReward()));
        ((TextView)findViewById(R.id.tvProjectDescription)).setText(project.getDescription());
        ((TextView)findViewById(R.id.tvProjectDifficulty)).setText(project.getName()); //Here comes the difficulty
        ((ImageView) findViewById(R.id.ivLogo)).setImageDrawable(project.getLogo(this));
        getSupportActionBar().setTitle(project.getName());
    }
}
