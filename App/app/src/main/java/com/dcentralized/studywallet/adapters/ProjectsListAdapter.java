package com.dcentralized.studywallet.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dcentralized.studywallet.R;
import com.dcentralized.studywallet.models.Project;

import java.util.List;

/**
 * Handles the binding of projects to a list
 *
 * @author Liwei Hu
 */
public class ProjectsListAdapter extends ArrayAdapter<Project> {
    private ViewHolder viewHolder;

    /**
     * View holder class, contains the items in the list item
     *
     * @author Liwei Hu
     */
    private static class ViewHolder {
        private String id;
        private TextView textLanguage;
        private TextView textDescription;
        private ImageView imageProjectLogo;
    }

    /**
     * ProjectsListAdapter constructor
     *
     * @param context
     * @param id
     * @param items
     * @author Liwei Hu
     */
    public ProjectsListAdapter(Context context, int id, List items){
        super(context, id, items);
    }

    /**
     * Called by android to retrieve a item
     *
     * @param position item position
     * @param view element on screen
     * @param parent parent element
     * @return list item
     * @author Liwei Hu
     */
    public View getView(int position, View view, ViewGroup parent){
        if(view == null){
            view = LayoutInflater.from(this.getContext()).inflate(R.layout.project_list_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.textLanguage = view.findViewById(R.id.textName);
            viewHolder.textDescription = view.findViewById(R.id.textDescription);
            viewHolder.imageProjectLogo = view.findViewById(R.id.imageProjectLogo);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ProjectsListAdapter.ViewHolder) view.getTag();
        }

        Project item = getItem(position);
        if(item != null){

            switch(item.getLanguage()){
                case CS:
                    viewHolder.textLanguage.setText("C#");
                    break;
                case CPP:
                    viewHolder.textLanguage.setText("C++");
                    break;
                default:
                    viewHolder.textLanguage.setText(String.valueOf(item.getLanguage()));
            }
            viewHolder.textLanguage.setTextColor(getContext().getResources().getColor(R.color.colorAccent));
            viewHolder.textLanguage.setBackgroundColor(getContext().getResources().getColor(R.color.colorPrimary));

            viewHolder.textDescription.setText(item.getName());
            viewHolder.textDescription.setTextColor(getContext().getResources().getColor(R.color.colorAccent));
            viewHolder.textDescription.setBackgroundColor(getContext().getResources().getColor(R.color.colorPrimary));

            viewHolder.imageProjectLogo.setImageDrawable(item.getLogo(getContext()));
            viewHolder.imageProjectLogo.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }

        return view;
    }
}
