package io.keinix.justthetext.main.domain.usecases;

import android.app.AlertDialog;
import android.content.Context;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import io.keinix.justthetext.data.ConvertedText;

public class EditConvertedText {

    private Context mContext;
    private EditText mEditText;
    private TextView mCapturedTextItemTextView;
    private ConvertedText mConvertedText;


    public EditConvertedText(Context context, TextView textView, ConvertedText convertedText) {
        mContext = context;
        mCapturedTextItemTextView = textView;
        mConvertedText = convertedText;
    }

    public void showEditDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        mEditText = new EditText(mContext);
        mEditText.setText(mConvertedText.getmConvertedText());
        AlertDialog dialog = builder.setView(mEditText)
                .setPositiveButton("OK", (dialogInterface, i) -> changesSaved())
                .setNegativeButton("CANCEL", (dialogInterface, i) -> {})
                .create();
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.show();
        mEditText.requestFocus();
    }


    private void changesSaved() {
        mCapturedTextItemTextView.setText(mEditText.getText());
        mConvertedText.setConvertedText(mEditText.getText().toString());
    }
}
