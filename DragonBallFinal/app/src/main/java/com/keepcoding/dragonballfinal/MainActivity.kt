package com.keepcoding.dragonballfinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import com.keepcoding.dragonballfinal.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            etUser.doAfterTextChanged { editable ->
                editable?.let {
                    btnLogin.isEnabled =
                        it.toString().isNotEmpty() && etPass.text.isNotEmpty()
                }
            }
            etPass.doAfterTextChanged { editable ->
                editable?.let {
                    btnLogin.isEnabled =
                        it.toString().isNotEmpty() && etUser.text.isNotEmpty()
                }
            }
            btnLogin.setOnClickListener {
                if (etUser.text.contains("@") && etUser.text.contains(".") && etPass.text.length >= 4)
                    Toast.makeText(this@MainActivity, "Login Correcto", Toast.LENGTH_LONG).show()
                else
                    Toast.makeText(this@MainActivity, "Login Fallido", Toast.LENGTH_LONG).show()
            }

        }
        Toast.makeText(this, "Login Fallido", Toast.LENGTH_LONG).show()
    }
}