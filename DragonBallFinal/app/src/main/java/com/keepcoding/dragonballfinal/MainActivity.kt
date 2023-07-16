package com.keepcoding.dragonballfinal

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import com.keepcoding.dragonballfinal.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    //binding: xa acceder a los elementos del layout sin tener que referenciarlos con:
    // var t = findViewById<TextView>(R.id.tvHellow)
    //var q va a tener valor pero q aún no se lo puedo dar
    private lateinit var binding : ActivityMainBinding
    private val userTag = "USER_TAG"
    private val passTag = "PASS_TAG"
    //creamos variable de tipo viewModel
    private val viewModel : MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //con with entramos en el binding, así q ya no hace falta llamarle to_do el rato
        //pero dentro del binding el this ya no es la activity sino el binding
        with(binding) {
            //aunque llamemos a otra activity sigue ejecutándose el código de esta
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
                    lanzarLogin(etUser.text.toString(), etPass.text.toString())
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

    private fun lanzarLogin(user: String, pass: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            val state = viewModel.loguear(user, pass)
            when(state) {
                is MainActivityViewModel.LoginState.OnSuccess -> abrirHeroListActivity()
                is MainActivityViewModel.LoginState.OnError -> {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@MainActivity, state.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun abrirHeroListActivity() {
        //creamos un intent para cambiar de activity
        //al intent le pasamos a qué clase queremos ir y desde dónde
        val intent = Intent(this, HeroListActivity::class.java)
        startActivity(intent)
        //xa terminar la actividad actual.
        //Así si dan al botón volver no vuelve a abrir esta pantalla (login)
        finish()
    }
}