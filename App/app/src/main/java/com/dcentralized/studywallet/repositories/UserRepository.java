package com.dcentralized.studywallet.repositories;

import android.util.Log;

import com.dcentralized.studywallet.contexts.UserDatabaseContext;
import com.dcentralized.studywallet.contexts.interfaces.IUserContext;
import com.dcentralized.studywallet.models.Project;
import com.dcentralized.studywallet.models.Transaction;
import com.dcentralized.studywallet.models.User;
import com.dcentralized.studywallet.tasks.ProjectAddTask;
import com.dcentralized.studywallet.tasks.UsersTask;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Handles communication with the database context
 *
 * @author Tom de Wildt
 */
public class UserRepository {
    private static final String TAG = UserRepository.class.getSimpleName();
    private IUserContext context;

    /**
     * UserRepository constructor
     *
     * @author Tom de Wildt
     */
    public UserRepository() {
        this.context = new UserDatabaseContext();
    }

    /**
     * Gets transactions from the database context
     *
     * @param id userId
     * @return empty list or list with transactions
     */
    public List<Transaction> getTransactions(String id) {
        return context.getTransactions(context.getReferences(id, "transactions"));
    }

    /**
     * Gets projects from the database context
     *
     * @param id userId
     * @return empty list or list with projects
     */
    public List<Project> getProjects(String id) {
        return context.getProjects(context.getReferences(id, "projects"));
    }

    /**
     * Get rank from database context
     *
     * @param id userId
     */
    public int getRank(String id) {
        try {
            UsersTask task = new UsersTask();
            List<User> users = task.execute().get();
            User user = null;

            for (User foundUser : users) {
                if (foundUser.getId().equals(id)) {
                    user = foundUser;
                    break;
                }
            }

            return users.indexOf(user) + 1;
        } catch (ExecutionException e) {
            Log.e(TAG, "ExecutionException occurred", e);
            return 0;
        } catch (InterruptedException e) {
            Log.e(TAG, "InterruptException occurred", e);
            return 0;
        }
    }

    public boolean addProject(String userId, String projectId){
        try {
            ProjectAddTask task = new ProjectAddTask(userId, projectId);
            return task.execute().get();
        } catch (InterruptedException e) {
            Log.e(TAG, "InterruptException occurred", e);
        } catch (ExecutionException e) {
            Log.e(TAG, "ExecutionException occurred", e);
        }
        return false;
    }
}
