package com.erif.contentloaderdemo.helper.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.erif.contentloaderdemo.R
import com.erif.contentloaderdemo.helper.ImageRes
import com.google.android.material.imageview.ShapeableImageView

class AdapterHorizontal: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list: MutableList<Int> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DataHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_data_horizontal, parent, false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is DataHolder) {
            val image: Int = ImageRes.clothes()
            holder.img.setImageResource(image)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: MutableList<Int>) {
        this.list = list
        notifyDataSetChanged()
    }

    private class DataHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img: ShapeableImageView
        init {
            img = itemView.findViewById(R.id.item_horizontal_img)
        }
    }

}