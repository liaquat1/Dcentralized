package com.dcentralized.studywallet.models;

import com.google.firebase.firestore.Exclude;

import java.util.Date;

/**
 * This class represents a project in the application
 *
 * @author Tom de Wildt
 */
public class Project {
	private String id;
	private String name;
	private Language language;
	private Company owner;
	private int reward;
	private Date time;
	private boolean finished;

	/**
	 * Empty constructor for loading from firestore
	 *
	 * @author Tom de Wildt
	 */
	public Project() {
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
	public Language getLanguage() {
		return this.language;
	}
	@Exclude
	public Company getOwner() {
		return this.owner;
	}
	public void setOwner(Company company) {
		this.owner = company;
	}
	public int getReward() {
		return this.reward;
	}
	public Date getTime() {
		return this.time;
	}
	public boolean getFinished() {
		return this.finished;
	}
}