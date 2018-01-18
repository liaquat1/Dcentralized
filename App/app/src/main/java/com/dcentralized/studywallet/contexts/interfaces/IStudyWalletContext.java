package com.dcentralized.studywallet.contexts.interfaces;

import com.dcentralized.studywallet.models.User;

import java.util.List;

/**
 * This class handles communication with the database
 *
 * @author Tom de Wildt
 */
public interface IStudyWalletContext {
    /**
     * Checks if the user is in the database
     *
     * @param id userId
     * @return true if user is in database
     * @author Tom de Wildt
     */
    boolean isUserInDatabase(String id);

    /**
     * Add a user to the database
     *
     * @param user
     * @return true if successful
     * @author Tom de Wildt
     */
    boolean addUserToDatabase(User user);

    /**
     * Gets a user from the database
     *
     * @param id userId
     * @return user or null
     * @author Tom de Wildt
     */
    User getUserFromDatabase(String id);

    /**
     *
     *
     * @return
     */
    List<User> getAllUsersFromDatabase();
}
