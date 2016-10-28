package com.junior.dwan.sharepictures.ui.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.junior.dwan.sharepictures.R;

import static com.junior.dwan.sharepictures.R.id.toolbar;

/**
 * Created by Might on 18.08.2016.
 */
public abstract class SingleFragmentActivity extends AppCompatActivity {
    protected abstract Fragment createFragment();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
//        customizeToolbar(toolbar);

        FragmentManager fragmentManager=getFragmentManager();
        Fragment fragment=fragmentManager.findFragmentById(R.id.fragment_container);
        if(fragment==null){
            fragment= createFragment();
            fragmentManager.beginTransaction().add(R.id.fragment_container,fragment).commit();
        }
    }

    public void customizeToolbar(Toolbar toolbar) {
        // Save current title and subtitle
        final CharSequence originalTitle = toolbar.getTitle();
        // Temporarily modify title and subtitle to help detecting each
        toolbar.setTitle("title");

        View view = toolbar.getChildAt(0);

        if(view instanceof TextView){
            TextView textView = (TextView) view;

            if(textView.getText().equals("title")){
                // Customize title's TextView
                Toolbar.LayoutParams params = new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);
                params.gravity = Gravity.CENTER_HORIZONTAL;
                textView.setLayoutParams(params);

                // Apply custom font
                Typeface typeface = Typeface.createFromAsset(getAssets(), getString(R.string.digit_keyboard_font_tatiana));
                textView.setTypeface(typeface);
            }
        }
        // Restore title and subtitle
        toolbar.setTitle(originalTitle);
    }
}
