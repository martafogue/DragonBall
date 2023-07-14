package com.keepcoding.dragonballfinal

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import okhttp3.Credentials
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request

class HeroListActivityViewModel: ViewModel() {



    suspend fun descargarListaHerores(): HeroListState {
        val client = OkHttpClient()
        val url = "${BASE_URL}heros/all"
        val formBody =
            FormBody.Builder()
                .add("name", "")
                .build() // Esto dice que es un POST
        println("Carlos $token")
        val request = Request.Builder()
            .url(url)
            .addHeader("Authorization", "Bearer $token")
            .post(formBody)
            .build()
        val call = client.newCall(request)
        val response = call.execute()
        if (response.isSuccessful)
            response.body?.let {
                val herosDto = Gson().fromJson(it.string(), Array<HeroDto>::class.java)

                return HeroListState.OnSuccess(
                    herosDto.map {
                        Hero(it.name, it.photo)
                    }
                )
            } ?: return HeroListState.OnError("No se ha recibido ningún token")
        else
            return HeroListState.OnError(response.message)
    }

    data class HeroDto(
        val id: String,
        val photo: String,
        var favorite: Boolean,
        val name: String,
        val description: String,
    )

    data class Hero(
        val nombre: String,
        val imageUrl: String,
    )

    sealed class HeroListState {
        data class OnSuccess(val list: List<Hero>) : HeroListState()
        data class OnError(val message: String): HeroListState()
    }
}