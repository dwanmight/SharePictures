package com.junior.dwan.sharepictures.ui.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.junior.dwan.sharepictures.R;
import com.junior.dwan.sharepictures.ui.data.managers.Letter;
import com.junior.dwan.sharepictures.ui.data.managers.LetterLab;
import com.junior.dwan.sharepictures.ui.data.managers.LoadPhotoDialog;
import com.junior.dwan.sharepictures.ui.utils.ConstantManager;
import com.squareup.picasso.Picasso;

import java.util.UUID;

import static android.R.attr.data;

/**
 * Created by Might on 19.08.2016.
 */
public class ShareFragment extends Fragment {
    private Letter mLetter;
    EditText mEmail;
    EditText mSubject;
    EditText mEmailMessage;
    ImageView mImageView;
    //    TextView mAddPhotoTextView;
    Button btnShare;
    LoadPhotoDialog mLoadPhotoDialog;


    public static ShareFragment newInstance(UUID letterId) {
        Bundle args = new Bundle();
        args.putSerializable(ConstantManager.EXTRA_LETTER_ID, letterId);
        ShareFragment fragment = new ShareFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        UUID letterId = (UUID) getActivity().getIntent().getSerializableExtra(EXTRA_LETTER_ID);
        UUID letterId = (UUID) getArguments().getSerializable(ConstantManager.EXTRA_LETTER_ID);
        mLetter = LetterLab.getInstance(getActivity()).getLetter(letterId);

        setHasOptionsMenu(true);

//        if (NavUtils.getParentActivityName(getActivity()) != null) {
//            getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
//        }
//        getActivity().getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        getActivity().getActionBar().setCustomView(R.layout.actionbar);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_share, parent, false);

        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(), getString(R.string.digit_keyboard_font_tatiana));
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_back);


        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("title");

        customizeToolbar(toolbar);


//        mAddPhotoTextView = (TextView) v.findViewById(R.id.tvAddPhoto);
        mImageView = (ImageView) v.findViewById(R.id.imgView_Photo);
        if (mLetter.getPhotoUri() != null) {
            Picasso.with(getActivity())
                    .load(mLetter.getPhotoUri())
                    .into(mImageView);
//            mAddPhotoTextView.setTypeface(typeFace);
//            mAddPhotoTextView.setVisibility(View.GONE);
        }
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoadPhotoDialog = new LoadPhotoDialog();
                mLoadPhotoDialog.setTargetFragment(ShareFragment.this, ConstantManager.REQUEST_GALLERY);
                mLoadPhotoDialog.show(getFragmentManager(), ConstantManager.DIALOG_ADD_PHOTO);
            }
        });

        mEmail = (EditText) v.findViewById(R.id.etEmail);
        mEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mLetter.setEmail(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        mSubject = (EditText) v.findViewById(R.id.etSubjects);
        mSubject.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mLetter.setSubject(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        btnShare = (Button) v.findViewById(R.id.btnShare);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
                btnShare.setText(mLetter.getUUID().toString());
            }
        });
        mEmailMessage = (EditText) v.findViewById(R.id.etBody);
        mEmailMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mLetter.setEmailMessage(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        return v;
    }

    private void sendEmail() {
        Intent sendEmailIntent = new Intent(Intent.ACTION_SEND);
        sendEmailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{mEmail.getText().toString()});
        sendEmailIntent.putExtra(Intent.EXTRA_SUBJECT, mSubject.getText().toString());
        sendEmailIntent.setType("message/rfc822");
        if (mLetter.getPhotoUri() != null) {
            sendEmailIntent.setType("image/*");
            sendEmailIntent.putExtra(Intent.EXTRA_STREAM, mLetter.getPhotoUri());
        }
        startActivity(Intent.createChooser(sendEmailIntent, "choose an email client: "));
    }


    public void customizeToolbar(Toolbar toolbar) {
        // Save current title and subtitle
        final CharSequence originalTitle = toolbar.getTitle();
        // Temporarily modify title and subtitle to help detecting each
        toolbar.setTitle("title");
        for(int i = 0; i < toolbar.getChildCount(); i++) {
            View view = toolbar.getChildAt(i);


            if (view instanceof TextView) {
                TextView textView = (TextView) view;

                if (textView.getText().equals("title")) {
                    // Customize title's TextView
                    Toolbar.LayoutParams params = new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);
                    params.gravity = Gravity.CENTER;
                    textView.setLayoutParams(params);

                    // Apply custom font
                    Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), getString(R.string.digit_keyboard_font_tatiana));
                    textView.setTypeface(typeface);
                }
            }
            // Restore title and subtitle
            toolbar.setTitle(originalTitle);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mLoadPhotoDialog.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case ConstantManager.REQUEST_GALLERY: {
                System.out.println(resultCode);
                if (resultCode == Activity.RESULT_OK && data != null) {
                    mLetter.setPhotoUri(data.getData());
                    Picasso.with(getActivity())
                            .load(mLetter.getPhotoUri())
                            .into(mImageView);
//                    mAddPhotoTextView.setVisibility(View.GONE);
                    System.out.println(data.getData());
                    System.out.println("loaded");
                }

            }
            break;
            case ConstantManager.REQUEST_CAMERA: {
                System.out.println(resultCode);
//                Uri uri = data.getData();
//                System.out.println(uri);
                if (resultCode == Activity.RESULT_OK && data != null) {
                    Picasso.with(getActivity())
                            .load(data.getData())
                            .into(mImageView);
                    System.out.println("camera done");
                    Log.i("TAG", data.getData().toString());
                }
                break;
            }
        }
    }
}
