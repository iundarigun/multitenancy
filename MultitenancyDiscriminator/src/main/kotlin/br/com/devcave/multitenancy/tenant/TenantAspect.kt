package br.com.devcave.multitenancy.tenant

import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.hibernate.Session
import org.springframework.stereotype.Component
import javax.persistence.EntityManager

//@Aspect
//@Component
class TenantAspect(
    private val entityManager: EntityManager
) {
//    @Before("execution(* org.springframework.data.repository.Repository+.*(..))")
    fun activateTenantFilter() {
        val session = entityManager.unwrap(Session::class.java)
        session.enableFilter("tenantFilter")
            .setParameter("tenantId", getTenant() ?: 0)
    }
}