package com.jyes.www.spring;

import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jyes.www.common.Config;

@Configuration
public class DataSourceConfig {

	ResourceBundle resource = ResourceBundle.getBundle("config.spring.context.jdbc");
	
	@Bean(destroyMethod="close")
	public DataSource dataSourceSpied() {
		Config.init();
		String jdbc_driverClass = resource.getString("jdbc.driverClass");
		String jdbc_dev_url = resource.getString("jdbc.dev.url");
		String jdbc_dep_url = resource.getString("jdbc.dep.url");
		String jdbc_username = resource.getString("jdbc.username");
		String jdbc_password = resource.getString("jdbc.password");
		int jdbc_maxActive = Integer.parseInt(resource.getString("jdbc.maxActive"));
		int jdbc_maxIdle = Integer.parseInt(resource.getString("jdbc.maxIdle"));
		int jdbc_maxWait = Integer.parseInt(resource.getString("jdbc.maxWait"));
		boolean jdbc_removeAbandoned = Boolean.parseBoolean(resource.getString("jdbc.removeAbandoned"));
		int jdbc_removeAbandonedTimeout = Integer.parseInt(resource.getString("jdbc.removeAbandonedTimeout"));
		boolean jdbc_logAbandoned = Boolean.parseBoolean(resource.getString("jdbc.logAbandoned"));
		String jdbc_validationQuery = resource.getString("jdbc.validationQuery");
		boolean jdbc_testOnBorrow = Boolean.parseBoolean(resource.getString("jdbc.testOnBorrow"));
		boolean jdbc_testOnReturn = Boolean.parseBoolean(resource.getString("jdbc.testOnReturn"));
		BasicDataSource dataSourceSpied = new BasicDataSource();
		dataSourceSpied.setDriverClassName(jdbc_driverClass);
		if(Config.isDevMode) {
			dataSourceSpied.setUrl(jdbc_dev_url);
		} else {
			dataSourceSpied.setUrl(jdbc_dep_url);
		}
		dataSourceSpied.setUsername(jdbc_username);
		dataSourceSpied.setPassword(jdbc_password);
		dataSourceSpied.setMaxActive(jdbc_maxActive);
		dataSourceSpied.setMaxIdle(jdbc_maxIdle);
		dataSourceSpied.setMaxWait(jdbc_maxWait);
		dataSourceSpied.setRemoveAbandoned(jdbc_removeAbandoned);
		dataSourceSpied.setRemoveAbandonedTimeout(jdbc_removeAbandonedTimeout);
		dataSourceSpied.setLogAbandoned(jdbc_logAbandoned);
		dataSourceSpied.setValidationQuery(jdbc_validationQuery);
		dataSourceSpied.setTestOnBorrow(jdbc_testOnBorrow);
		dataSourceSpied.setTestOnReturn(jdbc_testOnReturn);
		return dataSourceSpied;
	}
	
}
