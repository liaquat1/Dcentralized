package com.dcentralized.studywallet.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dcentralized.studywallet.R;
import com.dcentralized.studywallet.models.Transaction;
import com.dcentralized.studywallet.models.User;

import java.util.List;

/**
 * Handles binding of users to list
 *
 * @author Tom de Wildt
 */
public class RankListAdapter extends ArrayAdapter<User>{
    private ViewHolder viewHolder;

    /**
     * View holder class, contains the items in the list item
     *
     * @author Liwei Hu
     */
    private static class ViewHolder {
        private TextView tvName;
        private TextView tvCoins;
        private TextView tvRank;
    }

    /**
     * TransactionListAdapter constructor
     *
     * @param context
     * @param id
     * @param items
     * @author Liwei Hu
     */
    public RankListAdapter(Context context, int id, List items){
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
            view = LayoutInflater.from(this.getContext()).inflate(R.layout.highscore_list_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.tvName = view.findViewById(R.id.tvHighscoreName);
            viewHolder.tvCoins = view.findViewById(R.id.tvHighscoreDescription);
            viewHolder.tvRank = view.findViewById(R.id.tvHighscoreRanking);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        User item = getItem(position);
        if(item != null){
            viewHolder.tvName.setText(item.getFirstname());
            viewHolder.tvName.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
            viewHolder.tvCoins.setText(String.valueOf(item.getTotalCoins()));
            viewHolder.tvCoins.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
            viewHolder.tvRank.setText( "#" + String.valueOf(item.getRank()));
            viewHolder.tvRank.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
        }

        return view;
    }
}
