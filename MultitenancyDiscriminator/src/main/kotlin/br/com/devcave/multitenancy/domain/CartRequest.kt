package br.com.devcave.multitenancy.domain

import javax.validation.Valid
import javax.validation.constraints.Positive
import javax.validation.constraints.Size

data class CartRequest(
    @Valid
    @field:Size(min = 1)
    val itemList: List<ItemRequest>
)

data class ItemRequest(
    @Positive
    val quantity: Int,
    @Positive
    val productId: Long
)
