package br.com.devcave.multitenancy.tenant;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.RequestFacade;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@Configuration
public class TenantFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        log.info("filter");

        String tenantId = ((HttpServletRequest) servletRequest).getHeader("tenantId");
        if (StringUtils.isEmpty(tenantId)){
            tenantId = "schema1";
        }
        Tenant.setIdentificador(tenantId);

        filterChain.doFilter(servletRequest, servletResponse);
    }
}