package br.com.fiap.trabalhofinalapplication.evaluation.contracts

data class Address(
    val zipCode: String,
    val country: String,
    val city: String,
    val state: String,
    val street: String,
    val neighborhood: String,
    val complement: String,
    val number: String,
    val notes: String
)