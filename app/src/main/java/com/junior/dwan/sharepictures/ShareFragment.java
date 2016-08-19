package com.junior.dwan.sharepictures;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Might on 19.08.2016.
 */
public class ShareFragment extends Fragment {
    private Letter mLetter;

    @Override
    public void onCreate(Bundle  savedInstanceState){
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup parent,Bundle savedInstanceState){
        View v=inflater.inflate(R.layout.activity_share,parent,false);

        return v;
    }
}
