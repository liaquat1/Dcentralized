package com.dcentralized.studywallet.models;

import com.dcentralized.studywallet.repositories.UserRepository;
import com.google.firebase.firestore.Exclude;

import java.util.*;

public class User {
    private String id;
	private String firstname;
	private String lastname;
	private int employeeId;
	private String email;
	private UserType type;
	private int balance;
	private int totalCoins;
	private List<Transaction> transactions;
	private List<Project> projects;
	private UserRepository repository;

    /**
     * Empty constructor for loading from firestore
     *
     * @author Tom de Wildt
     */
	public User() {
	    this.repository = new UserRepository();
    }

    /**
     * Constructor for loading from Fontys API
     *
     * @param id
     * @param firstname
     * @param lastname
     * @param employeeId
     * @param email
     * @param type
     * @author Tom de Wildt
     */
	public User(String id, String firstname, String lastname, int employeeId, String email, UserType type) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.employeeId = employeeId;
		this.email = email;
		this.type = type;
		this.balance = 0;
		this.totalCoins = 0;
		this.transactions = new ArrayList<>();
		this.projects = new ArrayList<>();
		this.repository = new UserRepository();
	}

	public void getTransactionsFromDatabase() {
	    transactions = repository.getTransactions(id);
    }

    public void getProjectsFromDatabase() {
	    projects = repository.getProjects(id);
    }

    @Exclude
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
	    this.id = id;
    }
	public String getFirstname() {
		return this.firstname;
	}
	public String getLastname() {
		return this.lastname;
	}
	public int getEmployeeId() {
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
    @Exclude
	public List<Transaction> getTransactions() {
		return Collections.unmodifiableList(transactions);
	}
    @Exclude
	public List<Project> getProjects() {
	    return Collections.unmodifiableList(projects);
    }
}