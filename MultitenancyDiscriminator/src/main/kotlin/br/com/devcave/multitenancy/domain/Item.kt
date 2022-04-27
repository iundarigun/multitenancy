package br.com.devcave.multitenancy.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class Item(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val quantity: Int = 1,

    @ManyToOne
    val product: Product,

    @ManyToOne
    val cart: Cart
) : BaseEntity()
