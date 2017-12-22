package com.dcentralized.studywallet.repositories;

import com.dcentralized.studywallet.contexts.UserDatabaseContext;
import com.dcentralized.studywallet.contexts.interfaces.IUserContext;
import com.dcentralized.studywallet.models.Project;
import com.dcentralized.studywallet.models.Transaction;

import java.util.List;

public class UserRepository {
    private IUserContext context;

    public UserRepository() {
        this.context = new UserDatabaseContext();
    }

    public List<Transaction> getTransactions(String id) {
        return context.getTransactions(id);
    }

    public List<Project> getProjects(String id) {
        return context.getProjects(id);
    }
}
