package br.com.devcave.multitenancy.domain

import br.com.devcave.multitenancy.tenant.getTenant
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.Filter
import org.hibernate.annotations.FilterDef
import org.hibernate.annotations.ParamDef
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.MappedSuperclass
import javax.persistence.PrePersist

@MappedSuperclass
@FilterDef(
    name = "tenantFilter", parameters =
    [ParamDef(name = "tenantId", type = "long")]
)
@Filter(name = "tenantFilter", condition = "tenant_id = :tenantId")
open class BaseEntity(
    @Column(nullable = false)
    var tenantId: Long? = null,

    @CreationTimestamp
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @UpdateTimestamp
    val updatedAt: LocalDateTime = LocalDateTime.now()
) {

    @PrePersist
    fun prePersist() {
        this.tenantId = getTenant()
    }
}

