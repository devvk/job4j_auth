package ru.job4j.auth;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class PersonsApplication {

    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:db/dbchangelog.xml");
        liquibase.setDataSource(dataSource);
        return liquibase;
    }

	public static void main(String[] args) {
		SpringApplication.run(PersonsApplication.class, args);
        System.out.println("http://localhost:8080/persons");
	}
}
