package io.keinix.justthetext.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.keinix.justthetext.R;
import io.keinix.justthetext.data.ConvertedText;

public class ConvertedTextAdapter extends RecyclerView.Adapter<ConvertedTextAdapter.ConvertedTextViewHolder> {

    private List<ConvertedText> mConvertedTexts;

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


    class ConvertedTextViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_view_original_image) ImageView thumbNailImageView;

        ConvertedTextViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int position) {
            thumbNailImageView.setImageBitmap(mConvertedTexts.get(position).getmOrigionalThumbNail());
        }
    }
}
