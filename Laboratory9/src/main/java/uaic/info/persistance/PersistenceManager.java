package uaic.info.persistance;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PersistenceManager {
    private static PersistenceManager instance = null;
    private EntityManagerFactory emFactory;

    private PersistenceManager() {
        emFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
    }

    public static PersistenceManager getInstance() {
        if (instance == null) {
            instance = new PersistenceManager();
        }
        return instance;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return emFactory;
    }

    public void closeEntityManagerFactory() {
        if (emFactory != null && emFactory.isOpen()) {
            emFactory.close();
        }
    }
}
