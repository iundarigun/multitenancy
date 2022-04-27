package br.com.devcave.multitenancy.service

import br.com.devcave.multitenancy.domain.Cart
import br.com.devcave.multitenancy.domain.CartRequest
import br.com.devcave.multitenancy.domain.CartResponse
import br.com.devcave.multitenancy.domain.Item
import br.com.devcave.multitenancy.domain.ItemResponse
import br.com.devcave.multitenancy.domain.Status
import br.com.devcave.multitenancy.repository.CartRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CartService(
    private val productService: ProductService,
    private val cartRepository: CartRepository
) {
    @Transactional
    fun save(request: CartRequest): String {
        val cart = Cart(status = Status.PENDING).also { cart ->
            val itemList = request.itemList.map {
                Item(
                    quantity = it.quantity,
                    product = productService.findById(it.productId),
                    cart = cart
                )
            }
            cart.itemList.addAll(itemList)
        }
        return cartRepository.save(cart).reference
    }

    @Transactional(readOnly = true)
    fun findAll(): List<CartResponse> {
        return cartRepository.findAll().map {
            it.toCartResponse()
        }
    }

    @Transactional
    fun findByReference(reference: String): CartResponse {
        return cartRepository.findByReference(reference)?.toCartResponse() ?: throw RuntimeException("Not found")
    }
}

private fun Cart.toCartResponse(): CartResponse =
    CartResponse(
        id = this.id,
        shopAt = this.shopAt,
        reference = this.reference,
        status = this.status,
        items = this.itemList.map {
            ItemResponse(it.product.id, it.product.name, it.quantity)
        }
    )
