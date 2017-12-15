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

    public ProjectListAdapter(Context context, int id, ArrayList items){
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
            
        }

        return v;
    }
}
