package com.junior.dwan.sharepictures;

import android.app.Fragment;

/**
 * Created by Might on 18.08.2016.
 */
public class PictureListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new PictureListFragment();
    }
}
