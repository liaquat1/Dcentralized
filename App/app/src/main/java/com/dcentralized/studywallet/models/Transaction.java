package com.dcentralized.studywallet.models;

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