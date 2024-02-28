package persistence.manager;


import org.hibernate.SessionFactory;
import org.hibernate.Session;
import util.HibernateUtil;

public class DatabaseSingleton {
    private static volatile DatabaseSingleton instance = null;
    private static volatile SessionFactory sessionFactory = null;
    private static final ThreadLocal<Session> threadLocalSession = new ThreadLocal<>();

    private DatabaseSingleton() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public static DatabaseSingleton getInstance() {
        if (instance == null) {
            synchronized (DatabaseSingleton.class) {
                if (instance == null) {
                    instance = new DatabaseSingleton();
                }
            }
        }
        return instance;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Session getSession() {
        Session session = threadLocalSession.get();
        if (session == null) {
            session = sessionFactory.openSession();
            threadLocalSession.set(session);
        }
        return session;
    }

    public void closeSession() {
        Session session = threadLocalSession.get();
        if (session != null) {
            session.close();
            threadLocalSession.remove();
        }
    }
}