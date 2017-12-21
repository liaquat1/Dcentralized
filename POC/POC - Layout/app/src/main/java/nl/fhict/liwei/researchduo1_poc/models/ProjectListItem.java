package nl.fhict.liwei.researchduo1_poc.models;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import nl.fhict.liwei.researchduo1_poc.Enums.ProjectLanguage;

/**
 * Created by Liwei on 15-Dec-17.
 */

public class ProjectListItem {
    private ProjectLanguage projectLanguage;
    private String projectShortDescription;
    private Drawable languagePicture;

    public ProjectListItem(ProjectLanguage projectLanguage, String projectShortDescription, Drawable languagePicture){
        this.projectLanguage = projectLanguage;
        this.projectShortDescription = projectShortDescription;
        this.languagePicture = languagePicture;
    }

    public ProjectLanguage getProjectLanguage() {
        return projectLanguage;
    }

    public String getProjectLanguageString(){
        switch (projectLanguage){
            case Cpp:
                return "C++";
            case CS:
                return "C#";
            default:
                return projectLanguage.toString();
        }
    }

    public String getProjectShortDescription() {
        return projectShortDescription;
    }

    public Drawable getLanguagePicture() {
        return languagePicture;
    }
}
