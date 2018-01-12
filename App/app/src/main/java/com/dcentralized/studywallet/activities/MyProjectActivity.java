package com.dcentralized.studywallet.activities;

import android.graphics.Color;
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
import java.util.Date;

/**
 * Created by Liwei on 1/12/2018.
 */

public class MyProjectActivity extends AppCompatActivity implements View.OnClickListener {
    private Project project;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        project = (Project)getIntent().getSerializableExtra("project");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ((Button)findViewById(R.id.btnApply)).setText("Finish Project");
        if(project.getTime().before(new Date())){
            ((TextView)findViewById(R.id.tvProjectDate)).setTextColor(Color.RED);
            ((TextView)findViewById(R.id.tvProjectCoins)).setTextColor(Color.RED);
            ((TextView)findViewById(R.id.tvProjectCoins)).setText(String.valueOf(project.getReward() / 2));
            ((TextView)findViewById(R.id.tvProjectDate)).setText((new SimpleDateFormat("dd-MM-yyyy")).format(project.getTime()) + " LATE");
        } else {
            ((TextView)findViewById(R.id.tvProjectCoins)).setText(String.valueOf(project.getReward()));
            ((TextView)findViewById(R.id.tvProjectDate)).setText((new SimpleDateFormat("dd-MM-yyyy")).format(project.getTime()));
        }
        ((TextView)findViewById(R.id.tvProjectDescription)).setText(project.getDescription());
        ((TextView)findViewById(R.id.tvProjectDifficulty)).setText(String.valueOf(project.getDifficulty()));
        ((ImageView) findViewById(R.id.ivLogo)).setImageDrawable(project.getLogo(this));
        (findViewById(R.id.btnApply)).setOnClickListener(this);
        getSupportActionBar().setTitle(project.getName());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnApply:
                onBtnFinishedClick(v);
                break;
        }
    }

    public void onBtnFinishedClick(View view){
        //TODO
    }
}
