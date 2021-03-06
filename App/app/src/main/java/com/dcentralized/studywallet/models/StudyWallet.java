package com.dcentralized.studywallet.models;

import android.content.Context;

import com.dcentralized.studywallet.repositories.StudyWalletRepository;
import com.dcentralized.studywallet.services.StorageService;
import com.dcentralized.studywallet.tasks.ProjectsAllTask;

import java.util.List;
import java.util.Observer;

/**
 * This is the main class for the system
 *
 * @author Tom de Wildt
 */
public class StudyWallet {
	private StudyWalletRepository repository;
	private static StudyWallet instance;
	private User currentUser;
	private Context context;

    /**
     * StudyWallet constructor
     *
     * @param context activity context
     * @author Tom de Wildt
     */
	private StudyWallet(Context context) {
		repository = new StudyWalletRepository(context);
		this.context = context;
	}

	public User getCurrentUser() {
		return this.currentUser;
	}
    /**
     * Checks if user is in database else creates a new user from the fontys database
     *
     * @author Tom de Wildt
	 * @return true if successful
     */
    public boolean setCurrentUser() {
        String id = repository.getUserId();

        if (id != null) {
            if (repository.isUserInDatabase(id)) {
                currentUser = repository.getUserFromDatabase(id);
				StorageService.getInstance(context).storeValue(StorageService.ID_PREF_KEY, currentUser.getId());
                return true;
            } else {
                User user = repository.getUserFromFontys();
                if (user != null) {
                    repository.addUserToDatabase(user);
                    currentUser = user;
                    StorageService.getInstance(context).storeValue(StorageService.ID_PREF_KEY, currentUser.getId());
                    return true;
                }
            }
        }
        return false;
    }

    public List<User> getAllUsers(){
        return repository.getAllUsersFromDatabase();
    }

    /**
     * Gets all projects from database
     *
     * @author Tom de Wildt
     * @return list of projects
     */
    public void getAllProjects(Observer observer) {
        new Thread(new ProjectsAllTask(observer)).start();
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