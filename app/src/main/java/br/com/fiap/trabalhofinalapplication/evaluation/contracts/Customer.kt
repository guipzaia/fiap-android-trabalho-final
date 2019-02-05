package br.com.fiap.trabalhofinalapplication.evaluation.contracts

import br.com.fiap.trabalhofinalapplication.evaluation.enums.GenreEnum
import java.util.*


data class Customer(
    val id: String,
    val documentNumber: String,
    val firstName: String,
    val lastName: String,
    val genre: GenreEnum,
    val dateOfBirth: Calendar,
    val contacts: List<Contact>,
    val adresses: List<Address>,
    val createdAt: Calendar,
    val updatedAt: Calendar
)