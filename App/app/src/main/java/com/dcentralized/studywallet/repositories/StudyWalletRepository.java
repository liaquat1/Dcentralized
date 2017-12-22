package com.dcentralized.studywallet.repositories;

import android.content.Context;

import com.dcentralized.studywallet.contexts.FontysAPIContext;
import com.dcentralized.studywallet.contexts.StudyWalletDatabaseContext;
import com.dcentralized.studywallet.contexts.interfaces.IFontysContext;
import com.dcentralized.studywallet.contexts.interfaces.IStudyWalletContext;
import com.dcentralized.studywallet.models.User;
import com.dcentralized.studywallet.utilities.ConverterUtility;

import org.json.JSONObject;

public class StudyWalletRepository {
    private IFontysContext fontysContext;
    private IStudyWalletContext databaseContext;

    public StudyWalletRepository(Context context) {
        fontysContext = new FontysAPIContext(context);
        databaseContext = new StudyWalletDatabaseContext();
    }

    public String getUserId() {
        return fontysContext.getCurrentUserId();
    }

    public boolean isUserInDatabase(String id) {
        return databaseContext.isUserInDatabase(id);
    }

    public void addUserToDatabase(User user) {
        databaseContext.addUserToDatabase(user);
    }

    public User getUserFromFontys() {
        JSONObject object = fontysContext.getCurrentUser();
        return ConverterUtility.jsonToUser(object);
    }

    public User getUserFromDatabase(String id) {
        User user = databaseContext.getUserFromDatabase(id);
        user.getTransactionsFromDatabase();
        user.getProjectsFromDatabase();
        return user;
    }
}
