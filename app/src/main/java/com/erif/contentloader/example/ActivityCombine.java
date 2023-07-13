package com.erif.contentloader.example;

import static java.lang.Math.abs;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.erif.contentloader.LoaderContainer;
import com.erif.contentloader.R;
import com.erif.contentloader.helper.adapter.AdapterHorizontal;
import com.erif.contentloader.helper.adapter.AdapterVertical;
import com.erif.contentloader.helper.timer.Delay;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.List;

public class ActivityCombine extends AppCompatActivity {

    private final AdapterHorizontal adapter = new AdapterHorizontal();
    private final AdapterVertical adapterVertical = new AdapterVertical();
    private final List<Integer> list = new ArrayList<>();
    private final List<Integer> listVertical = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combine);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Combined Loader");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        LoaderContainer loader = findViewById(R.id.act_combined_loader);
        RelativeLayout image = findViewById(R.id.act_combined_layoutImage);
        loader.startAndHideContent(image, true);

        RecyclerView recyclerView = findViewById(R.id.act_combined_horizontal_recyclerView);
        setupList(recyclerView);

        LinearLayout horizontalContent = findViewById(R.id.act_combined_horizontal_contents);
        LoaderContainer loaderHorizontal = findViewById(R.id.content_loader_horizontal);
        loaderHorizontal.startAndHideContent(horizontalContent, true);

        new Delay(2, () -> loader.stopAndShowContent(image, true)).start();

        new Delay(2, () -> {
            adapter.setList(list);
            loaderHorizontal.stopAndShowContent(horizontalContent, true);
            recyclerView.scheduleLayoutAnimation();
        }).start();

        int sWidth = getResources().getDisplayMetrics().widthPixels;
        ShapeableImageView imgLeft = findViewById(R.id.act_combined_imgLeft);
        ViewGroup.MarginLayoutParams paramLeft = (ViewGroup.MarginLayoutParams) imgLeft.getLayoutParams();
        float snipped = sWidth * -1;
        float transX = snipped + (paramLeft.getMarginEnd() * 2);
        imgLeft.setTranslationX(transX);

        ShapeableImageView imgRight = findViewById(R.id.act_combined_imgRight);
        imgRight.setTranslationX(abs(transX));

        ShapeableImageView imgCenter = findViewById(R.id.act_combined_imgCenter);
        ViewGroup.MarginLayoutParams paramCenter = (ViewGroup.MarginLayoutParams) imgCenter.getLayoutParams();
        paramCenter.setMarginStart(paramCenter.getMarginStart() * 2);
        paramCenter.setMarginEnd(paramCenter.getMarginEnd() * 2);
        imgCenter.setLayoutParams(paramCenter);

        RecyclerView recyclerViewVertical = findViewById(R.id.act_combined_loader_vertical_recyclerView);
        setupListVertical(recyclerViewVertical);

        LoaderContainer loaderVertical = findViewById(R.id.act_combined_loader_vertical);
        loaderVertical.startAndHideContent(recyclerViewVertical, true);

        new Delay(2.2, () -> {
            adapterVertical.setList(listVertical);
            recyclerViewVertical.scheduleLayoutAnimation();
            new Delay(0.1, () ->
                    loaderVertical.stopAndShowContent(recyclerViewVertical, true)
            ).start();
        }).start();

    }

    private void setupList(RecyclerView recyclerView) {
        recyclerView.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);
        for (int i=0; i<20; i++) {
            list.add(i);
        }
    }

    private void setupListVertical(RecyclerView recyclerView) {
        recyclerView.setAdapter(adapterVertical);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        for (int i=0; i<20; i++) {
            listVertical.add(i);
        }
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