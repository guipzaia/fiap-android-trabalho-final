package br.com.fiap.trabalhofinalapplication.evaluation.api.v1

import br.com.fiap.trabalhofinalapplication.evaluation.contracts.Customer
import br.com.fiap.trabalhofinalapplication.evaluation.contracts.customers.v1.CustomersReponse
import retrofit2.Call
import retrofit2.http.*

interface CustomerApi {

    @POST("/v1/customers")
    fun create(@Header("Authorization") token: String, @Body customer: Customer): Call<Customer>

    @POST("/v1/customers")
    fun update(@Header("Authorization") token: String, @Body customer: Customer): Call<Customer>

    @GET("/v1/customers/{uuid}")
    fun list(@Header("Authorization") token: String, @Path("uuid") uuid: String): Call<CustomersReponse>

}