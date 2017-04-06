package fr.ebiz.cdb;

import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan("fr.ebiz.cdb.persistence")
@PropertySource("classpath:application.properties")
public class DBConfig {

    private static final String PROP_DS_PREFIX = "datasource.";
    private static final String PROP_DS_URL = "url";
    private static final String PROP_DS_USER = "user";
    private static final String PROP_DS_PASSWORD = "password";
    private static final String PROP_DS_POOL_SIZE = "pool-size";
    private static final String PROP_DS_JDBC_DRIVER = "driver";

    @Autowired
    private Environment env;

    /**
     * @return DataSource
     */
    @Bean
    public DataSource dataSource() {
        HikariDataSource ds = new HikariDataSource();
        ds.addDataSourceProperty(PROP_DS_URL, env.getRequiredProperty(PROP_DS_PREFIX + PROP_DS_URL));
        ds.addDataSourceProperty(PROP_DS_USER, env.getRequiredProperty(PROP_DS_PREFIX + PROP_DS_USER));
        ds.addDataSourceProperty(PROP_DS_PASSWORD, env.getRequiredProperty(PROP_DS_PREFIX + PROP_DS_PASSWORD));
        ds.setMaximumPoolSize(Integer.parseInt(env.getRequiredProperty(PROP_DS_PREFIX + PROP_DS_POOL_SIZE)));
        ds.setDataSourceClassName(env.getRequiredProperty(PROP_DS_PREFIX + PROP_DS_JDBC_DRIVER));
        return ds;
    }

    /**
     * @return LocalSessionFactoryBean
     */
    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("fr.ebiz.cdb.core");
        sessionFactory.setHibernateProperties(getHibernateProperties());
        return sessionFactory;
    }

    /**
     * @return Properties
     */
    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put(AvailableSettings.DIALECT, env.getRequiredProperty("hibernate.dialect"));
        properties.put(AvailableSettings.SHOW_SQL, env.getRequiredProperty("hibernate.show_sql"));
        return properties;
    }

    /**
     * @param sessionFactory sessionFactory
     * @return HibernateTransactionManager
     */
    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }

}
