package io.keinix.justthetext.data;

import android.graphics.Bitmap;

import java.io.File;

public class ConvertedText {

    private Bitmap mOrigionalThumbNail;
    private String mConvertedText;
    private File mImageFile;

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

    public File getImageFile() {
        return mImageFile;
    }

    public void setImageFile(File imageFile) {
        mImageFile = imageFile;
    }
}
