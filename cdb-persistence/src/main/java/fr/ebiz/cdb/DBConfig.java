package fr.ebiz.cdb;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@ComponentScan("fr.ebiz.cdb.persistence")
@PropertySource("classpath:application.properties")
public class DBConfig {

    private static final String PROPERTY_DATASOURCE_URL = "datasource.url";
    private static final String PROPERTY_DATASOURCE_USERNAME = "datasource.username";
    private static final String PROPERTY_DATASOURCE_PASSWORD = "datasource.password";
    private static final String PROPERTY_DATASOURCE_DRIVER_CLASS_NAME = "datasource.driver-class-name";
    private static final String PROPERTY_DATASOURCE_INITIAL_SIZE = "datasource.pool-size";

    private final Environment env;

    /**
     * @param env env
     */
    @Autowired
    public DBConfig(Environment env) {
        this.env = env;
    }

    /**
     * @return dataSource
     */
    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(env.getRequiredProperty(PROPERTY_DATASOURCE_URL));
        dataSource.setUsername(env.getRequiredProperty(PROPERTY_DATASOURCE_USERNAME));
        dataSource.setPassword(env.getRequiredProperty(PROPERTY_DATASOURCE_PASSWORD));
        dataSource.setDriverClassName(env.getRequiredProperty(PROPERTY_DATASOURCE_DRIVER_CLASS_NAME));
        dataSource.setInitialSize(Integer.parseInt(env.getRequiredProperty(PROPERTY_DATASOURCE_INITIAL_SIZE)));
        return dataSource;
    }

    /**
     * PlatformTransactionManager.
     *
     * @return PlatformTransactionManager
     */
    @Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }

}
