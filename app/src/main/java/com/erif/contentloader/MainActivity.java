package com.erif.contentloader;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onClick(R.id.btnVertical, ActivityVertical.class);
        onClick(R.id.btnHorizontal, ActivityHorizontal.class);
        onClick(R.id.btnFrame, ActivityFrame.class);

    }

    private void onClick(int id, Class<?> destination) {
        getButton(id).setOnClickListener(view -> intent(destination));
    }

    private void intent(Class<?> destination) {
        Intent intent = new Intent(this, destination);
        startActivity(intent);
    }

    private Button getButton(int id) {
        return (Button) findViewById(id);
    }

}