package com.erif.contentloader.example;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.erif.contentloader.ContentLoaderFrameLayout;
import com.erif.contentloader.R;
import com.erif.contentloader.helper.AdapterHorizontal;
import com.erif.contentloader.helper.DelayTimer;

import java.util.ArrayList;
import java.util.List;

public class ActivityHorizontal extends AppCompatActivity {

    private final AdapterHorizontal adapter = new AdapterHorizontal();
    private final List<Integer> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Horizontal Loader");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        Toast.makeText(this, "Loading simulation...", Toast.LENGTH_SHORT).show();

        RecyclerView recyclerView = findViewById(R.id.act_horizontal_recyclerView);
        setupList(recyclerView);

        LinearLayout content = findViewById(R.id.act_horizontal_contents);

        ContentLoaderFrameLayout loader = findViewById(R.id.content_loader_horizontal);
        loader.startAndHideContent(content, true);

        new DelayTimer(3, () -> {
            adapter.setList(list);
            loader.stopAndShowContent(content, true);
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}