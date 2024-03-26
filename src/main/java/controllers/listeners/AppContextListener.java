package controllers.listeners;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import persistence.manager.DatabaseSingleton;
import services.JWTService;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DatabaseSingleton.getInstance().init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        DatabaseSingleton.getInstance().closeEntityManagerFactory();

        // Unregister JDBC driver
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        AbandonedConnectionCleanupThread.uncheckedShutdown();
    }
}
