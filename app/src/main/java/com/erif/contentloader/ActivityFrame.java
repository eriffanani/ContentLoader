package com.erif.contentloader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.erif.contentloader.helper.DelayTimer;

public class ActivityFrame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Frame Loader");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        Toast.makeText(this, "Loading simulation...", Toast.LENGTH_SHORT).show();


        ContentLoader loader = findViewById(R.id.act_frame_loader);
        ImageView image = findViewById(R.id.act_frame_image);
        loader.startAndHideContent(image, true);

        new DelayTimer(2, () -> loader.stopAndShowContent(image, true)).start();

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