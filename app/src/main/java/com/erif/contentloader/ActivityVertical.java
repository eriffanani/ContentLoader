package com.erif.contentloader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.erif.contentloader.helper.DataAdapterVertical;
import com.erif.contentloader.helper.DelayTimer;

import java.util.ArrayList;
import java.util.List;

public class ActivityVertical extends AppCompatActivity {

    private final DataAdapterVertical adapter = new DataAdapterVertical();
    private final List<Integer> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Vertical Loader");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        Toast.makeText(this, "Loading simulation...", Toast.LENGTH_SHORT).show();

        RecyclerView recyclerView = findViewById(R.id.act_vertical_recyclerView);
        setupList(recyclerView);

        ContentLoaderVertical loader = findViewById(R.id.content_loader_vertical);
        loader.startAndHideContent(recyclerView);

        new DelayTimer(3, () -> {
            adapter.setList(list);
            loader.stopAndShowContent(recyclerView);
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