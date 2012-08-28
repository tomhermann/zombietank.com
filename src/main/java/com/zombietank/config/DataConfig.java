package com.zombietank.config;

import javax.inject.Inject;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jndi.JndiAccessor;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.AbstractEntityManagerFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zombietank.config.annotation.Dev;
import com.zombietank.config.annotation.Prod;

@Configuration
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
public class DataConfig {

	@Inject
	private DataSource dataSource;
	
	@Inject
	private EntityManagerFactory entityManagerFactory;
	
	@Bean
	public PersistenceExceptionTranslator exceptionTranslator() {
		return new HibernateExceptionTranslator();
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager(entityManagerFactory);
	}

	@Dev @Configuration
	static class Development {
		@Bean(destroyMethod = "shutdown")
		public DataSource dataSource() {
			return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).build();
		}
		
		@Bean
		public AbstractEntityManagerFactoryBean entityManagerFactory() {
			LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
			factory.setDataSource(dataSource());
			factory.setJpaVendorAdapter(jpaVendorAdapter());
			factory.setPackagesToScan("com.zombietank");
			factory.setPersistenceUnitName("spring-jpa");
			return factory;
		}
		
		private JpaVendorAdapter jpaVendorAdapter() {
			HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
			adapter.setShowSql(true);
			adapter.setGenerateDdl(true);
			adapter.setDatabase(Database.HSQL);
			return adapter;
		}
	}

	@Prod @Configuration
	static class Production {
		@Inject
		private Environment environment;
		
		@Bean(destroyMethod = "dispose")
		public DataSource dataSource() throws NamingException {
			String dsName = environment.getProperty("database.datasourceName");
			return new JndiAccessor().getJndiTemplate().lookup(dsName, DataSource.class);
		}
	}
}