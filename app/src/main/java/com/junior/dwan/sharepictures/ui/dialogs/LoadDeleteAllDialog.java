package com.junior.dwan.sharepictures.ui.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.junior.dwan.sharepictures.R;
import com.junior.dwan.sharepictures.utils.ConstantManager;

/**
 * Created by Might on 30.10.2016.
 */

public class LoadDeleteAllDialog extends DialogFragment {
    SharedPreferences mSharedPreferences;

    private void sendResult(int resultCode) {
        if (getTargetFragment() == null)
            return;

        Intent i = new Intent();
        i.putExtra(ConstantManager.PREF_DIALOG_DELETE_ALL, true);

        getTargetFragment()
                .onActivityResult(getTargetRequestCode(), resultCode, i);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mSharedPreferences=getActivity().getSharedPreferences(ConstantManager.PREF_NAME, Context.MODE_PRIVATE);

        System.out.println("Dialog");
        String[] selectItems = {"Yes", "No"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("          Remove all letters?");
        builder.setItems(selectItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        System.out.println("deleted all");
                        sendResult(Activity.RESULT_OK);
                        break;
                    case 1:
                        System.out.println("deleted false");

                        break;
                }
            }
        });
        return builder.create();
    }
}
