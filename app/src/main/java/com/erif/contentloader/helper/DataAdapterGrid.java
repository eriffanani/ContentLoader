package com.erif.contentloader.helper;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.erif.contentloader.R;

import java.util.ArrayList;
import java.util.List;

public class DataAdapterGrid extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

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

        public MyHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
