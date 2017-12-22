package com.dcentralized.studywallet.models;

import android.content.Context;

import com.dcentralized.studywallet.repositories.StudyWalletRepository;
import com.dcentralized.studywallet.services.FirebaseAuthService;

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
	    String id = repository.getUserId();

		if (repository.isUserInDatabase(id)) {
			currentUser = repository.getUserFromDatabase(id);
		} else {
			User user = repository.getUserFromFontys();
			repository.addUserToDatabase(user);
			currentUser = user;
		}
	}
	public User getCurrentUser() {
		return this.currentUser;
	}

	/**
	 * Returns the instance of the study wallet, if it's null it will create a new one
	 *
	 * @param context the activity
	 * @return instance
	 * @author Tom de Wildt
	 */
	public static StudyWallet getInstance(Context context) {
		if (instance == null) {
			instance = new StudyWallet(context);
		}
		if (instance.context != context) {
            instance.context = context;
        }

		return instance;
	}
}