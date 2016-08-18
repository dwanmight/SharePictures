package com.junior.dwan.sharepictures;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by Might on 18.08.2016.
 */
public abstract class SingleFragmentActivity extends FragmentActivity {
    protected abstract Fragment createFragment();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        FragmentManager fragmentManager=getFragmentManager();
        Fragment fragment=fragmentManager.findFragmentById(R.id.fragment_container);
        if(fragment==null){
            fragment= createFragment();
            fragmentManager.beginTransaction().add(R.id.fragment_container,fragment).commit();
        }
    }
}
