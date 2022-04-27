package br.com.devcave.multitenancy.repository

import br.com.devcave.multitenancy.domain.Cart
import org.springframework.data.jpa.repository.EntityGraph

interface CartRepository: CustomJpaRepository<Cart, Long> {
    @EntityGraph(attributePaths = ["itemList", "itemList.product"])
    fun findByReference(reference: String): Cart?
}
