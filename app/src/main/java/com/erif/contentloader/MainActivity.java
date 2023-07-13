package com.erif.contentloader;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.erif.contentloader.example.ActivityCombine;
import com.erif.contentloader.example.ActivityBanner;
import com.erif.contentloader.example.ActivityGrid;
import com.erif.contentloader.example.ActivityHorizontal;
import com.erif.contentloader.example.ActivityShimmer;
import com.erif.contentloader.example.ActivityVertical;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Content Loader Animation");
            getSupportActionBar().setSubtitle("https://github.com/eriffanani/ContentLoader");
        }

        onClick(R.id.btnVertical, ActivityVertical.class);
        onClick(R.id.btnShimmer, ActivityShimmer.class);
        onClick(R.id.btnHorizontal, ActivityHorizontal.class);
        onClick(R.id.btnGrid, ActivityGrid.class);
        onClick(R.id.btnBanner, ActivityBanner.class);
        onClick(R.id.btnCombine, ActivityCombine.class);

    }

    private void onClick(int id, Class<?> destination) {
        getButton(id).setOnClickListener(view -> intent(destination));
    }

    private void intent(Class<?> destination) {
        Intent intent = new Intent(this, destination);
        startActivity(intent);
    }

    private Button getButton(int id) {
        return findViewById(id);
    }

}