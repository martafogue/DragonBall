package com.keepcoding.dragonballfinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.keepcoding.dragonballfinal.databinding.ActivityHeroListBinding

class HeroListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHeroListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeroListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Ponerle el adapter
        binding.rvHeroes.adapter = HeroAdapter()
        // Decirle como mostrarlos (Vertical, en cuadricula, etc...)
        binding.rvHeroes.layoutManager = LinearLayoutManager(this)

    }
}