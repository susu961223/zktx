package com.xiaoshu.common.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.xiaoshu.admin.Enum.DBTypeEnum;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

@SpringBootConfiguration
@PropertySource(value = {"file:config/config.properties","classpath:config/config.properties"},ignoreResourceNotFound = true)
@MapperScan({"com.xiaoshu.admin.mapper*","com.xiaoshu.admin.oracle.mapper*","com.xiaoshu.admin.shbfk.mapper*"})
public class MybatisPlusConfig {
	
    /***
     * plus 的性能优化
     * @return
     */
    @Bean
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        /*<!-- SQL 执行性能分析，开发环境使用，线上不推荐。 maxTime 指的是 sql 最大执行时长 -->*/
/*        performanceInterceptor.setMaxTime(10000);*/
        /*<!--SQL是否格式化 默认false-->*/
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }

    /**
     * mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor page = new PaginationInterceptor();
        page.setDialectType("mysql");
        return page;
    }
    
    @Bean(name = "db1")
    @ConfigurationProperties(prefix = "spring.datasource" )
    public DataSource db1 () {
        return DruidDataSourceBuilder.create().build();
    }
 
    @Bean(name = "db2")
    @ConfigurationProperties(prefix = "spring.second-datasource" )
    public DataSource db2 () {
        return DruidDataSourceBuilder.create().build();
    }
    
    @Bean(name = "db3")
    @ConfigurationProperties(prefix = "spring.third-datasource" )
    public DataSource db3 () {
        return DruidDataSourceBuilder.create().build();
    }
    /**
     * 动态数据源配置
     * @return
     */
    @Bean
    @Primary
    public DataSource multipleDataSource (@Qualifier("db1") DataSource db1,
                                          @Qualifier("db2") DataSource db2, 
                                          @Qualifier("db3") DataSource db3 ) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map< Object, Object > targetDataSources = new HashMap<>();
        targetDataSources.put(DBTypeEnum.db1.getValue(), db1 );
        targetDataSources.put(DBTypeEnum.db2.getValue(), db2);
        targetDataSources.put(DBTypeEnum.db3.getValue(), db3);
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.setDefaultTargetDataSource(db1);
        return dynamicDataSource;
    }
    
}
