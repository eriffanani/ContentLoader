package com.erif.contentloader.helper;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.erif.contentloader.R;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataAdapterVertical extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Integer> list = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_data_vertical, parent, false);
        return new DataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof DataHolder mHolder) {
            mHolder.image.setImageResource(randomImage());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setList(List<Integer> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    private int randomImage() {
        int[] images = new int[] {
                R.mipmap.man1,
                R.mipmap.man2,
                R.mipmap.man3,
                R.mipmap.women1,
                R.mipmap.women2,
                R.mipmap.women3,
        };
        int randomNumber = new Random().nextInt(images.length);
        return images[randomNumber];
    }

    private static class DataHolder extends RecyclerView.ViewHolder {

        ShapeableImageView image;

        public DataHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.item_vertical_img);
        }
    }

}
