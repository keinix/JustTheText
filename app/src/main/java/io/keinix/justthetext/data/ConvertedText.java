package io.keinix.justthetext.data;

import android.graphics.Bitmap;

public class ConvertedText {

    private Bitmap mOrigionalThumbNail;
    private String mConvertedText;

    public Bitmap getmOrigionalThumbNail() {
        return mOrigionalThumbNail;
    }

    public ConvertedText(Bitmap mOrigionalThumbNail, String mConvertedText) {
        this.mOrigionalThumbNail = mOrigionalThumbNail;
        this.mConvertedText = mConvertedText;
    }

    public ConvertedText() {

    }

    public void setmOrigionalThumbNail(Bitmap mOrigionalThumbNail) {
        this.mOrigionalThumbNail = mOrigionalThumbNail;
    }

    public String getmConvertedText() {
        return mConvertedText;
    }

    public void setmConvertedText(String mConvertedText) {
        this.mConvertedText = mConvertedText;
    }
}
