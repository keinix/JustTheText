package io.keinix.justthetext.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.widget.Toast;

public final class ClipboardUtil {

    public static void copyText(Context context, String text) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Copied from JustTheText", text);
        if (clipboard != null) {
            clipboard.setPrimaryClip(clip);
            Toast.makeText(context, "copied", Toast.LENGTH_SHORT).show();
        }
    }
}
