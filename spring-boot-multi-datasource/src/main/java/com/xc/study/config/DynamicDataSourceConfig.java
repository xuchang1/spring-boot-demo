package com.xc.study.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DynamicDataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.ds01")
    @Primary
    public DataSource ds01DataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.ds02")
    public DataSource ds02DataSource() {
        return DruidDataSourceBuilder.create().build();
    }

//    @Bean
//    @Primary
//    public DataSource dynamicDataSource(DataSource ds01DataSource, DataSource ds02DataSource) {
//        Map<Object, Object> targetDataSources = new HashMap<>();
//        targetDataSources.put("ds01", ds01DataSource);
//        targetDataSources.put("ds02", ds02DataSource);
//
//        DynamicRoutingDataSource dynamicDataSource = new DynamicRoutingDataSource();
//        dynamicDataSource.setTargetDataSources(targetDataSources);
//        dynamicDataSource.setDefaultTargetDataSource(ds01DataSource);
//
//        return dynamicDataSource;
//    }

    @Bean
    @Primary
    public SqlSessionFactory primarySqlSessionFactory(DataSource ds01DataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(ds01DataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/ds01/*.xml"));
        return sessionFactory.getObject();
    }

    @Bean
    public SqlSessionFactory secondarySqlSessionFactory(DataSource ds02DataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(ds02DataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/ds02/*.xml"));
        return sessionFactory.getObject();
    }

//    @Bean
//    public PlatformTransactionManager transactionManager(DataSource dynamicDataSource) {
//        return new DataSourceTransactionManager(dynamicDataSource);
//    }
}
