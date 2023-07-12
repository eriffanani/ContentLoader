package com.erif.contentloader.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.erif.contentloader.ContentLoaderFrameLayout;
import com.erif.contentloader.R;
import com.erif.contentloader.helper.AdapterVertical;
import com.erif.contentloader.helper.DelayTimer;

import java.util.ArrayList;
import java.util.List;

public class ActivityCombine extends AppCompatActivity {

    private final AdapterVertical adapter = new AdapterVertical();
    private final List<Integer> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combine);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Combine Loader");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        Toast.makeText(this, "Loading simulation...", Toast.LENGTH_SHORT).show();

        ContentLoaderFrameLayout loader = findViewById(R.id.act_combine_loader);
        RelativeLayout image = findViewById(R.id.act_combine_layoutImage);
        loader.startAndHideContent(image, true);

        RecyclerView recyclerView = findViewById(R.id.act_combine_recyclerView);
        setupList(recyclerView);

        ContentLoaderFrameLayout loaderVertical = findViewById(R.id.content_combine_vertical);
        loaderVertical.startAndHideContent(recyclerView, true);

        new DelayTimer(2, () -> loader.stopAndShowContent(image, true)).start();

        new DelayTimer(3, () -> {
            adapter.setList(list);
            loaderVertical.stopAndShowContent(recyclerView, true);
        }).start();

    }

    private void setupList(RecyclerView recyclerView) {
        recyclerView.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        for (int i=0; i<20; i++) {
            list.add(i);
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