package com.dcentralized.studywallet.models;

import java.util.*;

public class Company {
	private String id;
	private String name;
	private String description;
	private List<Project> projects;

	/**
	 * Empty constructor for loading from firestore
	 *
	 * @author Tom de Wildt
	 */
	public Company() {
		// Empty
	}

	public String getId() {
		return this.id;
	}
	public String getName() {
		return this.name;
	}
	public String getDescription() {
		return this.description;
	}
	public List<Project> getProjects() {
	    return Collections.unmodifiableList(projects);
    }
}