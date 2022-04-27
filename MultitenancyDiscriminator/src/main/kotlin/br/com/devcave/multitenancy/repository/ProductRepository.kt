package br.com.devcave.multitenancy.repository

import br.com.devcave.multitenancy.domain.Product
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface ProductRepository : CustomJpaRepository<Product, Long> {
    fun findByNameContainingIgnoreCase(name:String): List<Product>
    fun existsBySku(sku: String): Boolean
    @Modifying
    @Query("update Product p set p.price = p.price + :value where p.sku = :sku")
    fun updatePrice(sku:String, value: Int)

    // Filters do not work on native queries, since filter is applied to Entity
    @Modifying
    @Query(value = "update Product set price = price + :value where sku = :sku", nativeQuery = true)
    fun updatePriceNative(sku:String, value: Int)
}