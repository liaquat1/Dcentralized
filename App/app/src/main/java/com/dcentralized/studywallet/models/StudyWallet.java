package com.dcentralized.studywallet.models;

public class StudyWallet {
	private static StudyWallet instance;
	private User currentUser;

	public User getCurrentUser() {
		return this.currentUser;
	}
	public static StudyWallet getInstance() {
		if (instance == null) {
			instance = new StudyWallet();
		}
		return instance;
	}
}