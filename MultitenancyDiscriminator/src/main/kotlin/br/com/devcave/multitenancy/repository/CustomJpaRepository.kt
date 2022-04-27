package br.com.devcave.multitenancy.repository

import br.com.devcave.multitenancy.domain.BaseEntity
import br.com.devcave.multitenancy.tenant.getTenant
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean
import java.util.Optional

@NoRepositoryBean
interface CustomJpaRepository<T : BaseEntity, ID : Any> : JpaRepository<T, ID> {

    override fun findById(id: ID): Optional<T> {
        return findByIdAndTenantId(id, tenantId = getTenant() ?: 0)
    }

    fun findByIdAndTenantId(id: ID, tenantId: Long): Optional<T>

    override fun getById(id: ID): T {
        return findById(id).orElseThrow()
    }

    override fun deleteById(id: ID) {
        deleteByIdAndTenantId(id, tenantId = getTenant() ?: 0)
    }

    fun deleteByIdAndTenantId(id: ID, tenantId: Long)

    override fun deleteAllById(ids: Iterable<ID>) {
        ids.forEach { deleteByIdAndTenantId(it, tenantId = getTenant() ?: 0) }
    }
}