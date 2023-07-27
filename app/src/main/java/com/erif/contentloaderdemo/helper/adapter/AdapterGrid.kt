package com.erif.contentloaderdemo.helper.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.erif.contentloaderdemo.R
import com.erif.contentloaderdemo.helper.ImageRes

class AdapterGrid: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list: MutableList<Int> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_data_grid, parent, false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: MutableList<Int>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MyHolder) {
            val image: Int = ImageRes.getAll()
            holder.img.setImageResource(image)
        }
    }

    private class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img: ImageView
        init {
            img = itemView.findViewById(R.id.item_grid_img)
        }
    }

}