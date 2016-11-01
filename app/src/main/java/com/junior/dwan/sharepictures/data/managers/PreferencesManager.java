package com.junior.dwan.sharepictures.data.managers;

import android.content.SharedPreferences;
import android.net.Uri;

import com.junior.dwan.sharepictures.utils.ConstantManager;
import com.junior.dwan.sharepictures.utils.ShareLetterApplication;

import java.io.File;

/**
 * Created by Might on 01.11.2016.
 */

public class PreferencesManager {
    private SharedPreferences mSharedPreferences;

    public PreferencesManager() {
        this.mSharedPreferences = ShareLetterApplication.getSharedPreferences();
    }

    public void saveCaptureFromDialog(File photoFile) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(ConstantManager.PREF_CAMERA, Uri.fromFile(photoFile).toString());
        editor.apply();
    }

    public String loadCaptureFromDialog() {
        return mSharedPreferences.getString(ConstantManager.PREF_CAMERA, "");
    }

}
