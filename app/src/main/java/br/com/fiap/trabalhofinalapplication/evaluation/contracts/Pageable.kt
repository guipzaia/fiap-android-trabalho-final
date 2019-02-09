package br.com.fiap.trabalhofinalapplication.evaluation.contracts

data class Pageable(
    val sort: Sort? = null,
    val pageSize: Long? = null,
    val pageNumber: Long? = null,
    val offset: Long? = null,
    val paged: Boolean? = null,
    val unpaged: Boolean? = null
)