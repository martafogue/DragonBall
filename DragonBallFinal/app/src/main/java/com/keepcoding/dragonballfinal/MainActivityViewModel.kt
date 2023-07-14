package com.keepcoding.dragonballfinal

import androidx.lifecycle.ViewModel
import kotlin.random.Random

class MainActivityViewModel : ViewModel() {

    var a = Random.nextDouble()

    fun isUserValid(user: String) = user.contains("@") && user.contains(".")

    fun isPassValid(pass: String) = pass.length >= 4


}

