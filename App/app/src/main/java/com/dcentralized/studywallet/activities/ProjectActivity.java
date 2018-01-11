package com.dcentralized.studywallet.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dcentralized.studywallet.R;
import com.dcentralized.studywallet.models.Project;

public class ProjectActivity extends AppCompatActivity {
    private Project project;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        project = (Project)getIntent().getSerializableExtra("project");
        setContentView(R.layout.activity_project);
    }
}
