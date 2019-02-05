package br.com.fiap.trabalhofinalapplication.evaluation.contracts.oauth.v1

data class LoginResponse(
    val token: String,
    val message: String
)