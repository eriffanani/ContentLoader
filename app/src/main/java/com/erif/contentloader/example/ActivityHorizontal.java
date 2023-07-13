package com.erif.contentloader.example;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.erif.contentloader.LoaderContainer;
import com.erif.contentloader.R;
import com.erif.contentloader.helper.adapter.AdapterHorizontal;
import com.erif.contentloader.helper.timer.Delay;

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

        RecyclerView recyclerView = findViewById(R.id.act_horizontal_recyclerView);
        setupList(recyclerView);

        LinearLayout content = findViewById(R.id.act_horizontal_contents);

        LoaderContainer loader = findViewById(R.id.content_loader_horizontal);
        loader.startAndHideContent(content, true);

        new Delay(2.5, () -> {
            recyclerView.scheduleLayoutAnimation();
            adapter.setList(list);
            new Delay(0.1, () -> loader.stopAndShowContent(content, true)).start();
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