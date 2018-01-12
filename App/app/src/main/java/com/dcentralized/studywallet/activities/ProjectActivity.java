package com.dcentralized.studywallet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dcentralized.studywallet.R;
import com.dcentralized.studywallet.models.Project;
import com.dcentralized.studywallet.models.StudyWallet;

import java.text.SimpleDateFormat;

public class ProjectActivity extends AppCompatActivity implements View.OnClickListener {
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
        ((TextView)findViewById(R.id.tvProjectDifficulty)).setText(String.valueOf(project.getDifficulty()));
        ((ImageView) findViewById(R.id.ivLogo)).setImageDrawable(project.getLogo(this));
        (findViewById(R.id.btnApply)).setOnClickListener(this);
        getSupportActionBar().setTitle(project.getName());
    }

    private void onButtonApplyClick(View view) {
        if(StudyWallet.getInstance(this).getCurrentUser().addProject(project)){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Failed adding project!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnApply:
                onButtonApplyClick(view);
                break;
        }
    }
}
