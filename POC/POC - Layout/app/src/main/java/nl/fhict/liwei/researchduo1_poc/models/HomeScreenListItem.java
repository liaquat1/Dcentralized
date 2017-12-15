package nl.fhict.liwei.researchduo1_poc.models;

import android.widget.ListView;

/**
 * Created by Liwei on 14-Dec-17.
 */

public class HomeScreenListItem {
    private String name;
    private String coins;

    public HomeScreenListItem(String name, String coins){
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
