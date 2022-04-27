package br.com.devcave.multitenancy.tenant

import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class TenantFilter : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        logger.info("do filter")
        kotlin.runCatching {
            request.getHeader("tenantId").toLongOrNull()?.let {
                setTenant(it)
            }
        }
        filterChain.doFilter(request, response)
    }
}