package com.dcentralized.studywallet.contexts.interfaces;

import com.dcentralized.studywallet.models.Project;
import com.dcentralized.studywallet.models.Transaction;

import java.util.List;

public interface IUserContext {
    List<Transaction> getTransactions(String id);
    List<Project> getProjects(String id);
}
