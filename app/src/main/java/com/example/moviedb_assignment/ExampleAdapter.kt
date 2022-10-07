package com.example.moviedb_assignment

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExampleAdapter(
    private val exampleList: List<ExampleItem>,
    private val listener: OnItemClickListener
    ) : RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.example_item,
        parent, false)

        return ExampleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = exampleList[position]

        holder.imageView.setImageResource(currentItem.imageReSource)
        holder.textView1.text = currentItem.text1
        holder.textView2.text = currentItem.text2


        // Don't do this. It will re-load the view everytime it is called, rather than once (once: from the ExampleViewHolder class)
        //holder.itemView.text_view_1.text = currentItem.text1
    }

    override fun getItemCount() = exampleList.size

    // ViewHolder represents a single row in the list
    inner class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
    OnClickListener{

        // Cache the ids and hold them, rather than loading them everytime you see a id of a ImageView
        val imageView: ImageView = itemView.findViewById(R.id.image_view)
        val textView1: TextView = itemView.findViewById(R.id.text_view_1)
        val textView2: TextView = itemView.findViewById(R.id.text_view_2)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position : Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)

    }
}