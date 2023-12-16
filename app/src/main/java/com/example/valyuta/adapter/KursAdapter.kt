package com.example.valyuta.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.valyuta.databinding.ItemKursBinding
import com.example.valyuta.models.MainValyuta

class KursAdapter(var list:ArrayList<MainValyuta>, var click: Click): RecyclerView.Adapter<KursAdapter.Vh>() {
    inner class Vh(var item : ItemKursBinding ): RecyclerView.ViewHolder(item.root){
        fun onBind(mainValyuta: MainValyuta,position: Int){
            item.tv1.text = mainValyuta.title
            item.tv2.text = mainValyuta.cb_price
            item.root.setOnClickListener {
                click.click(position,list)
            }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemKursBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position],position)
    }
    interface Click{
        fun click(position: Int, list: ArrayList<MainValyuta>)
    }
}


