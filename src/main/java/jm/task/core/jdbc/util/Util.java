package jm.task.core.jdbc.util;
import  java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/jm_task";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";
    private static  SessionFactory sessionFactory;

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            Configuration configuration = new Configuration();

            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }

}
