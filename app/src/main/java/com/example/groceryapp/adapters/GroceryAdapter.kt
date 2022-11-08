package com.example.groceryapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.groceryapp.R
import com.example.groceryapp.model.Record

class GroceryAdapter :
    PagingDataAdapter<Record,GroceryAdapter.GroceryViewHolder>(COMPARATOR) {
    class GroceryViewHolder(itemView: View) : ViewHolder(itemView) {
        val marketTv: TextView = itemView.findViewById(R.id.market_tv)
        val groceryNameTv: TextView = itemView.findViewById(R.id.grocery_name_tv)
        val addressTv: TextView = itemView.findViewById(R.id.address_tv)
        val priceTv: TextView = itemView.findViewById(R.id.price_tv)
        val dateTv: TextView = itemView.findViewById(R.id.date_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
        return GroceryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_grocery, parent, false)
        )
    }

    override fun onBindViewHolder(holder: GroceryViewHolder, position: Int) {
        getItem(position)?.let {
           holder.dateTv.text = it.arrivalDate
           holder.marketTv.text = it.market
           holder.groceryNameTv.text = "${it.commodity} (${it.variety})"
           holder.addressTv.text = "${it.district}, ${it.state}"
           holder.priceTv.text ="Price :₹${it.modalPrice}"
       }
    }

    companion object{
        val COMPARATOR = object: DiffUtil.ItemCallback<Record>() {
            override fun areItemsTheSame(oldItem: Record, newItem: Record): Boolean {
                return oldItem.commodity==newItem.commodity && oldItem.variety == newItem.variety && oldItem.market==newItem.market
            }

            override fun areContentsTheSame(oldItem: Record, newItem: Record): Boolean {
                return oldItem==newItem
            }

        }
    }
}