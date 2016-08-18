package com.junior.dwan.sharepictures;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Might on 18.08.2016.
 */
public class LetterLab {
    private static LetterLab sLetterLab;
    private ArrayList<Letter> mLetters;
    Context mAppContext;
//    private static LetterLab ourInstance = new LetterLab();

    private LetterLab(Context appContext) {
        mAppContext=appContext;
        mLetters=new ArrayList<Letter>();
        for (int i = 0; i <100 ; i++) {
            Letter l=new Letter();
            l.setEmail("email "+i);
            l.setSubject("subject " + i);
            mLetters.add(l);
        }
    }

    public static LetterLab getInstance(Context c) {
        if(sLetterLab==null)
            sLetterLab=new LetterLab(c.getApplicationContext());
        return sLetterLab;
    }

    public ArrayList<Letter> getLetters(){
        return mLetters;
    }


}
