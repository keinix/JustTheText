package io.keinix.justthetext.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.keinix.justthetext.R;
import io.keinix.justthetext.data.ConvertedText;
import io.keinix.justthetext.utils.ClipboardUtil;
import io.keinix.justthetext.utils.ShareUtil;

public class ConvertedTextAdapter extends RecyclerView.Adapter<ConvertedTextAdapter.ConvertedTextViewHolder> {

    private List<ConvertedText> mConvertedTexts;
    private Context mContext;

    ConvertedTextAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ConvertedTextViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_captured_text, viewGroup, false);
        return new ConvertedTextViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConvertedTextViewHolder convertedTextViewHolder, int postion) {
        convertedTextViewHolder.bindView(postion);
    }

    @Override
    public int getItemCount() {
        if (mConvertedTexts != null) {
            return mConvertedTexts.size();
        }
        return 0;
    }

    void updateAdapter(List<ConvertedText> convertedTexts) {
        if (mConvertedTexts == null) mConvertedTexts = new ArrayList<>();
        mConvertedTexts.addAll(convertedTexts);
        notifyDataSetChanged();
    }

    void updateAdapter(ConvertedText convertedText) {
        if (mConvertedTexts == null) mConvertedTexts = new ArrayList<>();
        mConvertedTexts.add(convertedText);
        notifyDataSetChanged();
    }

    List<ConvertedText> getmConvertedTexts() {
        return mConvertedTexts;
    }

    private void showUndoSnackbar(ConvertedText lastDeletedConvertedText, int position) {
        View view = ((MainActivity) mContext).findViewById(R.id.coordinator_layout_main);
        Snackbar snackbar = Snackbar.make(view, "Deleted", Snackbar.LENGTH_LONG);
        snackbar.setAction("UNDO", v -> undoDelete(lastDeletedConvertedText, position));
        snackbar.show();
    }

    private void deleteItem(ConvertedText convertedText) {
        for (int i = 0; i < mConvertedTexts.size(); i++) {
            ConvertedText item = mConvertedTexts.get(i);
            if (convertedText.equals(item)) {
                mConvertedTexts.remove(i);
                notifyItemRemoved(i);
                showUndoSnackbar(convertedText, i);
                break;
            }
        }
    }

    private void undoDelete(ConvertedText lastDeletedConvertedText, int position) {
        mConvertedTexts.add(position, lastDeletedConvertedText);
        notifyItemInserted(position);
    }


    class ConvertedTextViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_view_original_image) ImageView thumbNailImageView;
        @BindView(R.id.text_view_converted_text) TextView convertedTextTextView;

        private ConvertedText convertedText;

        @OnClick(R.id.image_button_share)
        void shareText() {
            ShareUtil.shareText(mContext, convertedText.getmConvertedText());
        }

        @OnClick(R.id.image_button_copy)
        void copyText() {
            ClipboardUtil.copyText(mContext, convertedText.getmConvertedText());
        }

        @OnClick(R.id.image_button_clear_item)
        void clearItem() {
            deleteItem(convertedText);
        }

        ConvertedTextViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int position) {
            convertedText = mConvertedTexts.get(position);
            thumbNailImageView.setImageBitmap(convertedText.getmOrigionalThumbNail());
            convertedTextTextView.setText(convertedText.getmConvertedText());

        }
    }
}
