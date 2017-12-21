package com.dcentralized.studywallet.contexts.interfaces;

import com.dcentralized.studywallet.models.User;

public interface IStudyWalletDatabaseContext {
    boolean isUserInDatabase(String id);
    void addUserToDatabase(String id, String firstname, String lastname, int employeeId, String email, String type);
    User getUserFromDatabase(String id);
}
