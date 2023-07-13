package com.erif.contentloader.example;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.erif.contentloader.LoaderContainer;
import com.erif.contentloader.R;
import com.erif.contentloader.helper.adapter.AdapterVertical;
import com.erif.contentloader.helper.timer.Delay;

import java.util.ArrayList;
import java.util.List;

public class ActivityVertical extends AppCompatActivity {

    private final AdapterVertical adapter = new AdapterVertical();
    private final List<Integer> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Vertical Loader");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        RecyclerView recyclerView = findViewById(R.id.act_vertical_recyclerView);
        setupList(recyclerView);

        LoaderContainer loader = findViewById(R.id.content_loader_vertical);
        loader.startAndHideContent(recyclerView, true);

        new Delay(2.5, () -> {
            adapter.setList(list);
            recyclerView.scheduleLayoutAnimation();
            new Delay(0.1, () ->
                    loader.stopAndShowContent(recyclerView, true)
            ).start();
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