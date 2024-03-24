package utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.Properties;
import java.util.logging.Logger;

public class DataSourceConfig {
    private static final Properties props = new Properties();
    private static final int processors;
    private static final double systemLoadAverage;
    private static HikariDataSource dataSource = null;
    private static boolean isDataSourceCreated = false;


    static {
        try (InputStream input = DataSourceConfig.class.getClassLoader().getResourceAsStream("db.properties")) {
            props.load(input);
        } catch (IOException ex) {
            Logger.getGlobal().severe("Error loading db.properties file" + ex.getMessage());
        }
        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        processors = Runtime.getRuntime().availableProcessors();
        systemLoadAverage = osBean.getSystemLoadAverage();
    }

    public static HikariDataSource getHikariDataSource() {
        if (isDataSourceCreated) {
            throw new IllegalStateException("HikariDataSource has already been created");
        }
        if (dataSource == null) {
            HikariConfig hikariConfig = new HikariConfig();

            int calculatedPoolSize = calculatePoolSize();
            int minimumIdle = calculateMinimumIdle(calculatedPoolSize);

            hikariConfig.setMaximumPoolSize(calculatedPoolSize);
            hikariConfig.setMinimumIdle(minimumIdle);
            hikariConfig.setDriverClassName(props.getProperty("driverClassName"));
            hikariConfig.setJdbcUrl(props.getProperty("jdbcUrl"));
            hikariConfig.setUsername(props.getProperty("username"));
            hikariConfig.setPassword(props.getProperty("password"));
            hikariConfig.setPoolName(props.getProperty("poolName"));
            hikariConfig.setIdleTimeout(Long.parseLong(props.getProperty("idleTimeout")));
            hikariConfig.setMaxLifetime(Long.parseLong(props.getProperty("maxLifetime")));

            dataSource = new HikariDataSource(hikariConfig);
            isDataSourceCreated = true;
        }
        return dataSource;
    }

    private static int calculatePoolSize() {
        if (systemLoadAverage == -1.0) {
            return processors;
        } else {
            return (int) Math.max(1, Math.ceil(processors * systemLoadAverage));
        }
    }

    private static int calculateMinimumIdle(int calculatedPoolSize) {
        int minimumIdle = Math.max(1, calculatedPoolSize / 2);
        if (minimumIdle == calculatedPoolSize) {
            minimumIdle--;
        }
        return minimumIdle;
    }
}