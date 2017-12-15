package nl.fhict.liwei.researchduo1_poc.models;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import nl.fhict.liwei.researchduo1_poc.R;

/**
 * Created by Liwei on 14-Dec-17.
 */

public class HomeScreenListAdapter extends ArrayAdapter<HomeScreenListItem> {
    private ViewHolder viewHolder;

    private static class ViewHolder {
        private TextView itemView;
        private TextView coinView;
    }

    public HomeScreenListAdapter(Context context, int id, ArrayList items){
        super(context, id, items);
    }

    public View getView(int position, View v, ViewGroup parent){
        if(v == null){
            v = LayoutInflater.from(this.getContext()).inflate(R.layout.listview_item_1, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.itemView = v.findViewById(R.id.tvListName);
            viewHolder.coinView = v.findViewById(R.id.tvListCoins);

            v.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) v.getTag();
        }

        HomeScreenListItem item = getItem(position);
        if(item != null){
            viewHolder.itemView.setText(item.getName());
            viewHolder.itemView.setTextColor(Color.parseColor("#F3AB3B"));
            viewHolder.coinView.setText(item.getCoins());
            viewHolder.coinView.setTextColor(Color.parseColor("#F3AB3B"));
        }

        return v;
    }
}
