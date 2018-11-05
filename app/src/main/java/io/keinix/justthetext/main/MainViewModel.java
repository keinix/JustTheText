package io.keinix.justthetext.main;


import android.graphics.Bitmap;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import io.keinix.justthetext.data.ConvertedText;

public class MainViewModel extends android.arch.lifecycle.ViewModel {

    private List<ConvertedText> mConvertedTexts;


    public ConvertedText createConvertedText(Bitmap bitmap, String text) {
        ConvertedText convertedText = new ConvertedText(bitmap, text);
        if (mConvertedTexts == null) mConvertedTexts = new ArrayList<>();
        mConvertedTexts.add(convertedText);
        return convertedText;
    }

    @Nullable
    public List<ConvertedText> getConvertedTexts() {
        return mConvertedTexts;
    }
}
