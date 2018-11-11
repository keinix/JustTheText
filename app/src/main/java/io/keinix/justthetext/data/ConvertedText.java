package io.keinix.justthetext.data;

import android.graphics.Bitmap;

import java.io.File;
import java.util.Objects;

public class ConvertedText {

    private Bitmap mOrigionalThumbNail;
    private String mConvertedText;

    public ConvertedText(Bitmap mOrigionalThumbNail, String mConvertedText) {
        this.mOrigionalThumbNail = mOrigionalThumbNail;
        this.mConvertedText = mConvertedText;
    }

    public Bitmap getmOrigionalThumbNail() {
        return mOrigionalThumbNail;
    }

    public String getmConvertedText() {
        return mConvertedText;
    }

    public void setConvertedText(String convertedText) {
        mConvertedText = convertedText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConvertedText)) return false;
        ConvertedText that = (ConvertedText) o;
        return Objects.equals(mOrigionalThumbNail, that.mOrigionalThumbNail) &&
                Objects.equals(mConvertedText, that.mConvertedText);
    }

    @Override
    public int hashCode() {

        return Objects.hash(mOrigionalThumbNail, mConvertedText);
    }
}
