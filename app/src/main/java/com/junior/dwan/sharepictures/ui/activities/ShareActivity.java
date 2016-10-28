package com.junior.dwan.sharepictures.ui.activities;

import android.app.Fragment;
import android.content.Intent;

import com.junior.dwan.sharepictures.R;
import com.junior.dwan.sharepictures.ui.data.managers.LoadPhotoDialog;
import com.junior.dwan.sharepictures.ui.fragments.ShareFragment;
import com.junior.dwan.sharepictures.ui.utils.ConstantManager;

import java.util.UUID;

/**
 * Created by Might on 19.08.2016.
 */
public class ShareActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
//        return new ShareFragment();

        UUID letterId=(UUID)getIntent().getSerializableExtra(ConstantManager.EXTRA_LETTER_ID);
        return ShareFragment.newInstance(letterId);
    }
//    @Override
//    protected Fragment createFragment() {
////        return new ShareFragment();
//
//        UUID letterId=(UUID)getIntent().getSerializableExtra(ShareFragment.EXTRA_LETTER_ID);
//        return new ShareFragment();
//    }
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data)
        {
            Fragment fragmentObject  = getFragmentManager().findFragmentById(R.id.fragment_container);
            fragmentObject.onActivityResult(requestCode, resultCode, data);
        }
}
