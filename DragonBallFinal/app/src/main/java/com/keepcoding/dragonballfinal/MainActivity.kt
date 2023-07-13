package com.keepcoding.dragonballfinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import com.keepcoding.dragonballfinal.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

     private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etUser.doAfterTextChanged { editable ->
            editable?.let {
                binding.btnLogin.isEnabled = it.toString().isNotEmpty() && binding.etPass.text.isNotEmpty()
            }
        }
        binding.etPass.doAfterTextChanged { editable ->
            editable?.let {
                binding.btnLogin.isEnabled = it.toString().isNotEmpty() && binding.etUser.text.isNotEmpty()
            }
        }
        binding.btnLogin.setOnClickListener {
            // TODO muestra toast de "ok" si, el usuario tiene una @ y un "." y la contraseña tiene más de 4 chars
        }
    }
}