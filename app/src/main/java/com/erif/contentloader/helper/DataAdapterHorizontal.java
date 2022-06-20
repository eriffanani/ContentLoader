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

public class DataAdapterHorizontal extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Integer> list = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_data_horizontal, parent, false);
        return new DataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {}

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

        public DataHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
