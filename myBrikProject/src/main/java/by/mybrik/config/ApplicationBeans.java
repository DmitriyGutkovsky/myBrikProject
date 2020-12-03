package by.mybrik.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import javax.sql.DataSource;

@Configuration
public class ApplicationBeans {

//    @Bean
//    public DataSource hikariDatasource(DatabaseConfig databaseConfig){
//
//        HikariDataSource hikariDataSource = new HikariDataSource();
//
//        hikariDataSource.setDriverClassName(databaseConfig.getDriverName());
//        hikariDataSource.setJdbcUrl(databaseConfig.getUrl());
//        hikariDataSource.setUsername(databaseConfig.getLogin());
//        hikariDataSource.setPassword(databaseConfig.getPassword());
//        hikariDataSource.setMaximumPoolSize(20);
//
//        return hikariDataSource;
//    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource){
        return new NamedParameterJdbcTemplate(dataSource);
    }


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

}