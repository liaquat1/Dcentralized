package com.dcentralized.studywallet.models;

import com.google.firebase.firestore.Exclude;

/**
 * This class represents a transaction in the application
 *
 * @author Tom de Wildt
 */
public class Transaction {
	private String id;
	private String name;
	private int amount;

	/**
	 * Empty constructor for loading from firestore
	 *
	 * @author Tom de Wildt
	 */
	public Transaction() {
		// Empty
	}

	public Transaction(String name, int amount) {
		this.name = name;
		this.amount = amount;
	}

	@Exclude
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return this.name;
	}
	public int getAmount() {
		return this.amount;
	}
}