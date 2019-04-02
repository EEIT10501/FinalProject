package com.funwork.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class RootAppConfig {
  public static final Logger logger = Logger.getLogger("com.funwork");

  /**
   * DataSource bean, build by com.mchange.v2.c3p0.
   */
  @Bean
  public DataSource dataSource() {
    ComboPooledDataSource ds = new ComboPooledDataSource();
    ds.setUser("memberuser@eeit105fk");   //遠端使用
    ds.setPassword("Eeit10507");
//    ds.setUser("memberuser@eeit105fk"); //local使用
//    ds.setPassword("Eeit10507");
    try {
      ds.setDriverClass("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    } catch (PropertyVetoException e) {
      logger.warning(e.getMessage());
    }
    ds.setJdbcUrl("jdbc:sqlserver://eeit105fk.database.windows.net:1433;databaseName=funwork");   //遠端
//  ds.setJdbcUrl("jdbc:sqlserver://localhost:1433;databaseName=funwork");                        //local
    ds.setInitialPoolSize(4);
    ds.setMaxPoolSize(8);
    return ds;
  }

  /**
   * IOC容器透過這個Bean建立SessionFactory，並自動注入給HibernateTransactionManager.
   */
  @Bean
  public LocalSessionFactoryBean sessionFactory() {
    LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
    factory.setDataSource(dataSource());
    factory.setPackagesToScan("com.funwork.model");
    factory.setHibernateProperties(additionalProperties());
    return factory;
  }

  /**
   * HibernateTransactionManager bean.
   */
  @Bean
  @Autowired
  public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
    HibernateTransactionManager txManager = new HibernateTransactionManager();
    txManager.setSessionFactory(sessionFactory);
    return txManager;
  }

  /**
   * Give LocalSessionFactoryBean 進階組態資訊.
   */
  public Properties additionalProperties() {
    Properties properties = new Properties();
    properties.put("hibernate.dialect", org.hibernate.dialect.SQLServer2012Dialect.class);
    properties.put("hibernate.show_sql", Boolean.FALSE);
    properties.put("hibernate.format_sql", Boolean.FALSE);
    properties.put("default_batch_fetch_size", 10);
    properties.put("hibernate.hbm2ddl.auto", "validate");
    return properties;
  }
}
