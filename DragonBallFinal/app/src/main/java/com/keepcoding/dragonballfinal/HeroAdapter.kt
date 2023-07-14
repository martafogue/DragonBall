package com.keepcoding.dragonballfinal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.keepcoding.dragonballfinal.databinding.ItemHeroBinding

class HeroAdapter: RecyclerView.Adapter<HeroAdapter.HeroViewHolder>() {

    class HeroViewHolder(val binding: ItemHeroBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(nombre: String) {
            binding.tvName.text = nombre
        }
    }

    var list = listOf("Goku", "Vegeta")

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
        holder.bind(list[position])
    }
}