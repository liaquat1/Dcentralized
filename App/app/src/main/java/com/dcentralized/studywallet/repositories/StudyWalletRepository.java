package com.dcentralized.studywallet.repositories;

import android.content.Context;

import com.dcentralized.studywallet.contexts.FontysAPIContext;
import com.dcentralized.studywallet.contexts.StudyWalletDatabaseContext;
import com.dcentralized.studywallet.contexts.interfaces.IFontysContext;
import com.dcentralized.studywallet.contexts.interfaces.IStudyWalletContext;
import com.dcentralized.studywallet.models.User;
import com.dcentralized.studywallet.utilities.ConverterUtility;

import org.json.JSONObject;

import java.util.List;

/**
 * Handles communication with the fontys context and database context
 *
 * @author Tom de Wildt
 */
public class StudyWalletRepository {
    private static final String TAG = StudyWalletRepository.class.getSimpleName();
    private IFontysContext fontysContext;
    private IStudyWalletContext databaseContext;

    /**
     *
     * @param context
     * @author Tom de Wildt
     */
    public StudyWalletRepository(Context context) {
        fontysContext = new FontysAPIContext(context);
        databaseContext = new StudyWalletDatabaseContext();
    }

    /**
     * Gets user id from fontys API
     *
     * @return userId or null
     * @author Tom de Wildt
     */
    public String getUserId() {
        return fontysContext.getCurrentUserId();
    }

    /**
     * Checks if a user is in the database
     *
     * @param id userId
     * @return true is user is in database
     * @author Tom de Wildt
     */
    public boolean isUserInDatabase(String id) {
        return databaseContext.isUserInDatabase(id);
    }

    /**
     * Adds a new user to the database
     *
     * @param user to add
     * @author Tom de Wildt
     */
    public void addUserToDatabase(User user) {
        databaseContext.addUserToDatabase(user);
    }

    /**
     * Gets a user from the fontys API
     *
     * @return user or null
     * @author Tom de Wildt
     */
    public User getUserFromFontys() {
        JSONObject object = fontysContext.getCurrentUser();
        return ConverterUtility.jsonToUser(object);
    }

    /**
     * Gets user from the database and retrieves transactions and projects
     *
     * @param id userId
     * @return user or null
     * @author Tom de Wildt
     */
    public User getUserFromDatabase(String id) {
        User user = databaseContext.getUserFromDatabase(id);
        user.getTransactionsFromDatabase();
        user.getProjectsFromDatabase();
        return user;
    }

    public List<User> getAllUsersFromDatabase(){
        return databaseContext.getAllUsersFromDatabase();
    }
}
