package com.dcentralized.studywallet.contexts.interfaces;

import org.json.JSONObject;

/**
 * This class handles communication with the Fontys API
 *
 * @author Tom de Wildt
 */
public interface IFontysContext {
    /**
     * Gets the current users id bound to the token
     *
     * @return null or the id
     * @author Tom de Wildt
     */
    String getCurrentUserId();

    /**
     * Gets the current users bound to the token
     *
     * @return a json user or null
     * @author Tom de Wildt
     */
    JSONObject getCurrentUser();
}
