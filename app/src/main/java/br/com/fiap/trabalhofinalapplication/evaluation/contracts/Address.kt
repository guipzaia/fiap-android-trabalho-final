package br.com.fiap.trabalhofinalapplication.evaluation.contracts

data class Address(
    val zipCode: String? = null,
    val country: String? = null,
    val city: String? = null,
    val state: String? = null,
    val street: String? = null,
    val neighborhood: String? = null,
    val complement: String? = null,
    val number: String? = null,
    val notes: String? = null
)