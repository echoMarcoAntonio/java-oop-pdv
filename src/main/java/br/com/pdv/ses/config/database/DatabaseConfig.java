package br.com.pdv.ses.config.database;

import java.util.Map;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DatabaseConfig {
    private static EntityManagerFactory emf;

    public static void init(Map<String, Object> props) {
        emf = Persistence.createEntityManagerFactory("pdv-pu", props);
    }

    public static EntityManager getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("pdv-pu");
        }
        return emf.createEntityManager();
    }

    public static void fechar() {
        if (emf != null && emf.isOpen())
            emf.close();
    }
}
