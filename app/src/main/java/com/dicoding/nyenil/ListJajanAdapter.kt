package com.dicoding.nyenil

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ListJajanAdapter(private val listJajan: ArrayList<Jajan>): RecyclerView.Adapter<ListJajanAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvHeadline: TextView = itemView.findViewById(R.id.tv_item_headline)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_jajan, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listJajan.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, headline, photo) = listJajan[position]
        if (photo != null) {
            holder.imgPhoto.setImageResource(photo)
        }
        holder.tvName.text = name
        holder.tvHeadline.text = headline
        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Kamu memilih " + listJajan[holder.adapterPosition].name, Toast.LENGTH_SHORT).show()
        }
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listJajan[holder.adapterPosition]) }
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: Jajan)
    }

}