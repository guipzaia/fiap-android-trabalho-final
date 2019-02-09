package br.com.fiap.trabalhofinalapplication.evaluation.contracts

import br.com.fiap.trabalhofinalapplication.evaluation.enums.GenreEnum
import java.util.*


data class Customer(
    val id: String? = null,
    val documentNumber: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val genre: GenreEnum? = null,
    val dateOfBirth: String? = null,
    val contacts: List<Contact>? = null,
    val adresses: List<Address>? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null
)