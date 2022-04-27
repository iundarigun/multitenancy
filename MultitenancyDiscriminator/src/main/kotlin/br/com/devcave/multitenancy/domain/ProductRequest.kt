package br.com.devcave.multitenancy.domain

class ProductRequest(
    val sku: String,
    val name: String,
    val price: Int
)