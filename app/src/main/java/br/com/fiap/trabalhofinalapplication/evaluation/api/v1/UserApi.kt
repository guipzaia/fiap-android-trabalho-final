package br.com.fiap.trabalhofinalapplication.evaluation.api.v1

import br.com.fiap.trabalhofinalapplication.evaluation.model.users.v1.UserRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST


interface UserAPI {

    @POST("/v1/users")
    fun create(@Header("Authorization") token: String,
               @Body userRequest: UserRequest
    ): Call<Any>

}