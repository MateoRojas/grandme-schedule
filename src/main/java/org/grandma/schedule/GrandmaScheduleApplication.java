package org.grandma.schedule;

import com.querydsl.jpa.hibernate.HibernateQueryFactory;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.util.Properties;

@SpringBootApplication
@EnableTransactionManagement
public class GrandmaScheduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrandmaScheduleApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public HibernateQueryFactory queryFactory(LocalSessionFactoryBean sessionFactory) {
        return new HibernateQueryFactory(() -> sessionFactory.getObject().getCurrentSession());
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {

        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("org.grandma.schedule.dto");
        sessionFactory.setHibernateProperties(new Properties(){
            {
                setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
                setProperty("hibernate.show_sql", "true");
            }
        });

        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost/grandma-schedule-db?serverTimezone=UTC");
        dataSource.setUsername("grandma-schedule-db");
        dataSource.setPassword("12345678");

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager(LocalSessionFactoryBean sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory.getObject());
        return transactionManager;
    }
}
