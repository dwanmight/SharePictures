package com.junior.dwan.sharepictures;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Might on 18.08.2016.
 */
public class PictureListFragment extends ListFragment {
    ArrayList<Letter> mLetters;
    public static final String TAG="myLogs";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Drafts and sent messages");
        mLetters=LetterLab.getInstance(getActivity()).getLetters();

//        ArrayAdapter<Letter> adapter =
//                new ArrayAdapter<Letter>(getActivity(),
//                        android.R.layout.simple_list_item_1,
//                        mLetters);
        LetterAdapter adapter=new LetterAdapter(mLetters);
        setListAdapter(adapter);


    }

    @Override
    public void onListItemClick(ListView l,View v,int position,long id){
//        Letter letter=(Letter)getListAdapter().getItem(position);
//        Letter letter=((LetterAdapter)getListAdapter()).getItem(position);
//        Log.i(TAG, "Was clicked " + position + " " + letter.getUUID());
        Intent i=new Intent(getActivity(),ShareActivity.class);
        startActivity(i);



    }

    private class LetterAdapter extends ArrayAdapter<Letter> {
        public LetterAdapter(ArrayList<Letter> letters) {
            super(getActivity(), 0, letters);
        }

        @Override
        public View getView(int position,View convertView,ViewGroup parent){
            // Если мы не получили представление, заполняем его
            if(convertView==null)
                convertView=getActivity().getLayoutInflater().inflate(R.layout.list_item_letter,null);

            // Настройка представления для объекта Crime
            Letter l=getItem(position);

            TextView textViewEmail=(TextView)convertView.findViewById(R.id.tvEmailAdress);
            textViewEmail.setText(l.getEmail());
            TextView textViewSubject=(TextView)convertView.findViewById(R.id.tvSubjectText);
            textViewSubject.setText(l.getSubject());

            return convertView;

        }
    }
}

