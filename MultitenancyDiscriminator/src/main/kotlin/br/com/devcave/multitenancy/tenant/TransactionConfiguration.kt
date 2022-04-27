package br.com.devcave.multitenancy.tenant

import org.hibernate.Session
import org.springframework.beans.factory.ObjectProvider
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.orm.jpa.JpaTransactionManager
import javax.persistence.EntityManager


@Configuration
class TransactionConfiguration {

    @Bean
    fun transactionManager(transactionManagerCustomizer: ObjectProvider<TransactionManagerCustomizers>): JpaTransactionManager {
        val transactionManager = object : JpaTransactionManager() {
            override fun createEntityManagerForTransaction(): EntityManager {
                return super.createEntityManagerForTransaction().also {
                    it.unwrap(Session::class.java)
                        .enableFilter("tenantFilter")
                        .setParameter("tenantId", getTenant() ?: 0L)
                }
            }
        }
        transactionManagerCustomizer.ifAvailable {
            it.customize(transactionManager)
        }
        return transactionManager
    }
}