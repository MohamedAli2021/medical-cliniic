package com.clinic.configuration;

import java.util.HashMap;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DbConfiguration {

	public LocalContainerEntityManagerFactoryBean getEntityManager(String dialect, String packageToScan, String persistenceUnitName, HikariDataSource dataSource) {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource);
		em.setPackagesToScan(packageToScan);
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.dialect", dialect);
		properties.put("hibernate.temp.use_jdbc_metadata_defaults", "false");
		properties.put("hibernate.connection.useUnicode", "true");
		properties.put("hibernate.connection.characterEncoding", "UTF-8");
		properties.put("hibernate.connection.charSet", "UTF-8");
		em.setJpaPropertyMap(properties);
		em.setPersistenceUnitName(persistenceUnitName);
		return em;
	}

	public HikariDataSource getDataSource(String driverName, String jdbcUrl, String userName, String password) {

		HikariDataSource dataSource = DataSourceBuilder.create().driverClassName(driverName).url(jdbcUrl).username(userName).password(password).type(HikariDataSource.class).build();

		dataSource.addDataSourceProperty("characterEncoding", "utf8");
		return dataSource;

	}



}
