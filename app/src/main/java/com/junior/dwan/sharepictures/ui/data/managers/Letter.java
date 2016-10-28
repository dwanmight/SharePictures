package com.junior.dwan.sharepictures.ui.data.managers;

import android.net.Uri;
import android.widget.ImageView;

import java.util.UUID;

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

