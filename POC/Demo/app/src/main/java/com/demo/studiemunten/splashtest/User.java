package com.demo.studiemunten.splashtest;

import java.io.Serializable;

/**
 * Created by Davey on 08-Dec-17.
 */

public class User implements Serializable {
    private String Name;
    private int id;

    public User(String name, int id) {
        Name = name;
        this.id = id;
    }

    public String getName() {

        return Name;
    }

    public int getId() {
        return id;
    }
}
