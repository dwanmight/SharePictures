package com.junior.dwan.sharepictures.data.managers;

import android.net.Uri;

import com.junior.dwan.sharepictures.utils.ConstantManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

import static com.junior.dwan.sharepictures.utils.ConstantManager.JSON_PHOTO_URI;

/**
 * Created by Might on 18.08.2016.
 */
public class Letter {

    private UUID mUUID;
    private String mEmail;
    private String mSubject;
    private String mEmailMessage;
    private Uri mPhotoUri;

    public Letter() {
        mUUID = UUID.randomUUID();
    }

    public Letter(JSONObject json) throws JSONException {
        mUUID = UUID.fromString(json.getString(ConstantManager.JSON_ID));
        mEmail = json.getString(ConstantManager.JSON_EMAIL);
        mSubject = json.getString(ConstantManager.JSON_SUBJECT);
        mEmailMessage = json.getString(ConstantManager.JSON_EMAIL_MESSAGE);
        if (json.has(JSON_PHOTO_URI))
            mPhotoUri = Uri.parse(json.getString(JSON_PHOTO_URI));
    }


    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(ConstantManager.JSON_ID, mUUID.toString());
        if (mEmail != null) {
            json.put(ConstantManager.JSON_EMAIL, mEmail);
        } else {
            json.put(ConstantManager.JSON_EMAIL, "No email");
        }
        if (mSubject != null) {
            json.put(ConstantManager.JSON_SUBJECT, mSubject);
        } else {
            json.put(ConstantManager.JSON_SUBJECT, "No Subject");

        }
        if (mEmailMessage != null) {
            json.put(ConstantManager.JSON_EMAIL_MESSAGE, mEmailMessage);
        } else {
            json.put(ConstantManager.JSON_EMAIL_MESSAGE, "No text");

        }
        if (mPhotoUri != null)
            json.put(JSON_PHOTO_URI, mPhotoUri.toString());

        return json;
    }

    public UUID getUUID() {
        return mUUID;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getSubject() {
        return mSubject;
    }

    public void setSubject(String subject) {
        mSubject = subject;
    }

    public String getEmailMessage() {
        return mEmailMessage;
    }

    public void setEmailMessage(String emailMessage) {
        mEmailMessage = emailMessage;
    }

    public Uri getPhotoUri() {
        return mPhotoUri;
    }

    public void setPhotoUri(Uri photoUri) {
        mPhotoUri = photoUri;
    }

}

