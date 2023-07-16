package com.keepcoding.dragonballfinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.keepcoding.dragonballfinal.databinding.ActivityHeroListBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HeroListActivity : AppCompatActivity(), HeroAdapter.HeroAdapterInterface {

    private lateinit var binding: ActivityHeroListBinding
    private val viewModel : HeroListActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeroListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Ponerle el adapter
        //accedemos al recicled view y le ponemos el adapter que hemos creado (la clase HeroAdapter)
        val adapter = HeroAdapter(this)
        binding.rvHeroes.adapter = adapter
        // Decirle como mostrarlos (Vertical, en cuadricula, etc...)
        binding.rvHeroes.layoutManager = LinearLayoutManager(this)
        //le asignamos la lista de elementos para rellenar el recycled view
        lifecycleScope.launch(Dispatchers.IO) {
            val state = viewModel.descargarListaHerores()
            when (state) {
                is HeroListActivityViewModel.HeroListState.OnSuccess -> {
                    withContext(Dispatchers.Main) {
                        adapter.ponerListaHeroes(state.list) }
                }
                is HeroListActivityViewModel.HeroListState.OnError ->
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@HeroListActivity, state.message, Toast.LENGTH_LONG).show()
                    }
            }
        }


        binding.fabBorrar.setOnClickListener {
            adapter.borrarTodo()
        }
    }

    override fun abrirDetallesHeroeActivity(nombre: String) {
        binding.textView.text = "se ha pulsado sobre $nombre"
    }

}