package br.com.devcave.multitenancy.tenant

private val tenantThread = ThreadLocal<Long>()

fun setTenant(tenantId: Long) {
    tenantThread.set(tenantId)
}

fun getTenant(): Long? = tenantThread.get() ?: null