package com.junior.dwan.sharepictures;

import java.util.UUID;

/**
 * Created by Might on 18.08.2016.
 */
public class Letter {

    private UUID mUUID;
    private String mEmail;
    private String mSubject;

    public Letter(){
        mUUID=UUID.randomUUID();

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



}

