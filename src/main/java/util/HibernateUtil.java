package util;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.Properties;



public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static HikariDataSource dataSource = null;


    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = getConfiguration();


                configuration.addAnnotatedClass(model.entity.User.class);
                configuration.addAnnotatedClass(model.entity.Address.class);
                configuration.addAnnotatedClass(model.entity.ShoppingCart.class);
                configuration.addAnnotatedClass(model.entity.Product.class);
                configuration.addAnnotatedClass(model.entity.Order.class);
                configuration.addAnnotatedClass(model.entity.DiscountedProduct.class);
                configuration.addAnnotatedClass(model.entity.Rating.class);
                configuration.addAnnotatedClass(model.entity.Category.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

                ValidatorFactory factory = Validation.byDefaultProvider()
                        .configure()
                        .messageInterpolator(new ParameterMessageInterpolator())
                        .buildValidatorFactory();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    private static Configuration getConfiguration() {
        HikariDataSource dataSource = getHikariDataSource();

        Configuration configuration = new Configuration();

        // Hibernate settings equivalent to hibernate.cfg.xml's properties
        Properties settings = new Properties();
        settings.put(Environment.DATASOURCE, dataSource);
        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
        settings.put(Environment.SHOW_SQL, "true");
        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        settings.put(Environment.HBM2DDL_AUTO, "update");

        configuration.setProperties(settings);
        return configuration;
    }

    public static HikariDataSource getHikariDataSource() {
        if (dataSource == null) {
            HikariConfig hikariConfig = new HikariConfig();

            OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
            int processors = Runtime.getRuntime().availableProcessors();
            double systemLoadAverage = osBean.getSystemLoadAverage();
            int calculatedPoolSize = (int) Math.max(1, Math.ceil(processors * systemLoadAverage));

            hikariConfig.setMaximumPoolSize(calculatedPoolSize);
            hikariConfig.setMinimumIdle(Math.max(1, calculatedPoolSize / 2));
            hikariConfig.setIdleTimeout(300000); // 5 minutes
            hikariConfig.setMaxLifetime(1800000); // 30 minutes
            hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
            hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/websiteschema");
            hikariConfig.setUsername("root");
            hikariConfig.setPassword("1234");
            dataSource = new HikariDataSource(hikariConfig);
        }
        return dataSource;
    }

}