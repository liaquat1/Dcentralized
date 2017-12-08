package com.demo.studiemunten.splashtest;

import java.io.Serializable;

/**
 * Created by Davey on 08-Dec-17.
 */

public class Transaction implements Serializable {
    private int id;
    private int sender_id;
    private int recipient_id;



    private User sender;
    private User recipient;
    private double amount;
    private boolean checked;

    public Transaction(int id, int sender_id, int recipient_id, User sender, User recipient, double amount, boolean checked) {
        this.id = id;
        this.sender_id = sender_id;
        this.recipient_id = recipient_id;
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
        this.checked = checked;
    }
    public User getSender() {
        return sender;
    }

    public User getRecipient() {
        return recipient;
    }
    public int getId() {
        return id;
    }

    public int getSender_id() {
        return sender_id;
    }

    public int getRecipient_id() {
        return recipient_id;
    }

    public double getAmount() {
        return amount;
    }

    public boolean isChecked() {
        return checked;
    }
}
