package com.dcentralized.studywallet.repositories;

import android.content.Context;

import com.dcentralized.studywallet.contexts.FontysAPIContext;
import com.dcentralized.studywallet.contexts.StudyWalletDatabaseContext;
import com.dcentralized.studywallet.contexts.interfaces.IFontysAPIContext;
import com.dcentralized.studywallet.contexts.interfaces.IStudyWalletDatabaseContext;
import com.dcentralized.studywallet.models.User;

public class StudyWalletRepository {
    private IFontysAPIContext fontysContext;
    private IStudyWalletDatabaseContext databaseContext;

    public StudyWalletRepository(Context context) {
        fontysContext = new FontysAPIContext(context);
        databaseContext = new StudyWalletDatabaseContext();
    }

    public boolean isUserInDatabase() {
        String id = fontysContext.getCurrentUserId();
        return databaseContext.isUserInDatabase(id);
    }

    public void addUserToDatabase(User user) {

    }

    public User getUserFromFontys() {
        return null;
    }

    public User getUserFromDatabase() {
        return null;
    }
}
