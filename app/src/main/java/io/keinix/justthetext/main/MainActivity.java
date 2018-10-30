package io.keinix.justthetext.main;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.keinix.justthetext.R;
import io.keinix.justthetext.data.ConvertedText;
import io.keinix.justthetext.main.domain.usecases.TakePhoto;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view_main) RecyclerView mainRecyclerView;

    @OnClick(R.id.fab)
    void launchCamera() {
        mTaskPhoto.takePhoto();
    }

    private MainViewModel mViewModel;
    private ConvertedTextAdapter mAdapter;
    private TakePhoto mTaskPhoto;

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
        mAdapter = new ConvertedTextAdapter();
        mainRecyclerView.setAdapter(mAdapter);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == TakePhoto.REQUEST_CODE_PHOTO && resultCode == RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            ConvertedText convertedText = new ConvertedText();
            convertedText.setmOrigionalThumbNail(bitmap);
            mAdapter.updateAdapter(convertedText);
        }
    }
}
