package com.dcentralized.studywallet.repositories;

import android.content.Context;

import com.dcentralized.studywallet.contexts.FontysAPIContext;
import com.dcentralized.studywallet.contexts.StudyWalletDatabaseContext;
import com.dcentralized.studywallet.contexts.interfaces.IFontysAPIContext;
import com.dcentralized.studywallet.contexts.interfaces.IStudyWalletDatabaseContext;
import com.dcentralized.studywallet.models.User;

import org.json.JSONObject;

public class StudyWalletRepository {
    private IFontysAPIContext fontysContext;
    private IStudyWalletDatabaseContext databaseContext;

    public StudyWalletRepository(Context context) {
        fontysContext = new FontysAPIContext(context);
        databaseContext = new StudyWalletDatabaseContext();
    }

    public String getUserId() {
        return fontysContext.getCurrentUserId();
    }

    public boolean isUserInDatabase() {
        String id = fontysContext.getCurrentUserId();
        return databaseContext.isUserInDatabase(id);
    }

    public void addUserToDatabase(User user) {
        databaseContext.addUserToDatabase(user.getId(), user.getFirstname(), user.getLastname(), user.getEmployeeId(), user.getEmail(), user.getType().toString());
    }

    public User getUserFromFontys() {
        JSONObject object = fontysContext.getCurrentUser();
        // TODO: convert JSON to User
        return null;
    }

    public User getUserFromDatabase(String id) {
        return databaseContext.getUserFromDatabase(id);
    }
}
