package br.com.devcave.multitenancy.service

import br.com.devcave.multitenancy.domain.Product
import br.com.devcave.multitenancy.domain.ProductRequest
import br.com.devcave.multitenancy.repository.ProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductService(
    private val productRepository: ProductRepository
) {
    @Transactional(readOnly = true)
    fun findById(id: Long): Product {
        return productRepository.findById(id).orElseThrow()
    }

    @Transactional
    fun save(request: ProductRequest): Long {
        if (productRepository.existsBySku(request.sku)) {
            throw RuntimeException("SKU already exists: ${request.sku}")
        }
        return productRepository.save(request.toEntity()).id
    }

    @Transactional(readOnly = true)
    fun findAll(): List<Product> {
        return productRepository.findAll().toList()
    }

    @Transactional(readOnly = true)
    fun findByName(name: String): List<Product> {
        return productRepository.findByNameContainingIgnoreCase(name)
    }

    @Transactional
    fun deleteById(id: Long) {
        productRepository.deleteById(id)
    }
}

private fun ProductRequest.toEntity(): Product =
    Product(
        sku = this.sku,
        name = this.name,
        price = this.price
    )
