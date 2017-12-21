package com.dcentralized.studywallet.models;

import java.util.*;

public class User {
	private String id;
	private String firstname;
	private String lastname;
	private String employeeId;
	private String email;
	private UserType type;
	private int balance;
	private int totalCoins;
	private List<Transaction> transactions;
	private List<Project> projects;



	public String getId() {
		return this.id;
	}
	public String getFirstname() {
		return this.firstname;
	}
	public String getLastname() {
		return this.lastname;
	}
	public String getEmployeeId() {
		return this.employeeId;
	}
	public String getEmail() {
		return this.email;
	}
	public UserType getType() {
		return this.type;
	}
	public int getBalance() {
		return this.balance;
	}
	public int getTotalCoins() {
		return this.totalCoins;
	}
	public List<Transaction> getTransactions() {
		return Collections.unmodifiableList(transactions);
	}
	public List<Project> getProjects() {
	    return Collections.unmodifiableList(projects);
    }
}