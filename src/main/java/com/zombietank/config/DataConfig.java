package com.zombietank.config;

import java.io.IOException;

import javax.inject.Inject;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jndi.JndiAccessor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zombietank.config.annotation.Dev;
import com.zombietank.config.annotation.Prod;
import com.zombietank.config.db.DatabaseBootstrap;
import com.zombietank.config.db.ScriptHistory;
import com.zombietank.config.db.ScriptRunner;
import com.zombietank.config.db.Scripts;

@Configuration
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
public class DataConfig {

	@Inject
	private DataSource dataSource;

	/**
	 * Allows repositories to access RDBMS data using the JDBC API.
	 */
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource);
	}

	/**
	 * Allows transactions to be managed against the RDBMS using the JDBC API.
	 */
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource);
	}

	@Dev @Configuration
	static class Development {
		@Inject
		private JdbcTemplate jdbcTemplate;
		
		@Bean
		public DatabaseBootstrap databaseBootstrap() throws IOException {
			Scripts scripts = new Scripts().addSystemScript("sql/scriptHistory.sql").addSystemScript("sql/schema.sql").addScript("sql/data.sql");
			return new DatabaseBootstrap(scripts, new ScriptRunner(new ScriptHistory(dataSource()), jdbcTemplate));
		}
		
		@Bean(destroyMethod = "shutdown")
		public DataSource dataSource() {
			return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
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