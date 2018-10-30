package io.keinix.justthetext.main.domain.usecases;

import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;

import io.keinix.justthetext.main.MainActivity;

public class TakePhoto {

    public static final int REQUEST_CODE_PHOTO = 100;

    private Context context;

    public TakePhoto(Context context) {
        this.context = context;
    }

    public void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            ((MainActivity) context).startActivityForResult(intent, REQUEST_CODE_PHOTO);
        }
    }
}
