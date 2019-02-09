package br.com.fiap.trabalhofinalapplication

import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor



class APIClient {

    private val retrofit: Retrofit? = null

    companion object {

        const val API_BASE_URL = "https://mvp-evaluation.herokuapp.com"

        internal val client: Retrofit?
            get() {

                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY

                val okhttp = OkHttpClient.Builder()
                    .addInterceptor(interceptor)
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
