package com.junior.dwan.sharepictures.data.managers;

import android.content.Context;
import android.util.Log;

import com.junior.dwan.sharepictures.utils.ConstantManager;
import com.junior.dwan.sharepictures.utils.JSONSerializer;

import java.util.ArrayList;
import java.util.UUID;

import static android.content.ContentValues.TAG;

/**
 * Created by Might on 18.08.2016.
 */
public class LetterLab {
    private static LetterLab sLetterLab;
    private ArrayList<Letter> mLetters;
    private JSONSerializer mSerializer;
    private Context mAppContext;

    private LetterLab(Context appContext) {
        mAppContext = appContext;
        mLetters = new ArrayList<Letter>();
        mSerializer = new JSONSerializer(mAppContext, ConstantManager.FILENAME);

        try {
            mLetters = mSerializer.loadLetters();
            Log.i(TAG, mLetters.toString());
        } catch (Exception e) {
            mLetters = new ArrayList<Letter>();
            Log.e(TAG, "Error loading crimes: ", e);
        }
//        for (int i = 0; i <100 ; i++) {
//            Letter l=new Letter();
//            l.setEmail("email "+i);
//            l.setSubject("subject " + i);
//            mLetters.add(l);
//        }
    }

    public static LetterLab getInstance(Context c) {
        if (sLetterLab == null)
            sLetterLab = new LetterLab(c.getApplicationContext());
        return sLetterLab;
    }

    public Letter getLetter(UUID uuid) {
        for (Letter l : mLetters) {
            if (l.getUUID().equals(uuid))
                return l;
        }
        return null;

    }

    public ArrayList<Letter> getLetters() {
        return mLetters;
    }

    public void addLetter(Letter l) {
        mLetters.add(l);
        saveLetters();
    }

    public void deleteCrime(Letter l) {
        mLetters.remove(l);
        saveLetters();
    }

    public void deleteAllCrime(ArrayList list) {
        list.clear();
        saveLetters();
    }

    public boolean saveLetters() {
        try {
            mSerializer.saveLetters(mLetters);
            Log.i(TAG, "Letters saved to file");
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error saving letters: " + e);
            return false;
        }
    }

}
