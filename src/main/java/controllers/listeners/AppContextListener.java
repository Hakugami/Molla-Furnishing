package controllers.listeners;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import persistence.manager.DatabaseSingleton;

@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DatabaseSingleton.getInstance();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        DatabaseSingleton.getInstance().closeEntityManagerFactory();
        AbandonedConnectionCleanupThread.checkedShutdown();
    }
}