package com.junior.dwan.sharepictures.utils;

/**
 * Created by Might on 23.09.2016.
 */
public interface ConstantManager {
    int REQUEST_DIALOG_CHOOSE_PHOTO = 400;
    int REQUEST_GALLERY = 100;
    int REQUEST_CAMERA = 200;
    int REQUEST_DELETE_ALL = 300;
    String EXTRA_PHOTO_FROM_GALLERY = "EXTRA_PHOTO_FROM_GALLERY";
    String DIALOG_ADD_PHOTO = "DIALOG_ADD_PHOTO";
    String DIALOG_DELETE_ALL = "DIALOG_DELETE_ALL";

    String EXTRA_LETTER_ID = "com.junior.dwan.sharepictures.letter_id";

    String JSON_ID = "JSON_ID";
    String JSON_EMAIL = "JSON_EMAIL";
    String JSON_SUBJECT = "JSON_SUBJECT";
    String JSON_EMAIL_MESSAGE = "JSON_EMAIL_MESSAGE";
    String JSON_PHOTO_URI = "JSON_PHOTO_URI";
    String FILENAME = "letters.json";

    String PREF_CAMERA = "PREF_CAMERA";
    String PREF_DIALOG_DELETE_ALL = "PREF_DIALOG_DELETE_ALL";
}
