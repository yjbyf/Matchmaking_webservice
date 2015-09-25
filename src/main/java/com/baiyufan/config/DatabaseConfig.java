package com.baiyufan.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;

@Configuration
@MapperScan(basePackages = { "com.baiyufan.db.persistence" })
public class DatabaseConfig {

	@Bean
	public DataSource dataSource() throws PropertyVetoException {

		DruidDataSource datasource = new DruidDataSource();
		datasource.setDriverClassName("com.mysql.jdbc.Driver");
		datasource
				.setUrl("jdbc:mysql://192.168.37.128:3306/wedding?useUnicode=true&amp;characterEncoding=utf8");
		datasource.setUsername("monty");
		datasource.setPassword("some_pass");
		datasource.setMaxActive(20);
		datasource.setInitialSize(5);
		return datasource;
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource)
			throws Exception {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		String url = "classpath*:com/baiyufan/db/sqlmap/**/*.xml";
		Resource[] resources = resolver.getResources(url);

		sessionFactory.setMapperLocations(resources);

		// 分页配置
		PageHelper pageHelper = new PageHelper();
		Properties p = new Properties();
		p.setProperty("dialect", "mysql");
		p.setProperty("reasonable", "true");
		pageHelper.setProperties(p);

		Interceptor[] plugins = { pageHelper };//
		sessionFactory.setPlugins(plugins);

		return sessionFactory.getObject();
	}

}
