package io.keinix.justthetext.main.domain.usecases;

import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;

import io.keinix.justthetext.main.MainActivity;

public class TaskPhoto {

    public static final int REQUEST_CODE_PHOTO = 100;

    private Context context;

    public TaskPhoto(Context context) {
        this.context = context;
    }

    public void taskPicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            ((MainActivity) context).startActivityForResult(intent, REQUEST_CODE_PHOTO);
        }
    }
}
