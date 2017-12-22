package com.dcentralized.studywallet.utilities;

import android.util.Log;
import com.dcentralized.studywallet.models.User;
import com.dcentralized.studywallet.models.UserType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ConverterUtility {
    private static final String TAG = ConverterUtility.class.getSimpleName();

    public static User jsonToUser(JSONObject object) {
        try {
            String id = object.getString("id");
            String firstname = object.getString("givenName");
            String lastname = object.getString("surName");
            String email = object.getString("mail");
            int employeeId = object.getInt("employeeId");
            UserType type = jsonToUserType(object);

            return new User(id, firstname, lastname, employeeId, email, type);
        } catch (JSONException e) {
            Log.e(TAG, "JSONException occurred", e);
            return null;
        }
    }

    public static UserType jsonToUserType(JSONObject object) {
        try {
            JSONArray array = object.getJSONArray("affiliations");
            if (array.toString().contains("student")) {
                return UserType.Student;
            } else {
                return UserType.Docent;
            }
        } catch (JSONException e) {
            Log.e(TAG, "JSONException occurred", e);
            return UserType.Unknown;
        }
    }
}
