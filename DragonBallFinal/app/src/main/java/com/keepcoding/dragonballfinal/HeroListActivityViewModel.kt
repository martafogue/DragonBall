package com.keepcoding.dragonballfinal

import androidx.lifecycle.ViewModel

class HeroListActivityViewModel: ViewModel() {

    fun descargarListaHerores() = List(199) {
        "Goku $it"
    }
}