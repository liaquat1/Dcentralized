package com.dcentralized.studywallet.contexts.interfaces;

import com.dcentralized.studywallet.models.Company;
import com.dcentralized.studywallet.models.Project;
import com.dcentralized.studywallet.models.Transaction;
import com.google.firebase.firestore.DocumentReference;

import java.util.List;

public interface IUserContext {
    List<DocumentReference> getReferences(String id, String collection);
    List<Transaction> getTransactions(List<DocumentReference> references);
    List<Project> getProjects(List<DocumentReference> references);
    Company getOwner(DocumentReference reference);
}
