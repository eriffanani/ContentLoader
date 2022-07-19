package com.erif.contentloader.example;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.erif.contentloader.ContentLoaderFrameLayout;
import com.erif.contentloader.R;
import com.erif.contentloader.helper.DataAdapterVertical;
import com.erif.contentloader.helper.DelayTimer;

import java.util.ArrayList;
import java.util.List;

public class ActivityShimmer extends AppCompatActivity {

    private final DataAdapterVertical adapter = new DataAdapterVertical();
    private final List<Integer> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shimmer);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Shimmer Loader");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        Toast.makeText(this, "Loading simulation...", Toast.LENGTH_SHORT).show();

        RecyclerView recyclerView = findViewById(R.id.act_shimmer_recyclerView);
        setupList(recyclerView);
        ContentLoaderFrameLayout loader = findViewById(R.id.act_shimmer_contentLoader);
        loader.startAndHideContent(recyclerView);

        new DelayTimer(3, () -> {
            adapter.setList(list);
            loader.stopAndShowContent(recyclerView, true);
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