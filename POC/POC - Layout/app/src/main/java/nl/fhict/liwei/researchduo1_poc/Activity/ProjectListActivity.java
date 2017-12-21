package nl.fhict.liwei.researchduo1_poc.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import nl.fhict.liwei.researchduo1_poc.Enums.ProjectLanguage;
import nl.fhict.liwei.researchduo1_poc.R;
import nl.fhict.liwei.researchduo1_poc.models.ProjectListAdapter;
import nl.fhict.liwei.researchduo1_poc.models.ProjectListItem;

public class ProjectListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_list);

        mockProjectList();
    }

    public void mockProjectList(){
        List<ProjectListItem> projects = new ArrayList<>();
        projects.add(new ProjectListItem(ProjectLanguage.Java, "JavaFX application for drawing.", getDrawable(R.drawable.java_logo)));
        projects.add(new ProjectListItem(ProjectLanguage.Python, "Data visualistaion of company numbers.", getDrawable(R.drawable.python_logo)));
        projects.add(new ProjectListItem(ProjectLanguage.php, "CMS for a shopping site.", getDrawable(R.drawable.php_logo)));
        projects.add(new ProjectListItem(ProjectLanguage.Android, "New browser for Android phones.", getDrawable(R.drawable.android_logo)));
        projects.add(new ProjectListItem(ProjectLanguage.Angular, "Website in angular.", getDrawable(R.drawable.angular_logo)));
        projects.add(new ProjectListItem(ProjectLanguage.C, "Game in C.", getDrawable(R.drawable.c_logo)));
        projects.add(new ProjectListItem(ProjectLanguage.Cpp, "Game in C++.", getDrawable(R.drawable.cpp_logo)));
        projects.add(new ProjectListItem(ProjectLanguage.CS, "Calculator in C#.", getDrawable(R.drawable.chastag)));
        projects.add(new ProjectListItem(ProjectLanguage.Go, "Server in GoLang.", getDrawable(R.drawable.golang_logo)));
        projects.add(new ProjectListItem(ProjectLanguage.HTML, "Website for FHICT.", getDrawable(R.drawable.html_logo)));
        projects.add(new ProjectListItem(ProjectLanguage.Javascript, "Backend in Javascript for webshop.", getDrawable(R.drawable.javascript_logo)));
        projects.add(new ProjectListItem(ProjectLanguage.Kotlin, "Android app in Kotlin.", getDrawable(R.drawable.kotlin_logo)));
        projects.add(new ProjectListItem(ProjectLanguage.React, "Webshop in React.", getDrawable(R.drawable.react_logo)));
        projects.add(new ProjectListItem(ProjectLanguage.Swift, "StudyCoin wallet in Swift.", getDrawable(R.drawable.swift_logo)));

        ListView list = findViewById(R.id.lvProjects);
        list.setAdapter(new ProjectListAdapter(this, R.layout.project_list_item, projects));
    }
}
