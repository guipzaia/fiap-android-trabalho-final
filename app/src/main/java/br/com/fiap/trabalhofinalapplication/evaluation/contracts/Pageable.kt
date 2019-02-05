package br.com.fiap.trabalhofinalapplication.evaluation.contracts

data class Pageable(
    val sort: Sort,
    val pageSize: Long,
    val pageNumber: Long,
    val offset: Long,
    val paged: Boolean,
    val unpaged: Boolean
)