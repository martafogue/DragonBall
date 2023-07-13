package com.keepcoding.ciclodevida

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private var numUsos = 0
    private val numUsosTag = "NUM_USOS"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("onCreate")

        /*
        val preferences = getPreferences(Context.MODE_PRIVATE)
        numUsos = preferences.getInt(numUsosTag, 0)
        numUsos++ */

        numUsos = getPreferences(Context.MODE_PRIVATE).getInt(numUsosTag, 0)
        numUsos++

        val toastText = getString(R.string.toast_execution_number, numUsos)
        Toast.makeText(this, toastText, Toast.LENGTH_LONG).show()
        println("onCreate - $numUsos")

        // Esto no hace nada, es un simple ejemplo de como acceder a estos recursos
        resources.getDimension(R.dimen.text_small)
        ContextCompat.getColor(this, R.color.black)
    }

    override fun onStart() {
        super.onStart()
        println("onStart")
    }

    override fun onResume() {
        super.onResume()
        println("onResume")
    }


    override fun onPause() {
        super.onPause()
        println("onPause")

    }

    override fun onStop() {
        super.onStop()
        println("onStop")

        /*
        val preferencias = getPreferences(Context.MODE_PRIVATE)
        val preferenciasEditables = preferencias.edit()
        preferenciasEditables.putInt("NUM_USOS", numUsos)
        preferenciasEditables.apply()
        */
        getPreferences(Context.MODE_PRIVATE).edit().apply {
            putInt(numUsosTag, numUsos)
            apply()
        }

    }

    override fun onRestart() {
        super.onRestart()
        println("onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy")
    }
}