package com.junior.dwan.sharepictures.ui.fragments;

import android.app.ActionBar;
import android.app.ListFragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.junior.dwan.sharepictures.R;
import com.junior.dwan.sharepictures.ui.activities.ShareActivity;
import com.junior.dwan.sharepictures.ui.data.managers.Letter;
import com.junior.dwan.sharepictures.ui.data.managers.LetterLab;
import com.junior.dwan.sharepictures.ui.utils.ConstantManager;

import java.util.ArrayList;

import static android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM;

/**
 * Created by Might on 18.08.2016.
 */
public class PictureListFragment extends ListFragment {
    ArrayList<Letter> mLetters;
    public static final String TAG = "myLogs";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
//        getActivity().getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        getActivity().getActionBar().setCustomView(R.layout.actionbar);
        mLetters = LetterLab.getInstance(getActivity()).getLetters();

        LetterAdapter adapter = new LetterAdapter(mLetters);
        setListAdapter(adapter);


    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Получение объекта Crime от адаптера
        Letter letter = ((LetterAdapter) getListAdapter()).getItem(position);
        // Запуск ShareActivity
        Intent i = new Intent(getActivity(), ShareActivity.class);
        i.putExtra(ConstantManager.EXTRA_LETTER_ID, letter.getUUID());
        startActivity(i);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.empty_list, null, false);

        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(),getString(R.string.digit_keyboard_font_tatiana));

        Button empty_list_add_crimeButton = (Button) v.findViewById(android.R.id.empty);
//        empty_list_add_crimeButton.setTypeface(typeFace);
        empty_list_add_crimeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Letter l = new Letter();
                LetterLab.getInstance(getActivity()).addLetter(l);
                Intent i = new Intent(getActivity(), ShareActivity.class);
                i.putExtra(ConstantManager.EXTRA_LETTER_ID, l.getUUID());
                startActivityForResult(i, 0);
            }
        });
        return v;
    }

    private class LetterAdapter extends ArrayAdapter<Letter> {
        public LetterAdapter(ArrayList<Letter> letters) {
            super(getActivity(), 0, letters);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Если мы не получили представление, заполняем его
            if (convertView == null)
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_letter, null);

            // Настройка представления для объекта Crime
            Letter l = getItem(position);
            ImageView poster = (ImageView) convertView.findViewById(R.id.letter_imageView);
            poster.setBackgroundResource((R.drawable.ic_camera_alt_black_24dp));
            if (l.getPhotoUri() != null) {
                poster.setImageURI(l.getPhotoUri());
            }
            TextView textViewEmail = (TextView) convertView.findViewById(R.id.tvEmailAdress);
            if (l.getEmail() != null) {
                textViewEmail.setText(l.getEmail());
            } else textViewEmail.setText("No email");
            TextView textViewSubject = (TextView) convertView.findViewById(R.id.tvSubjectText);
            if (l.getSubject() != null) {
                textViewSubject.setText(l.getSubject());
            } else {
                textViewSubject.setText("No subject");
            }
            return convertView;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((LetterAdapter) getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_menu_bar, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add_letter:
                Letter l = new Letter();
                LetterLab.getInstance(getActivity()).addLetter(l);
                Intent i = new Intent(getActivity(), ShareActivity.class);
                i.putExtra(ConstantManager.EXTRA_LETTER_ID, l.getUUID());
                startActivityForResult(i, 0);
        }
        return true;
    }
}

