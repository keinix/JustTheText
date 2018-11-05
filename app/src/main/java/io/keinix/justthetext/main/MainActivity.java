package io.keinix.justthetext.main;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.google.firebase.ml.vision.text.FirebaseVisionText;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.keinix.justthetext.R;
import io.keinix.justthetext.data.ConvertedText;
import io.keinix.justthetext.main.domain.usecases.ConvertImageToText;
import io.keinix.justthetext.main.domain.usecases.TakePhoto;
import io.keinix.justthetext.utils.ClipboardUtil;
import io.keinix.justthetext.utils.ShareUtil;

public class MainActivity extends AppCompatActivity implements ConvertImageToText.ImageConvertedListener {

    @BindView(R.id.recycler_view_main) RecyclerView mainRecyclerView;

    @OnClick(R.id.fab)
    void launchCamera() {
        mTaskPhoto.takePhoto();
    }

    private MainViewModel mViewModel;
    private ConvertedTextAdapter mAdapter;
    private TakePhoto mTaskPhoto;


    @Override
    public void onImageConvertedToText(Bitmap bitmap, FirebaseVisionText text) {
        ConvertedText convertedText = mViewModel.createConvertedText(bitmap, text.getText());
        mAdapter.updateAdapter(convertedText);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mTaskPhoto = new TakePhoto(this);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        mAdapter = new ConvertedTextAdapter(this);
        mainRecyclerView.setAdapter(mAdapter);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        List<ConvertedText> convertedTexts = mViewModel.getConvertedTexts();
        if (convertedTexts != null) mAdapter.updateAdapter(convertedTexts);
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share_all:
                ShareUtil.shareAllConvertedTexts(this, mAdapter.getmConvertedTexts());
                break;
            case R.id.action_copy_all:
                ClipboardUtil.copyAllConvertedTexts(this, mAdapter.getmConvertedTexts());
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == TakePhoto.REQUEST_CODE_PHOTO && resultCode == RESULT_OK) {
            Bitmap bitmap = BitmapFactory.decodeFile(mTaskPhoto.getPhotoFile().getAbsolutePath());
            ConvertImageToText.getConvertedText(this, bitmap);
        }
    }
}
