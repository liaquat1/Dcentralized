package com.dcentralized.studywallet.models;

import java.util.Date;

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
	public String getName() {
		return this.name;
	}
	public Language getLanguage() {
		return this.language;
	}
	public Company getOwner() {
		return this.owner;
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