package io.keinix.justthetext.main.domain.usecases;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.support.media.ExifInterface;
import android.util.Log;

import java.io.IOException;

public final class RotateBitmap {

    public static final String TAG = RotateBitmap.class.getSimpleName();

    public static Bitmap rotateBitmapRightSideUp(Bitmap bitmap, String filePath) {
        int imageOrientation = 0;
        try {
            imageOrientation = getImageOrientation(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rotateBitmap(bitmap, imageOrientation);
    }

    private static int getImageOrientation(String filePath) throws IOException {
        ExifInterface exifInterface = new ExifInterface(filePath);
        int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_UNDEFINED);

        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                Log.d(TAG, "Orientation 90");
                return 90;
            case ExifInterface.ORIENTATION_ROTATE_180:
                Log.d(TAG, "Orientation 180");
                return 180;
            case ExifInterface.ORIENTATION_ROTATE_270:
                Log.d(TAG, "Orientation 260");
                return 270;
            default: return 0;
        }
    }

    private static Bitmap rotateBitmap(Bitmap bitmap, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                bitmap.getHeight(), matrix, true);
    }
}
