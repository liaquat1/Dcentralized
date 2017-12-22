package com.dcentralized.studywallet.contexts.interfaces;

import com.dcentralized.studywallet.models.Company;
import com.dcentralized.studywallet.models.Project;
import com.dcentralized.studywallet.models.Transaction;
import com.google.firebase.firestore.DocumentReference;

import java.util.List;

/**
 * This class handles communication with the database
 *
 * @author Tom de Wildt
 */
public interface IUserContext {
    /**
     * Gets references from a collection in a document
     *
     * @param id userId
     * @param collection transactions or projects
     * @return empty list or list with references
     * @author Tom de Wildt
     */
    List<DocumentReference> getReferences(String id, String collection);

    /**
     * Gets transactions from the database
     *
     * @param references list with transactions references
     * @return empty list or list with transactions
     * @author Tom de Wildt
     */
    List<Transaction> getTransactions(List<DocumentReference> references);

    /**
     * Gets projects from the database
     *
     * @param references list with project references
     * @return empty list or list with projects
     * @author Tom de Wildt
     */
    List<Project> getProjects(List<DocumentReference> references);

    /**
     * Gets a project owner
     *
     * @param reference to the document
     * @return company or null
     * @author Tom de Wildt
     */
    Company getOwner(DocumentReference reference);
}
