package com.erif.contentloader.example;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.erif.contentloader.LoaderContainer;
import com.erif.contentloader.R;
import com.erif.contentloader.helper.adapter.AdapterVertical;
import com.erif.contentloader.helper.timer.Delay;
import com.google.android.material.progressindicator.CircularProgressIndicator;

import java.util.ArrayList;
import java.util.List;

public class ActivityShimmer extends AppCompatActivity {

    private final AdapterVertical adapter = new AdapterVertical();
    private final List<Integer> list = new ArrayList<>();

    private CircularProgressIndicator progressIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shimmer);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Shimmer Loader");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        RecyclerView recyclerView = findViewById(R.id.act_shimmer_recyclerView);
        setupList(recyclerView);
        LoaderContainer loader = findViewById(R.id.act_shimmer_contentLoader);
        loader.startAndHideContent(recyclerView);

        new Delay(2.5, () -> {
            adapter.setList(list);
            recyclerView.scheduleLayoutAnimation();
            new Delay(0.1, () -> {
                loader.stopAndShowContent(recyclerView, true);
            }).start();
            new Delay(.5, () -> {
                if (progressIndicator != null)
                    progressIndicator.setIndeterminate(false);
            }).start();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_loading, menu);
        final MenuItem menuItem = menu.findItem(R.id.menu_loading);

        View actionView = menuItem.getActionView();
        progressIndicator = actionView.findViewById(R.id.menu_loading_progress);
        return true;
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