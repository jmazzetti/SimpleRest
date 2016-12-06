package com.simplerest.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@EnableJpaRepositories(basePackages = "com.simplerest.persistence")
@EnableTransactionManagement
public class JPAConfig {

    private static final String ENTITY_PACKAGES_SCAN = "com.simplerest.persistence.entity";
    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";


    private static Logger log = LogManager.getLogger(com.simplerest.config.JPAConfig.class);

    @Value("${database.host}")
    private String HOST;
    @Value("${database.port}")
    private Integer PORT;
    @Value("${database.name}")
    private String NAME;
    @Value("${database.user}")
    private String USER;
    @Value("${database.pass}")
    private String PASS;
    @Value("${database.hbm2ddl}")
    private String HBM2DDL;
    @Value("${database.showsql}")
    private Boolean SHOW_SQL;
    @Value("${database.generateddl}")
    private Boolean GENERATE_DDL;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setUrl("jdbc:mysql://"+HOST+":"+PORT+"/"+NAME);
        dataSource.setUsername(USER);
        dataSource.setPassword(PASS);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(jpaVendorAdapter());
        factory.setPackagesToScan(ENTITY_PACKAGES_SCAN);
        factory.setDataSource(dataSource());
        factory.setJpaProperties(getJpaProperties());
        return factory;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(SHOW_SQL);
        hibernateJpaVendorAdapter.setGenerateDdl(GENERATE_DDL);
        hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
        return hibernateJpaVendorAdapter;
    }


    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory().getObject());
        txManager.setDataSource(dataSource());
        return txManager;
    }

    private Properties getJpaProperties() {
        return new Properties() {
            {
                setProperty("hibernate.hbm2ddl.auto", HBM2DDL);
                setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
                setProperty("hibernate.show_sql", SHOW_SQL.toString());
                setProperty("hibernate.format_sql", "true");
            }
        };
    }
}
