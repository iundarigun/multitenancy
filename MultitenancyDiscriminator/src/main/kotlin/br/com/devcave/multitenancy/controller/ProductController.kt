package br.com.devcave.multitenancy.controller

import br.com.devcave.multitenancy.domain.Product
import br.com.devcave.multitenancy.domain.ProductRequest
import br.com.devcave.multitenancy.service.ProductService
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("products")
class ProductController(
    private val productService: ProductService
) {
    @GetMapping("{id}")
    @Parameter(name = "tenantId", `in` = ParameterIn.HEADER)
    fun findById(@PathVariable id:Long): Product {
        return productService.findById(id)
    }

    @GetMapping
    @Parameter(name = "tenantId", `in` = ParameterIn.HEADER)
    fun findAll(): List<Product> {
        return productService.findAll()
    }

    @GetMapping("name")
    @Parameter(name = "tenantId", `in` = ParameterIn.HEADER)
    fun findByName(@RequestParam name:String): List<Product> {
        return productService.findByName(name)
    }

    @PostMapping
    @Parameter(name = "tenantId", `in` = ParameterIn.HEADER)
    fun create(request: ProductRequest): Long {
        return productService.save(request)
    }

    @DeleteMapping("{id}")
    @Parameter(name = "tenantId", `in` = ParameterIn.HEADER)
    fun deleteById(@PathVariable id: Long) {
        productService.deleteById(id)
    }
}