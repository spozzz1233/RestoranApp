package com.example.realestateapp.ui.main

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.restoranapp.R
import com.example.restoranapp.domain.model.MenuItem
import com.example.restoranapp.domain.model.item


class OrderAdapter(
    private val clickListener: HomeClick,
) : RecyclerView.Adapter<OrderAdapter.HomeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderAdapter.HomeViewHolder {
        val cardMusicView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return OrderAdapter.HomeViewHolder(cardMusicView)
    }

    override fun onBindViewHolder(holder: OrderAdapter.HomeViewHolder, position: Int) {
        holder.bind(item[position])
        val part = item[position]
        holder.itemView.setOnClickListener {
            clickListener.onClick(part)
        }

    }

    override fun getItemCount(): Int = item.size
    class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val name: TextView = itemView.findViewById(R.id.name)
        private val price: TextView = itemView.findViewById(R.id.price)
        private val image: ImageView = itemView.findViewById(R.id.image)


        fun bind(item: MenuItem) {
            name.text = item.itemName
            price.text = item.price.toString()

            Glide.with(itemView.context)
                .load(item.image)
                .transform(RoundedCorners(8))
                .into(image)
        }
    }

    fun interface HomeClick {
        fun onClick(realEstate: MenuItem)
    }

    fun setItems(newParts: ArrayList<MenuItem>) {
        item = newParts
        notifyDataSetChanged()
    }

}
