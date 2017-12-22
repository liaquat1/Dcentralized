package com.dcentralized.studywallet.repositories;

import com.dcentralized.studywallet.contexts.UserDatabaseContext;
import com.dcentralized.studywallet.contexts.interfaces.IUserContext;
import com.dcentralized.studywallet.models.Project;
import com.dcentralized.studywallet.models.Transaction;

import java.util.List;

/**
 * Handles communication with the database context
 *
 * @author Tom de Wildt
 */
public class UserRepository {
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
}
