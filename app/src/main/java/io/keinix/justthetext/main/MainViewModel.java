package io.keinix.justthetext.main;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import io.keinix.justthetext.data.ConvertedText;
import io.keinix.justthetext.main.domain.usecases.ConvertImageToText;
import io.keinix.justthetext.main.domain.usecases.RotateBitmap;

public class MainViewModel extends android.arch.lifecycle.ViewModel {

    private List<ConvertedText> mConvertedTexts;
    private String lastPhotoPath;

    ConvertedText createConvertedText(Bitmap bitmap, String text) {
        ConvertedText convertedText = new ConvertedText(bitmap, text);
        if (mConvertedTexts == null) mConvertedTexts = new ArrayList<>();
        mConvertedTexts.add(convertedText);
        return convertedText;
    }

    /**
     * called in onActivityResult() of {@link MainActivity} after a photo is taken.
     * @param listener where the UI will be updated with {@link ConvertedText}
     * @param photoFilePath or image taken by camera
     */
    void processPhotoFile(ConvertImageToText.ImageConvertedListener listener, String photoFilePath) {
        Bitmap bitmap = BitmapFactory.decodeFile(photoFilePath);
        // bitmap need to be up right before ML Kit can extract text
        bitmap = RotateBitmap.rotateBitmapRightSideUp(bitmap, photoFilePath);
        ConvertImageToText.getConvertedText(listener, bitmap);
    }

    @Nullable
    List<ConvertedText> getConvertedTexts() {
        return mConvertedTexts;
    }

    public String getLastPhotoPath() {
        return lastPhotoPath;
    }

    public void setLastPhotoPath(String lastPhotoPath) {
        this.lastPhotoPath = lastPhotoPath;
    }
}
