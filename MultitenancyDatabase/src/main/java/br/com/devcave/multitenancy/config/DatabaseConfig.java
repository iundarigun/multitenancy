package br.com.devcave.multitenancy.config;

import br.com.devcave.multitenancy.tenant.TenantRoutingDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DatabaseConfig {

    @Bean
    @Primary
    public DataSource dataSource(DataSourceProperties dataSourceProperties) {
        AbstractRoutingDataSource tenantRoutingDataSource = new TenantRoutingDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        dataSourceProperties.getDatasources().forEach(dataSourceProperty -> {
            DataSource dataSource = DataSourceBuilder.create()
                    .url(dataSourceProperty.getUrl())
                    .username(dataSourceProperty.getUsername())
                    .password(dataSourceProperty.getPassword())
                    .driverClassName(dataSourceProperty.getDriverClassName())
                    .build();

            if (dataSourceProperty.getName().equalsIgnoreCase("postgres")) {
                tenantRoutingDataSource.setDefaultTargetDataSource(dataSource);
            }
            targetDataSources.put(dataSourceProperty.getName(), dataSource);
        });
        tenantRoutingDataSource.setTargetDataSources(targetDataSources);
        tenantRoutingDataSource.afterPropertiesSet();
        return tenantRoutingDataSource;
    }}
