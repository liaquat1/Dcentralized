package com.dcentralized.studywallet.models;

import android.content.Context;

import com.dcentralized.studywallet.repositories.StudyWalletRepository;

public class StudyWallet {
	private StudyWalletRepository repository;
	private static StudyWallet instance;
	private User currentUser;
	private Context context;

	private StudyWallet(Context context) {
		repository = new StudyWalletRepository(context);
		this.context = context;
	}

	public void setCurrentUser() {
		if (repository.isUserInDatabase()) {
			currentUser = repository.getUserFromDatabase(repository.getUserId());
		} else {
			User user = repository.getUserFromFontys();
			repository.addUserToDatabase(user);
			currentUser = user;
		}
	}

	public User getCurrentUser() {
		return this.currentUser;
	}
	public static StudyWallet getInstance(Context context) {
		if (instance == null) {
			instance = new StudyWallet(context);
		}
		return instance;
	}
}