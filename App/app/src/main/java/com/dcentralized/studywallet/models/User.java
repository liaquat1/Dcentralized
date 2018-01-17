package com.dcentralized.studywallet.models;

import android.util.Log;

import com.dcentralized.studywallet.repositories.UserRepository;
import com.dcentralized.studywallet.tasks.TransactionAddTask;
import com.google.firebase.firestore.Exclude;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * This class represents a user in the application
 *
 * @author Tom de Wildt
 */
public class User {
    private static final String TAG = User.class.getSimpleName();
    private String id;
	private String firstname;
	private String lastname;
	private int employeeId;
	private String email;
	private UserType type;
	private int balance;
	private int totalCoins;
	private int rank;
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

    /**
     * Gets the user's transactions from the database
     *
     * @author Tom de Wildt
     */
	public void getTransactionsFromDatabase() {
	    transactions = repository.getTransactions(id);
    }

    /**
     * Sets the new transactions
     *
     * @param transactions to set
     * @author Tom de Wildt
     */
    public void setTransactions(List<Transaction> transactions) {
	    this.transactions.clear();
	    this.transactions.addAll(transactions);
    }

	/**
	 * Gets the user's projects from the database
     *
     * @author Tom de Wildt
     */
    public void getProjectsFromDatabase() {
	    projects = repository.getProjects(id);
    }

	/**
	 * Sets the new projects
	 *
	 * @param projects to set
	 * @author Tom de Wildt
	 */
	public void setProjects(List<Project> projects) {
		this.projects.clear();
		this.projects.addAll(projects);
	}

	/**
	 * Gets the user's rank from database
	 *
	 * @return rank number
     * @author Tom de Wildt
	 */
	@Exclude
	public void getRankFromDatabase() {
    	rank = repository.getRank(id);
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	/**
	 * Transfers money to a student if user is docent
	 * @param studentId Number of student
	 * @param name of the transaction
	 * @param amount Amount of coins
	 * @author Davey van den Bogaard
	 */
	public boolean transferCoins(String studentId, String name, int amount) {
		try {
            if (type == UserType.Docent){
                Transaction transaction = new Transaction(name, amount);
                TransactionAddTask task = new TransactionAddTask(transaction, studentId);

                return task.execute().get() != null;
            }
            return false;
        } catch (ExecutionException e) {
            Log.e(TAG, "ExecutionException occurred", e);
            return false;
        } catch (InterruptedException e) {
            Log.e(TAG, "InterruptException occurred", e);
            return false;
        }
	}

	public void updateBalance(int amount) {
		balance = balance + amount;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public boolean addProject(Project project){
		if (repository.addProject(this.id, project.getId())) {
		    projects.add(project);
		    return true;
        }
        return false;
	}

	public void addTransaction(Transaction transaction) {
		transactions.add(transaction);
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
    public int getRank() {
	    if (rank == 0) {
	        getRankFromDatabase();
        }
        return rank;
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