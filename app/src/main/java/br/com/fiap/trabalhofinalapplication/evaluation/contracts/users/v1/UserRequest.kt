package br.com.fiap.trabalhofinalapplication.evaluation.model.users.v1

data class UserRequest(

    var accountNonLocked: Boolean = true,
    val createdAt: String,
    var credentialsNonExpired: Boolean = true,
    val email: String,
    var enable: Boolean = true,
    val id: String,
    val password: String,
    val updatedAt: String,
    val username: String
)