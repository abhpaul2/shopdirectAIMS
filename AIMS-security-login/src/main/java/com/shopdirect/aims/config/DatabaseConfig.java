package com.shopdirect.aims.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean; 
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource; 

@Configuration
public class DatabaseConfig { 

	@Bean    
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource firstDataSource() {
        return DataSourceBuilder.create().build();
    }

	@Bean
	@Primary
    @ConfigurationProperties(prefix = "spring.seconddatasource")
    public DataSource secondDataSource() {
        return DataSourceBuilder.create().build();
    }
	
	@Bean
    public JdbcTemplate jdbcTemplateOne(@Qualifier("firstDataSource") DataSource ds) {
        return new JdbcTemplate(ds);
    }

    @Bean
    public JdbcTemplate jdbcTemplateTwo(@Qualifier("secondDataSource") DataSource ds) {
        return new JdbcTemplate(ds);
    }

}
