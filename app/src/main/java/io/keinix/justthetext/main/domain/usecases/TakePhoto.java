package io.keinix.justthetext.main.domain.usecases;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.keinix.justthetext.main.MainActivity;

public class TakePhoto {

    public static final int REQUEST_CODE_PHOTO = 100;
    private static final String FILE_URI = "io.keinix.fileprovider";


    private Context context;
    private File photoFile;

    public TakePhoto(Context context) {
        this.context = context;
    }

    public void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            File photoFIle = null;

            try {
                photoFIle = createImageFile();
            } catch (IOException e) { e.printStackTrace(); }

            if (photoFIle != null) {
                Uri photoUri = FileProvider.getUriForFile(context, FILE_URI, photoFIle);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                ((MainActivity) context).startActivityForResult(intent, REQUEST_CODE_PHOTO);
            }
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        photoFile = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        return photoFile;
    }

    public File getPhotoFile() {
        return photoFile;
    }
}
