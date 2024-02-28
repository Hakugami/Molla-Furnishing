package controller.listener;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import persistence.manager.DatabaseSingleton;
import util.HibernateUtil;

@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DatabaseSingleton.getInstance().getSessionFactory();
        sce.getServletContext().setAttribute("datasource", HibernateUtil.getHikariDataSource());

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        DatabaseSingleton.getInstance().getSessionFactory().close();
        HikariDataSource dataSource = (HikariDataSource) sce.getServletContext().getAttribute("datasource");
        if (dataSource != null) {
            dataSource.close();
        }
    }
}
