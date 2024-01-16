package com.example.datacollector.config.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource({"classpath:application.yml"})
@EnableJpaRepositories(
        basePackages = {"com.example.datacollector.repo.crud"},
        entityManagerFactoryRef = "postgresEntityManager",
        transactionManagerRef = "postgresTransactionManager")
public class PostgresConfiguration {
    private Environment env;

    public PostgresConfiguration(@Autowired Environment env) {
        this.env = env;
    }

    @Bean
    @Qualifier("postgres_entity_manager")
    public LocalContainerEntityManagerFactoryBean postgresEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(postgresDataSource());
        em.setPackagesToScan("com.example.datacollector.data.entity");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "none");
        properties.put("hibernate.jdbc.batch_size", 10000);
        properties.put("hibernate.order_inserts", true);
        properties.put("hibernate.order_updates", true);
        em.setJpaPropertyMap(properties);

        return em;
    }


    @Bean
    public DataSource postgresDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(env.getProperty("spring.datasource.postgres.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.postgres.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.postgres.password"));
        dataSource.setDriverClassName("org.postgresql.Driver");
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager postgresTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(postgresEntityManager().getObject());
        return transactionManager;
    }

    @Bean
    @Qualifier("postgres_data_source")
    public JdbcTemplate postgresJdbcTemplate() {
        return new JdbcTemplate(postgresDataSource());
    }

}
