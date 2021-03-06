package com.erif.contentloader.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.erif.contentloader.ContentLoaderFrameLayout;
import com.erif.contentloader.R;
import com.erif.contentloader.helper.DataAdapterGrid;
import com.erif.contentloader.helper.DataAdapterVertical;
import com.erif.contentloader.helper.DelayTimer;

import java.util.ArrayList;
import java.util.List;

public class ActivityGrid extends AppCompatActivity {

    private final DataAdapterGrid adapter = new DataAdapterGrid();
    private final List<Integer> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Grid Loader");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        Toast.makeText(this, "Loading simulation...", Toast.LENGTH_SHORT).show();

        RecyclerView recyclerView = findViewById(R.id.act_grid_recyclerView);
        setupList(recyclerView);

        ContentLoaderFrameLayout loader = findViewById(R.id.content_loader_grid);
        loader.startAndHideContent(recyclerView, true);

        new DelayTimer(3, () -> {
            adapter.setList(list);
            loader.stopAndShowContent(recyclerView, true);
        }).start();

    }

    private void setupList(RecyclerView recyclerView) {
        recyclerView.setAdapter(adapter);
        GridLayoutManager manager = new GridLayoutManager(this, 2);
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