package com.zhubibo.baby.module.gallery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhubibo.baby.R;


/**
 * Created by kevin on 2017/7/5.
 */

public class DetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView detailIv;
    private TextView detailTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageEntity imageEntity = (ImageEntity) getIntent().getSerializableExtra("imageEntity");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        detailIv = (ImageView) findViewById(R.id.detailIv);
        detailTv = (TextView) findViewById(R.id.detailTv);

        if (imageEntity != null) {
            detailIv.setImageResource(imageEntity.imageId);
            detailTv.setText(imageEntity.content);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return true;
    }
}
