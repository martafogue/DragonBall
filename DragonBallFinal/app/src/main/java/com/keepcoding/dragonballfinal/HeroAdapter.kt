package com.keepcoding.dragonballfinal

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.keepcoding.dragonballfinal.databinding.ItemHeroBinding

class HeroAdapter(val callback: HeroAdapterInterface): RecyclerView.Adapter<HeroAdapter.HeroViewHolder>() {

    interface HeroAdapterInterface {
        fun abrirDetallesHeroeActivity(nombre: String)
    }

    private var list = mutableListOf<String>()

    class HeroViewHolder(val binding: ItemHeroBinding,val callback: HeroAdapterInterface): RecyclerView.ViewHolder(binding.root) {
        fun bind(nombre: String, position: Int) {
            with(binding) {
                tvName.text = nombre
                val bgColorId = if (position % 2 == 0) R.color.dark_orange else R.color.orange
                root.setBackgroundColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        bgColorId
                    )
                )
                root.setOnClickListener {
                    Toast.makeText(root.context, "Se ha pulsado sobre $nombre", Toast.LENGTH_LONG).show()
                    callback.abrirDetallesHeroeActivity(nombre)
                }
            }
        }
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
        return HeroViewHolder(binding, callback)
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        holder.bind(list[position], position)
    }

    fun ponerListaHeroes(listHero : List<String>) {
        list = listHero.toMutableList()
        notifyDataSetChanged()
    }

    fun borrarTodo() {
        list.clear()
        notifyDataSetChanged()
    }
}