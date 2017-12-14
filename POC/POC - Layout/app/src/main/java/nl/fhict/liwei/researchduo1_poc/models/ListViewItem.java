package nl.fhict.liwei.researchduo1_poc.models;

import android.widget.ListView;

/**
 * Created by Liwei on 14-Dec-17.
 */

public class ListViewItem {
    private String name;
    private String coins;

    public ListViewItem(String name, String coins){
        this.name = name;
        this.coins = coins;
    }

    public String getName(){
        return name;
    }

    public String getCoins(){
        return coins;
    }
}
