package utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.Properties;

public class DataSourceConfig {
    private static HikariDataSource dataSource = null;

    public static HikariDataSource getHikariDataSource() {
        if (dataSource == null) {
            HikariConfig hikariConfig = new HikariConfig();

            Properties props = new Properties();
            try (InputStream input = DataSourceConfig.class.getClassLoader().getResourceAsStream("db.properties")) {
                props.load(input);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
            int processors = Runtime.getRuntime().availableProcessors();
            double systemLoadAverage = osBean.getSystemLoadAverage();
            int calculatedPoolSize = (int) Math.max(1, Math.ceil(processors * systemLoadAverage));
            hikariConfig.setMaximumPoolSize(calculatedPoolSize);
            hikariConfig.setMinimumIdle(Math.max(1, calculatedPoolSize / 2));
            hikariConfig.setDriverClassName(props.getProperty("driverClassName"));
            hikariConfig.setJdbcUrl(props.getProperty("jdbcUrl"));
            hikariConfig.setUsername(props.getProperty("username"));
            hikariConfig.setPassword(props.getProperty("password"));
            hikariConfig.setPoolName(props.getProperty("poolName"));
            hikariConfig.setIdleTimeout(Long.parseLong(props.getProperty("idleTimeout")));
            hikariConfig.setMaxLifetime(Long.parseLong(props.getProperty("maxLifetime")));

            dataSource = new HikariDataSource(hikariConfig);
        }
        return dataSource;
    }
}