package com.junior.dwan.sharepictures;

import android.app.Fragment;

/**
 * Created by Might on 19.08.2016.
 */
public class ShareActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new ShareFragment();
    }
}
