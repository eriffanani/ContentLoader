package com.erif.contentloader.example;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.erif.contentloader.LoaderContainer;
import com.erif.contentloader.R;
import com.erif.contentloader.helper.adapter.AdapterGrid;
import com.erif.contentloader.helper.timer.Delay;

import java.util.ArrayList;
import java.util.List;

public class ActivityGrid extends AppCompatActivity {

    private final AdapterGrid adapter = new AdapterGrid();
    private final List<Integer> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Grid Loader");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        RecyclerView recyclerView = findViewById(R.id.act_grid_recyclerView);
        setupList(recyclerView);

        LoaderContainer loader = findViewById(R.id.content_loader_grid);
        loader.post(() -> loader.startAndHideContent(recyclerView, true));

        new Delay(2.5, () -> {
            recyclerView.scheduleLayoutAnimation();
            adapter.setList(list);
            new Delay(0.1, () -> {
                loader.stopAndShowContent(recyclerView, true);
            }).start();
        }).start();

    }

    private void setupList(RecyclerView recyclerView) {
        recyclerView.setAdapter(adapter);
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(manager);
        for (int i=0; i<30; i++) {
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