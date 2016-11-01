package com.junior.dwan.sharepictures.ui.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.util.Log;

import com.junior.dwan.sharepictures.utils.ConstantManager;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Might on 23.09.2016.
 */
public class LoadPhotoDialog extends DialogFragment {
    private File mPhotoFile;

    public static LoadPhotoDialog newInstance(Uri uri) {
        Bundle args = new Bundle();
        args.putSerializable(ConstantManager.EXTRA_PHOTO_FROM_GALLERY, uri.toString());

        LoadPhotoDialog fragment = new LoadPhotoDialog();
        fragment.setArguments(args);

        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        System.out.println("Dialog");
        String[] selectItems = {"Camera", "Gallery Photo", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setItems(selectItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        loadFromCamera();
                        break;
                    case 1:
                        loadFromGallery();
                        break;
                    case 2:
                        break;
                }
            }
        });

        return builder.create();
    }

    private void loadFromCamera() {
        Intent loadFromCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Log.i("TAGTAG", "intent load");
        try {
            mPhotoFile = createFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadFromCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mPhotoFile));
        getActivity().getSharedPreferences(ConstantManager.PREF_NAME, Context.MODE_PRIVATE).edit().putString(ConstantManager.PREF_CAMERA, Uri.fromFile(mPhotoFile).toString()).apply();
        getActivity().startActivityForResult(loadFromCameraIntent, ConstantManager.REQUEST_CAMERA);
    }

    private void loadFromGallery() {
        Intent loadFromGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        loadFromGalleryIntent.setType("image/*");
        getActivity().startActivityForResult(Intent.createChooser(loadFromGalleryIntent, "Выберите фото"), ConstantManager.REQUEST_GALLERY);
    }

    private File createFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd:HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File fileDirection = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        File image = File.createTempFile(imageFileName, ".jpg", fileDirection);

        ContentValues cv = new ContentValues();
        cv.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
        cv.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        cv.put(MediaStore.MediaColumns.DATA, image.getAbsolutePath());

        getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, cv);
        return image;
    }
}
