package com.keepcoding.dragonballfinal

import androidx.lifecycle.ViewModel
import okhttp3.Credentials
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request

const val BASE_URL = "https://dragonball.keepcoding.education/api/"

var token = ""

class MainActivityViewModel : ViewModel() {


    fun isUserValid(user: String) = user.contains("@") && user.contains(".")

    fun isPassValid(pass: String) = pass.length >= 4


    suspend fun loguear(user: String, pass: String): LoginState {
        val client = OkHttpClient()
        val url = "${BASE_URL}auth/login"
        val credentials = Credentials.basic("carlos.bellmont1@pruebmail.es", "123456")
        val formBody = FormBody.Builder().build() // Esto dice que es un POST
        val request = Request.Builder()
            .url(url)
            .addHeader("Authorization", credentials)
            .post(formBody)
            .build()
        val call = client.newCall(request)
        val response = call.execute()
        if (response.isSuccessful)
            response.body?.let {
                token = it.string()
                return LoginState.OnSuccess
            } ?: return LoginState.OnError("No se ha recibido ningún token")
        else
            return LoginState.OnError(response.message)
    }

    sealed class LoginState {
        object OnSuccess : LoginState()
        data class OnError(val message: String): LoginState()
    }

}

