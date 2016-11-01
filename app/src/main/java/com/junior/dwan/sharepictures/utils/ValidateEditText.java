package com.junior.dwan.sharepictures.utils;

/**
 * Created by Might on 01.11.2016.
 */

public class ValidateEditText {


    public final static boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
