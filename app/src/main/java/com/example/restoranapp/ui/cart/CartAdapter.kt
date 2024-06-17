package com.example.autorus.ui.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.restoranapp.R
import com.example.restoranapp.domain.model.MenuItem
import com.example.restoranapp.domain.model.cart
import com.example.restoranapp.domain.model.item


class CartAdapter(
    private val clickListener: HistoryClick
) : RecyclerView.Adapter<CartAdapter.HistoryViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val cardMusicView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_mini, parent, false)
        return HistoryViewHolder(cardMusicView)
    }

    override fun getItemCount(): Int = cart.size

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val historyCart = cart[position]
        holder.bind(historyCart)
        holder.itemView.setOnClickListener {
            clickListener.onClick(historyCart)
        }
    }

    class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val Name: TextView = itemView.findViewById(R.id.Name)
        private val price: TextView = itemView.findViewById(R.id.price)
        private val image: ImageView = itemView.findViewById(R.id.image)

        fun bind(item: MenuItem) {
            Name.text = item.itemName
            price.text = item.price.toString()

            Glide.with(itemView.context)
                .load(item.image)
                .transform(RoundedCorners(8))
                .into(image)
        }
    }

    fun interface HistoryClick {
        fun onClick(part: MenuItem)
    }
    fun updateData() {
        notifyDataSetChanged()
    }
}
