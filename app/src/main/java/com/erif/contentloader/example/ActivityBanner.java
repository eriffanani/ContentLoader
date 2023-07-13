package com.erif.contentloader.example;

import static java.lang.Math.abs;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.erif.contentloader.LoaderContainer;
import com.erif.contentloader.R;
import com.erif.contentloader.helper.timer.Delay;
import com.google.android.material.imageview.ShapeableImageView;

public class ActivityBanner extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Banner / Slider Loader");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        LoaderContainer loader = findViewById(R.id.act_frame_loader);
        RelativeLayout image = findViewById(R.id.act_frame_layoutImage);
        loader.startAndHideContent(image, true);

        new Delay(2.5, () -> loader.stopAndShowContent(image, true)).start();

        int sWidth = getResources().getDisplayMetrics().widthPixels;
        ShapeableImageView imgLeft = findViewById(R.id.act_frame_imgLeft);
        ViewGroup.MarginLayoutParams paramLeft = (ViewGroup.MarginLayoutParams) imgLeft.getLayoutParams();
        float snipped = sWidth * -1;
        float transX = snipped + (paramLeft.getMarginEnd() * 2);
        imgLeft.setTranslationX(transX);

        ShapeableImageView imgRight = findViewById(R.id.act_frame_imgRight);
        imgRight.setTranslationX(abs(transX));

        ShapeableImageView imgCenter = findViewById(R.id.act_frame_imgCenter);
        ViewGroup.MarginLayoutParams paramCenter = (ViewGroup.MarginLayoutParams) imgCenter.getLayoutParams();
        paramCenter.setMarginStart(paramCenter.getMarginStart() * 2);
        paramCenter.setMarginEnd(paramCenter.getMarginEnd() * 2);
        imgCenter.setLayoutParams(paramCenter);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}