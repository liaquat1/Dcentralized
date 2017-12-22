package com.dcentralized.studywallet.contexts;

import android.content.Context;
import android.util.Log;

import com.dcentralized.studywallet.contexts.interfaces.IFontysContext;
import com.dcentralized.studywallet.services.FontysAuthService;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.apache.commons.lang3.mutable.MutableObject;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * This class handles communication with the Fontys API
 *
 * @author Tom de Wildt
 */
public class FontysAPIContext implements IFontysContext {
    private static final String TAG = FontysAPIContext.class.getSimpleName();
    private SyncHttpClient client;

    /**
     * FontysAPIContext constructor, creates a new http client and adds the authorization header
     *
     * @param context activity context
     * @author Tom de Wildt
     */
    public FontysAPIContext(Context context) {
        client = new SyncHttpClient();
        client.addHeader("Authorization", FontysAuthService.getInstance(context).getToken());
    }

    /**
     * Gets the current users id bound to the token
     *
     * @return null or the id
     * @author Tom de Wildt
     */
    @Override
    public String getCurrentUserId() {
        final MutableObject<String> id = new MutableObject<>();

        client.get("https://api.fhict.nl/people/me", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    super.onSuccess(statusCode, headers, response);
                    id.setValue(response.getString("id"));
                } catch (JSONException e) {
                    Log.e(TAG, "JSONException occurred", e);
                    id.setValue(null);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                id.setValue(null);
            }
        });

        return id.getValue();
    }

    /**
     * Gets the current users bound to the token
     *
     * @return a json user or null
     * @author Tom de Wildt
     */
    @Override
    public JSONObject getCurrentUser() {
        final MutableObject<JSONObject> id = new MutableObject<>();

        client.get("https://api.fhict.nl/people/me", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                id.setValue(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                id.setValue(null);
            }
        });

        return id.getValue();
    }
}
