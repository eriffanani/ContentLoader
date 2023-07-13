package com.erif.contentloader.helper.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.erif.contentloader.R;
import com.erif.contentloader.helper.ImageRes;

import java.util.ArrayList;
import java.util.List;

public class AdapterGrid extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Integer> list = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new MyHolder(
                inflater.inflate(R.layout.item_data_grid, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyHolder mHolder) {
            int image = ImageRes.getAll();
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

    private static class MyHolder extends RecyclerView.ViewHolder {

        ImageView img;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.item_grid_img);
        }
    }

}
