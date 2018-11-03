package io.keinix.justthetext.main.domain.usecases;

import android.graphics.Bitmap;
import android.util.Log;

import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;

public class ConvertImageToText {

    public static final String TAG = ConvertImageToText.class.getSimpleName();

    public interface ImageConvertedListener {
        void onImageConvertedToText(Bitmap bitmap, FirebaseVisionText text);
    }

    public static void getConvertedText(ImageConvertedListener listener,  Bitmap bitmap) {
        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(bitmap);
        FirebaseVisionTextRecognizer textRecognizer = FirebaseVision.getInstance()
                .getOnDeviceTextRecognizer();
        textRecognizer.processImage(image)
                .addOnSuccessListener(text -> listener.onImageConvertedToText(bitmap, text))
                .addOnFailureListener(exception -> Log.d(TAG, "ImageProcessing failed"));

    }


}
