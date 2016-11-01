package com.junior.dwan.sharepictures.ui.activities;

import android.app.Fragment;
import android.content.Intent;

import com.junior.dwan.sharepictures.R;
import com.junior.dwan.sharepictures.ui.fragments.PictureListFragment;

/**
 * Created by Might on 18.08.2016.
 */
public class PictureListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new PictureListFragment();
    }

}
