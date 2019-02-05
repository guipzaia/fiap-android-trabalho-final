package br.com.fiap.trabalhofinalapplication.evaluation.contracts.oauth.v1

data class LoginRequest(
    val username: String,
    val password: String
)