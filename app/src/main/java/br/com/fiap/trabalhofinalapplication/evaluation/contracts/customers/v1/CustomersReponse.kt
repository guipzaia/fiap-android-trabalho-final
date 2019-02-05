package br.com.fiap.trabalhofinalapplication.evaluation.contracts.customers.v1

import br.com.fiap.trabalhofinalapplication.evaluation.contracts.Customer
import br.com.fiap.trabalhofinalapplication.evaluation.contracts.Sort

data class CustomersReponse(
    val content: List<Customer>,
    val totalPages: Long,
    val totalElements: Long,
    val last: Long,
    val first: Long,
    val sort: Sort,
    val numberOfElements: Long,
    val size: Long,
    val number: Long,
    val empty: Boolean)