package nl.fhict.liwei.researchduo1_poc.models;

import android.graphics.Bitmap;

/**
 * Created by Liwei on 15-Dec-17.
 */

public class ProjectListItem {
    private String projectLanguage;
    private String projectShortDescription;
    private Bitmap languagePicture;

    public ProjectListItem(String projectLanguage, String projectShortDescription, Bitmap languagePicture){
        this.projectLanguage = projectLanguage;
        this.projectShortDescription = projectShortDescription;
        this.languagePicture = languagePicture;
    }

    public String getProjectLanguage() {
        return projectLanguage;
    }

    public String getProjectShortDescription() {
        return projectShortDescription;
    }

    public Bitmap getLanguagePicture() {
        return languagePicture;
    }
}
