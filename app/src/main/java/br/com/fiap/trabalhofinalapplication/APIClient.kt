package br.com.fiap.trabalhofinalapplication

import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIClient {

    private val retrofit: Retrofit? = null

    companion object {

        const val API_BASE_URL = "https://mvp-evaluation.herokuapp.com"

        internal val client: Retrofit?
            get() {

                val okhttp = OkHttpClient.Builder()
                    .addNetworkInterceptor(StethoInterceptor())
                    .build()

                val retrofit = Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okhttp)
                    .build()

                return retrofit
            }

    }


}
