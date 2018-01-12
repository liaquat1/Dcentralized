package com.dcentralized.studywallet.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
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
        setContentView(R.layout.activity_project);
        project = (Project)getIntent().getSerializableExtra("project");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ((TextView)findViewById(R.id.tvProjectDate)).setText((new SimpleDateFormat("dd-MM-yyyy")).format(project.getTime()));
        ((TextView)findViewById(R.id.tvProjectCoins)).setText(String.valueOf(project.getReward()));
        ((TextView)findViewById(R.id.tvProjectDescription)).setText(project.getDescription());
        ((TextView)findViewById(R.id.tvProjectDifficulty)).setText(project.getName()); //Here comes the difficulty
        ((ImageView) findViewById(R.id.ivLogo)).setImageDrawable(project.getLogo(this));
        getSupportActionBar().setTitle(project.getName());
        findViewById(R.id.btnApply).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
