package com.clinic.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.clinic.repositories", entityManagerFactoryRef = "cmEntityManager", transactionManagerRef = "cmTransactionManager")
@EnableConfigurationProperties
public class Cm {

	@Value("${oracle.dialect}")
	private String dialect;

	@Value("${oracle.drivername}")
	private String driverName;

	@Value("${cm.url}")
	private String cmUrl;

	@Value("${cm.username}")
	private String cmUserName;

	@Value("${cm.password}")
	private String cmPassword;

	@Autowired
	private DbConfiguration dbConfiguration;

	@Bean
	public LocalContainerEntityManagerFactoryBean cmEntityManager() {

		return dbConfiguration.getEntityManager(dialect, "com.clinic.entities", "cmEM", cmDataSource());

	}

	@Bean(name = "cmDataSource")
	public HikariDataSource cmDataSource() {

		return dbConfiguration.getDataSource(driverName, cmUrl, cmUserName, cmPassword);
	}

	@Bean(name = "cmJdbcTemplate")
	public JdbcTemplate cmJdbcTemplate(@Qualifier("cmDataSource") HikariDataSource ds) {
		return new JdbcTemplate(ds);
	}

	@Bean
	public PlatformTransactionManager cmTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(cmEntityManager().getObject());
		return transactionManager;
	}

}
