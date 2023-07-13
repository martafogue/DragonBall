package com.keepcoding.dragonballfinal

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import com.keepcoding.dragonballfinal.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val userTag = "USER_TAG"
    private val passTag = "PASS_TAG"

    private val viewModel : MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            cargarLoginDePreferencias()
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
                if (viewModel.isUserValid(etUser.text.toString()) && viewModel.isPassValid(etPass.text.toString())) {
                    Toast.makeText(this@MainActivity, "Login Correcto", Toast.LENGTH_LONG).show()
                    if (switchRememberUser.isChecked) guardarLoginEnPreferencias(etUser.text.toString(), etPass.text.toString())
                } else
                    Toast.makeText(this@MainActivity, "Login Fallido", Toast.LENGTH_LONG).show()
            }
            etUser.setOnFocusChangeListener { view, hasFocus ->
                if (hasFocus)
                    Log.w("MainActivity", "etUser tiene el foco")
                else
                    Log.w("MainActivity", "etUser ha perdido el foco")
            }
            etPass.setOnFocusChangeListener { view, hasFocus ->
                if (hasFocus)
                    Log.w("MainActivity", "etPass tiene el foco")
                else
                    Log.w("MainActivity", "etPass ha perdido el foco")
            }
            switchRememberUser.setOnCheckedChangeListener { _, isChecked ->
                if (!isChecked) borrarPreferencias()
            }
        }
        Toast.makeText(this, "Login Fallido", Toast.LENGTH_LONG).show()
    }

    private fun borrarPreferencias() {
        getPreferences(Context.MODE_PRIVATE).edit().clear().apply()
    }

    private fun guardarLoginEnPreferencias(user: String, pass: String) {
        getPreferences(Context.MODE_PRIVATE).edit()
            .putString(userTag, user)
            .putString(passTag, pass)
            .apply()
    }

    private fun cargarLoginDePreferencias() {
        with(binding) {
            getPreferences(Context.MODE_PRIVATE).apply {
                etUser.setText(getString(userTag, ""))
                etPass.setText(getString(passTag, ""))
                if (etPass.text.isNotEmpty() && etUser.text.isNotEmpty())
                    switchRememberUser.isChecked = true
            }
        }
    }
}