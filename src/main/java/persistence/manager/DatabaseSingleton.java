package persistence.manager;

public class DatabaseSingleton {
    private static volatile DatabaseSingleton instance = null;

    private DatabaseSingleton() {
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


}
