package br.com.devcave.multitenancy.domain

import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
data class Cart(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val reference: String = UUID.randomUUID().toString(),

    val shopAt: LocalDateTime = LocalDateTime.now(),

    @Enumerated(value = EnumType.STRING)
    val status: Status,

    @OneToMany(mappedBy = "cart", cascade = [CascadeType.PERSIST, CascadeType.REMOVE])
    val itemList: MutableList<Item> = mutableListOf()
) : BaseEntity()
