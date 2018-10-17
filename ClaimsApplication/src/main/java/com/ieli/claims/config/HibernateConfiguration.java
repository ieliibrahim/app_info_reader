package com.ieli.claims.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource({ "classpath:application.properties" })
public class HibernateConfiguration {

	@Autowired
	private Environment environment;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(this.environment.getRequiredProperty("jdbc.driverClassName"));
		dataSource.setUrl(this.environment.getRequiredProperty("jdbc.url"));
		dataSource.setUsername(this.environment.getRequiredProperty("jdbc.username"));
		dataSource.setPassword(this.environment.getRequiredProperty("jdbc.password"));
		return dataSource;
	}

}
