package com.junior.dwan.sharepictures.ui.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
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
        String[] selectItems = {getString(R.string.dialog_delete_all_yes), getString(R.string.dialog_delete_all_no)};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dialog_delete_all_title);
        builder.setItems(selectItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    //pressed yes
                    case 0:
                        sendResult(Activity.RESULT_OK);
                        break;
                    //pressed no
                    case 1:
                        break;
                }
            }
        });
        return builder.create();
    }
}
