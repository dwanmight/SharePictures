package com.junior.dwan.sharepictures.ui.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.junior.dwan.sharepictures.R;
import com.junior.dwan.sharepictures.data.managers.Letter;
import com.junior.dwan.sharepictures.data.managers.LetterLab;
import com.junior.dwan.sharepictures.ui.dialogs.LoadPhotoDialog;
import com.junior.dwan.sharepictures.utils.ConstantManager;
import com.junior.dwan.sharepictures.utils.ValidateEditText;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Might on 19.08.2016.
 */
public class ShareFragment extends Fragment {
    private Letter mLetter;
    EditText mEmail;
    EditText mSubject;
    EditText mEmailMessage;
    ImageView mImageView;
    Button btnShare;
    LoadPhotoDialog mLoadPhotoDialog;
    Snackbar mSnackbar;

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

        UUID letterId = (UUID) getArguments().getSerializable(ConstantManager.EXTRA_LETTER_ID);
        mLetter = LetterLab.getInstance(getActivity()).getLetter(letterId);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_share, parent, false);
        setupToolbar();
        initializeEditTextAndAddListener(v);
        initializeButtonsAndListener(v);
        checkTextForFields();
        return v;
    }


    private void initializeButtonsAndListener(View v) {
        mImageView = (ImageView) v.findViewById(R.id.imgView_Photo);
        if (mLetter.getPhotoUri() != null) {
            Picasso.with(getActivity())
                    .load(mLetter.getPhotoUri())
                    .into(mImageView);
        }
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoadPhotoDialog = new LoadPhotoDialog();
                mLoadPhotoDialog.setTargetFragment(ShareFragment.this, ConstantManager.REQUEST_DIALOG_CHOOSE_PHOTO);
                mLoadPhotoDialog.show(getFragmentManager(), ConstantManager.DIALOG_ADD_PHOTO);
            }
        });

        btnShare = (Button) v.findViewById(R.id.btnShare);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOnline()) {
                    sendEmail();
                } else {
                    showSnackBar("Turn on your internet connection");
                }
            }
        });
    }

    private void initializeEditTextAndAddListener(View v) {
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
    }

    private void checkTextForFields() {
        if (mLetter.getEmail() != null) {
            mEmail.setText(mLetter.getEmail());
        }

        if (mLetter.getSubject() != null) {
            mSubject.setText(mLetter.getSubject());
        }

        if (mLetter.getEmailMessage() != null) {
            mEmailMessage.setText(mLetter.getEmailMessage());
        }
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_back);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void sendEmail() {
        Intent sendEmailIntent = new Intent(Intent.ACTION_SEND);
        if (ValidateEditText.isEmailValid(mEmail.getText())) {
            sendEmailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{mEmail.getText().toString()});
            sendEmailIntent.putExtra(Intent.EXTRA_SUBJECT, mSubject.getText().toString());
            sendEmailIntent.setType("message/rfc822");
            if (mLetter.getPhotoUri() != null) {
                sendEmailIntent.setType("image/*");
                sendEmailIntent.putExtra(Intent.EXTRA_STREAM, mLetter.getPhotoUri());
            }
            startActivity(Intent.createChooser(sendEmailIntent, "Choose an email client: "));
        } else {
            showSnackBar("Please enter a valid address");

        }

    }

    protected boolean isOnline() {
        String cs = Context.CONNECTIVITY_SERVICE;
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(cs);
        if (cm.getActiveNetworkInfo() == null) {
            return false;
        } else {
            return true;
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
                    putImageIntoView();
                }
            }
            break;
            case ConstantManager.REQUEST_CAMERA: {
                System.out.println(resultCode);
                if (resultCode == Activity.RESULT_OK) {
                    String s = LetterLab.getInstance(getActivity()).getPreferencesManager().loadCaptureFromDialog();
                    mLetter.setPhotoUri(Uri.parse(s));
                    putImageIntoView();
                    break;
                }
            }
        }
    }

    private void putImageIntoView() {
        Picasso.with(getActivity())
                .load(mLetter.getPhotoUri())
                .into(mImageView);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_share_fragment_bar, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_delete:
                LetterLab.getInstance(getActivity()).deleteCrime(mLetter);
                getActivity().setResult(Activity.RESULT_CANCELED);
                getActivity().finish();
                break;
            case android.R.id.home:
                if (NavUtils.getParentActivityName(getActivity()) != null) {
                    NavUtils.navigateUpFromSameTask(getActivity());
                }
                return true;
        }
        return true;
    }

    private void showSnackBar(String msg) {
        mSnackbar = Snackbar.make(getView(), msg, Snackbar.LENGTH_LONG);
        mSnackbar.getView().setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorSnackBar));
        mSnackbar.show();
    }

    @Override
    public void onPause() {
        super.onPause();
        LetterLab.getInstance(getActivity()).saveLetters();
    }
}
