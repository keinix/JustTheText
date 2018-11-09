package io.keinix.justthetext.main;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

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

    public static final String TAG = MainActivity.class.getSimpleName();
    private MainViewModel mViewModel;
    private ConvertedTextAdapter mAdapter;
    private TakePhoto mTakePhoto;

    @OnClick(R.id.fab)
    void launchCamera() {
        mTakePhoto.takePhoto();
    }

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
        if (savedInstanceState == null) mTakePhoto = new TakePhoto(this);
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
        Log.d(TAG, "ON RESTORE INSTANCE STATE");
        List<ConvertedText> convertedTexts = mViewModel.getConvertedTexts();
        if (convertedTexts != null) mAdapter.updateAdapter(convertedTexts);
            mTakePhoto = new TakePhoto(this);
            mTakePhoto.setPhotoFilePath(mViewModel.getLastPhotoPath());
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
        Log.d(TAG, "ON ACTIVITY RESULT");
        if (requestCode == TakePhoto.REQUEST_CODE_PHOTO && resultCode == RESULT_OK) {
            mViewModel.processPhotoFile(this, mTakePhoto.getPhotoFilePath());
        }
    }

    public void savePhotoPath(String photoPath) {
        mViewModel.setLastPhotoPath(photoPath);
    }
}
