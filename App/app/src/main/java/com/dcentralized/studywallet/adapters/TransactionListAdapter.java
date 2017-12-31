package com.dcentralized.studywallet.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dcentralized.studywallet.R;
import com.dcentralized.studywallet.models.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles the binding of transactions to a list
 *
 * @author Liwei Hu
 */
public class TransactionListAdapter extends ArrayAdapter<Transaction> {
    private ViewHolder viewHolder;

    /**
     * View holder class, contains the items in the list item
     *
     * @author Liwei Hu
     */
    private static class ViewHolder {
        private TextView textItem;
        private TextView textCoins;
    }

    /**
     * TransactionListAdapter constructor
     *
     * @param context
     * @param id
     * @param items
     * @author Liwei Hu
     */
    public TransactionListAdapter(Context context, int id, List items){
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
            view = LayoutInflater.from(this.getContext()).inflate(R.layout.transaction_list_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.textItem = view.findViewById(R.id.tvListName);
            viewHolder.textCoins = view.findViewById(R.id.tvListCoins);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Transaction item = getItem(position);
        if(item != null){
            viewHolder.textItem.setText(item.getName());
            viewHolder.textItem.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
            viewHolder.textCoins.setText(String.valueOf(item.getAmount()));
            viewHolder.textCoins.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
        }

        return view;
    }
}
