package io.keinix.justthetext.utils;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.List;

import io.keinix.justthetext.data.ConvertedText;

public final class ShareUtil {

    public static void shareText(Context context, String text){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, text);
        sendIntent.setType("text/plain");
        if (sendIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(sendIntent);
        } else {
            Toast.makeText(context, "No installed apps can handle text :(", Toast.LENGTH_SHORT).show();
        }
    }

    public static void shareAllConvertedTexts(Context context, List<ConvertedText> convertedTexts) {
        StringBuilder builder = new StringBuilder();
        for (ConvertedText  convertedText: convertedTexts) {
            String text = convertedText.getmConvertedText() + " ";
            builder.append(text);
        }
        shareText(context, builder.toString());
    }
}
