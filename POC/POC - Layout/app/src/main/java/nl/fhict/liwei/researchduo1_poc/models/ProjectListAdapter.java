package nl.fhict.liwei.researchduo1_poc.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nl.fhict.liwei.researchduo1_poc.R;

/**
 * Created by Liwei on 15-Dec-17.
 */

public class ProjectListAdapter extends ArrayAdapter<ProjectListItem>{
    private ViewHolder viewHolder;

    private static class ViewHolder {
        private TextView projectLanguage;
        private TextView projectShortDescription;
        private ImageView languagePicture;
    }

    public ProjectListAdapter(Context context, int id, List items){
        super(context, id, items);
    }

    public View getView(int position, View v, ViewGroup parent){
        if(v == null){
            v = LayoutInflater.from(this.getContext()).inflate(R.layout.project_list_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.projectLanguage = v.findViewById(R.id.tvProjectListName);
            viewHolder.projectShortDescription = v.findViewById(R.id.tvProjectDescription);
            viewHolder.languagePicture = v.findViewById(R.id.ivProjectListItem);

            v.setTag(viewHolder);
        } else {
            viewHolder = (ProjectListAdapter.ViewHolder) v.getTag();
        }

        ProjectListItem item = getItem(position);
        if(item != null){
            viewHolder.languagePicture.setImageDrawable(item.getLanguagePicture());
            viewHolder.languagePicture.setBackgroundColor(Color.parseColor("#FFFFFF"));
            viewHolder.projectShortDescription.setText(item.getProjectShortDescription());
            viewHolder.projectShortDescription.setBackgroundColor(Color.parseColor("#F3AB3B"));
            viewHolder.projectShortDescription.setTextColor(Color.parseColor("#333333"));
            viewHolder.projectLanguage.setText(item.getProjectLanguageString());
            viewHolder.projectLanguage.setBackgroundColor(Color.parseColor("#F3AB3B"));
            viewHolder.projectLanguage.setTextColor(Color.parseColor("#3A3A3A"));
        }

        return v;
    }
}
