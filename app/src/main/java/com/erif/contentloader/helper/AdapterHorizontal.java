package com.erif.contentloader.helper;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.erif.contentloader.R;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.List;

public class AdapterHorizontal extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Integer> list = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_data_horizontal, parent, false);
        return new DataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof DataHolder mHolder) {
            int image = ImageResource.get();
            mHolder.img.setImageResource(image);
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

    private static class DataHolder extends RecyclerView.ViewHolder {

        ShapeableImageView img;

        public DataHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.item_horizontal_img);
        }
    }

}
