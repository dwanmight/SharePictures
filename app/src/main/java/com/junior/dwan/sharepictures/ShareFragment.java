package com.junior.dwan.sharepictures;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
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
        setHasOptionsMenu(true);
        if(NavUtils.getParentActivityName(getActivity())!=null){
            getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup parent,Bundle savedInstanceState){
        View v=inflater.inflate(R.layout.activity_share,parent,false);

        return v;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                if(NavUtils.getParentActivityName(getActivity())!=null){
                    NavUtils.navigateUpFromSameTask(getActivity());
                }
                return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
