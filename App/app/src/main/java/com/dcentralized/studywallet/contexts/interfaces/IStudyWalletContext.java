package com.dcentralized.studywallet.contexts.interfaces;

import com.dcentralized.studywallet.models.User;

public interface IStudyWalletDatabaseContext {
    boolean isUserInDatabase(String id);
    boolean addUserToDatabase(User user);
    User getUserFromDatabase(String id);
}
