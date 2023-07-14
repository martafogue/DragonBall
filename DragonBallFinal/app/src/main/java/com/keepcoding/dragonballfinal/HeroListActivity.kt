package com.keepcoding.dragonballfinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.keepcoding.dragonballfinal.databinding.ActivityHeroListBinding


class HeroListActivity : AppCompatActivity(), HeroAdapter.HeroAdapterInterface {

    private lateinit var binding: ActivityHeroListBinding
    private val viewModel : HeroListActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeroListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Ponerle el adapter

        val adapter = HeroAdapter(this)
        binding.rvHeroes.adapter = adapter
        // Decirle como mostrarlos (Vertical, en cuadricula, etc...)
        binding.rvHeroes.layoutManager = LinearLayoutManager(this)
        adapter.ponerListaHeroes(viewModel.descargarListaHerores())

        binding.fabBorrar.setOnClickListener {
            adapter.borrarTodo()
        }
    }

    override fun abrirDetallesHeroeActivity(nombre: String) {
        binding.textView.text = "se ha pulsado sobre $nombre"
    }

}