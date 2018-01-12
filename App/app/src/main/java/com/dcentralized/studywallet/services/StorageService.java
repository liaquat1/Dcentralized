package com.dcentralized.studywallet.services;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * This class handles storing data to the shared preferences for communication with the widget
 *
 * @author Tom de Wildt
 */
public class StorageService {
    // Preferences Keys
    public static final String ID_PREF_KEY = StorageService.class.getSimpleName().toUpperCase() + "_ID";

    // Class Variables
    private static final String TAG = StorageService.class.getSimpleName();
    private static StorageService instance;
    private Context context;

    /**
     * Instantiates the class
     *
     * @param context activity context
     * @author Tom de Wildt
     */
    private StorageService(Context context) {
        this.context = context;
    }

    /**
     * Stores a value to the shared preferences
     *
     * @param key of the value to store
     * @param value to store
     * @author Tom de Wildt
     */
    public void storeValue(String key, String value) {
        SharedPreferences preferences = ((Activity)context).getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * Loads a value from the shared preferences
     *
     * @param key of the string to load
     * @return value
     * @author Tom de Wildt
     */
    public String loadValue(String key) {
        SharedPreferences preferences = ((Activity)context).getPreferences(Context.MODE_PRIVATE);
        return preferences.getString(key, null);
    }

    /**
     * Deletes a value from the shared preferences
     *
     * @param key of string to remove
     * @author Tom de Wildt
     */
    public void deleteValue(String key) {
        SharedPreferences preferences = ((Activity)context).getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key);
        editor.commit();
    }

    /**
     * Returns the instance of the storage service class, if it's null it will create a new one
     *
     * @param context the activity
     * @return instance
     * @author Tom de Wildt
     */
    public static StorageService getInstance(Context context) {
        if (instance == null) {
            instance = new StorageService(context);
        }
        if (instance.context != context) {
            instance.context = context;
        }

        return instance;
    }
}
