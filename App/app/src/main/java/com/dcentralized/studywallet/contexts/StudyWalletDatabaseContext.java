package com.dcentralized.studywallet.contexts;

import android.util.Log;

import com.dcentralized.studywallet.contexts.interfaces.IStudyWalletContext;
import com.dcentralized.studywallet.models.User;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * This class handles communication with the database
 *
 * @author Tom de Wildt
 */
public class StudyWalletDatabaseContext implements IStudyWalletContext {
    private static final String TAG = StudyWalletDatabaseContext.class.getSimpleName();
    private FirebaseFirestore database;

    /**
     * StudyWalletDatabaseContext constructor, creates the firestore database
     *
     * @author Tom de Wildt
     */
    public StudyWalletDatabaseContext() {
        database = FirebaseFirestore.getInstance();
    }

    /**
     * Checks if the user is in the database
     *
     * @param id userId
     * @return true if user is in database
     * @author Tom de Wildt
     */
    @Override
    public boolean isUserInDatabase(String id) {
        try {
            DocumentReference reference = database.collection("users").document(id);
            DocumentSnapshot document = Tasks.await(reference.get());

            if (document.exists()) {
                return true;
            }
            return false;
        } catch (ExecutionException e) {
            Log.e(TAG, "ExecutionException occurred", e);
            return false;
        } catch (InterruptedException e) {
            Log.e(TAG, "InterruptException occurred", e);
            return false;
        }
    }

    /**
     * Add a user to the database
     *
     * @param user
     * @return true if successful
     * @author Tom de Wildt
     */
    @Override
    public boolean addUserToDatabase(User user) {
        try {
            Tasks.await(database.collection("users").document(user.getId()).set(user));

            Map<String, Object> map = new HashMap<>();
            map.put("projects", new ArrayList<>());
            map.put("transactions", new ArrayList<>());

            Tasks.await(database.collection("users").document(user.getId()).set(map, SetOptions.merge()));
            return true;
        } catch (ExecutionException e) {
            Log.e(TAG, "ExecutionException occurred", e);
            return false;
        } catch (InterruptedException e) {
            Log.e(TAG, "InterruptException occurred", e);
            return false;
        }
    }

    /**
     * Gets a user from the database
     *
     * @param id userId
     * @return user or null
     * @author Tom de Wildt
     */
    @Override
    public User getUserFromDatabase(String id) {
        try {
            DocumentReference reference = database.collection("users").document(id);
            DocumentSnapshot document = Tasks.await(reference.get());

            User user = document.toObject(User.class);
            user.setId(document.getId());

            return user;
        } catch (ExecutionException e) {
            Log.e(TAG, "ExecutionException occurred", e);
            return null;
        } catch (InterruptedException e) {
            Log.e(TAG, "InterruptException occurred", e);
            return null;
        }
    }
}
