package com.dcentralized.studywallet.models;

import java.util.*;

/**
 * This class represents a company in the application
 *
 * @author Tom de Wildt
 */
public class Company {
	private String id;
	private String name;
	private String description;

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
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return this.name;
	}
	public String getDescription() {
		return this.description;
	}
}