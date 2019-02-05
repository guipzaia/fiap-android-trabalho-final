package br.com.fiap.trabalhofinalapplication.evaluation.api.v1

import br.com.fiap.trabalhofinalapplication.evaluation.contracts.oauth.v1.LoginRequest
import br.com.fiap.trabalhofinalapplication.evaluation.contracts.oauth.v1.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface OAuthApi {

    @POST("/v1/oauth/login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST("/v1/oauth/validate")
    fun validate(@Header("Authorization") token: String): Call<Any>

}