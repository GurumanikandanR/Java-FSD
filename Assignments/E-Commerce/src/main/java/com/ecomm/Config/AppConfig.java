package com.ecomm.Config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "com.ecomm")
public class AppConfig {
    @Bean
    public DataSource dataSource(){
        HikariConfig config=new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/ecomm");
        config.setUsername("root");
        config.setPassword("root");
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return new HikariDataSource(config);
    }

    @Bean
    public JdbcTemplate getTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
