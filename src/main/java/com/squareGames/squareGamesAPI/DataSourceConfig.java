package com.squareGames.squareGamesAPI;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    @Profile("h2")
    public DataSource dataSourceH2(){
        return DataSourceBuilder.create()
                .url("jdbc:h2:mem:testdb")
                .username("sa")
                .password("")
                .build();
    }

    @Bean
    @Profile("mysql")
    public DataSource dataSourceMysql(){
        return DataSourceBuilder.create()
                .url("jdbc:mysql://localhost:6603/square_games")
                .username("root")
                .password("helloworld")
                .build();
    }
}
