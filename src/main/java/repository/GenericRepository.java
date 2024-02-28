package repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import persistence.manager.DatabaseSingleton;

public abstract class GenericRepository <T, ID>{

    protected Transaction transaction;

    public boolean create(T t) {
        Session session = DatabaseSingleton.getInstance().getSession();
        transaction = session.beginTransaction();
        session.persist(t);
        transaction.commit();
        return true;
    }

    public T read(ID id) {
        Session session = DatabaseSingleton.getInstance().getSession();
        transaction = session.beginTransaction();
        T t = session.get((Class<T>) this.getClass(), id);
        transaction.commit();
        return t;
    }

    public boolean update(T t) {
        Session session = DatabaseSingleton.getInstance().getSession();
        transaction = session.beginTransaction();
        session.merge(t);
        transaction.commit();
        return true;
    }

    public void delete(ID id) {
        Session session = DatabaseSingleton.getInstance().getSession();
        transaction = session.beginTransaction();
        T t = session.get((Class<T>) this.getClass(), id);
        session.remove(t);
        transaction.commit();
    }

}
