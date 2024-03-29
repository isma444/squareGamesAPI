package com.squareGames.squareGamesAPI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String userName;
    @Value("${spring.datasource.password}")
    private String mdp;
    @Bean
    @Profile("h2")
    public DataSource dataSourceH2(){
        return DataSourceBuilder.create()
                .url(url)
                .username(userName)
                .password(mdp)
                .build();
    }

    @Bean
    @Profile("mysql")
    public DataSource dataSourceMysql(){
        return DataSourceBuilder.create()
                .url(url)
                .username(userName)
                .password(mdp)
                .build();
    }

}
