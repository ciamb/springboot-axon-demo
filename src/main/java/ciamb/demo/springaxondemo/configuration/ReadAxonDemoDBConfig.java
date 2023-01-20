package ciamb.demo.springaxondemo.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "readEntityManagerFactory",
        transactionManagerRef = "readTransactionManager",
        basePackages = { "ciamb.demo.springaxondemo.core.api.repository.read"}
)
public class ReadAxonDemoDBConfig {
    @Bean(name = "readDataSource")
    @ConfigurationProperties(prefix = "spring.seconddatasource")
    public DataSource readDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "readEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean readEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                            @Qualifier("readDataSource") DataSource readDataSource) {
        return builder
                .dataSource(readDataSource)
                .packages("ciamb.demo.springaxondemo.core.api.repository.read")
                .build();
    }

    @Bean(name = "readTransactionManager")
    public PlatformTransactionManager readTransactionManager(@Qualifier("readEntityManagerFactory") EntityManagerFactory readEntityManagerFactory) {
        return new JpaTransactionManager(readEntityManagerFactory);
    }
}
