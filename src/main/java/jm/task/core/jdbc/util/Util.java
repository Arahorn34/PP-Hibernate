package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String DATA = "jdbc:mysql://localhost:3306/test";
    private static final String USER = "Ars";
    private static final String PASSWORD = "1234";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings.put(AvailableSettings.DRIVER, DRIVER);
                settings.put(AvailableSettings.URL, DATA);
                settings.put(AvailableSettings.USER, USER);
                settings.put(AvailableSettings.PASS, PASSWORD);
                settings.put(AvailableSettings.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(AvailableSettings.SHOW_SQL, "true");
                settings.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(AvailableSettings.HBM2DDL_AUTO, "create-drop");
                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (HibernateException e) {
                throw new RuntimeException(e);
            }
        }
        return sessionFactory;
    }

    public Connection getConnection() throws RuntimeException {
        Connection connection = null;

        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DATA, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
