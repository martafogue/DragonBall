package com.keepcoding.dragonballfinal

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.keepcoding.dragonballfinal.databinding.ItemHeroBinding

class HeroAdapter: RecyclerView.Adapter<HeroAdapter.HeroViewHolder>() {

    class HeroViewHolder(val binding: ItemHeroBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(nombre: String, position: Int) {
            binding.tvName.text = nombre
            val bgColorId = if (position % 2 == 0) R.color.dark_orange else R.color.orange
            binding.root.setBackgroundColor(ContextCompat.getColor(binding.root.context, bgColorId))

        }
    }

    var list = List(100) {
        "Goku $it"
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        val binding = ItemHeroBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HeroViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        holder.bind(list[position], position)
    }
}