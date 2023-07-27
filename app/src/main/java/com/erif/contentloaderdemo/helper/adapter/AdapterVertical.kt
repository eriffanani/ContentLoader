package com.erif.contentloaderdemo.helper.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.erif.contentloaderdemo.R
import com.erif.contentloaderdemo.helper.ImageRes
import com.google.android.material.imageview.ShapeableImageView

class AdapterVertical: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list: MutableList<Int> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DataHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_data_vertical, parent, false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is DataHolder) {
            val image: Int = ImageRes.people()
            holder.image.setImageResource(image)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: MutableList<Int>) {
        this.list = list
        notifyDataSetChanged()
    }

    private class DataHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ShapeableImageView
        init {
            image = itemView.findViewById(R.id.item_vertical_img)
        }
    }

}