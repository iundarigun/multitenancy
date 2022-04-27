package br.com.devcave.multitenancy.domain

import java.time.LocalDateTime

data class CartResponse(
    val id: Long,
    val reference: String,
    val shopAt: LocalDateTime,
    val status: Status,
    val items: List<ItemResponse>
)

data class ItemResponse(
    val productId: Long,
    val productName: String,
    val quantity: Int
)
