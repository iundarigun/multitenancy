package br.com.devcave.multitenancy.controller

import br.com.devcave.multitenancy.domain.CartRequest
import br.com.devcave.multitenancy.domain.CartResponse
import br.com.devcave.multitenancy.service.CartService
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("carts")
class CartController(
    private val cartService: CartService
) {

    @PostMapping
    @Parameter(name = "tenantId", `in` = ParameterIn.HEADER)
    fun closeCart(@Valid @RequestBody request: CartRequest): String {
        return cartService.save(request)
    }

    @GetMapping
    @Parameter(name = "tenantId", `in` = ParameterIn.HEADER)
    fun findAll(): List<CartResponse> {
        return cartService.findAll()
    }

    @GetMapping("reference/{reference}")
    @Parameter(name = "tenantId", `in` = ParameterIn.HEADER)
    fun findByReference(@PathVariable reference: String): CartResponse {
        return cartService.findByReference(reference)
    }
}