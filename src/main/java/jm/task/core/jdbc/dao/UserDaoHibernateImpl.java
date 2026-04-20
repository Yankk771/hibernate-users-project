package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Transaction transaction = null;

        try (var session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.createSQLQuery(
                    "CREATE TABLE IF NOT EXISTS users (" +
                            "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                            "name VARCHAR(255), " +
                            "lastName VARCHAR(255), " +
                            "age TINYINT)"
            ).executeUpdate();

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        var session = sessionFactory.openSession();
        session.beginTransaction();

        session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        var session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(new User(name, lastName, age));





        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void removeUserById(long id) {
        var session = sessionFactory.openSession();
        session.beginTransaction();

        User user = session.get(User.class, id);
        session.delete(user);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        var session = sessionFactory.openSession();
        session.beginTransaction();

        List<User> users = session.createQuery("from User", User.class).getResultList();

        session.getTransaction().commit();
        session.close();

        return users;
    }

    @Override
    public void cleanUsersTable() {
        var session = sessionFactory.openSession();
        session.beginTransaction();

        session.createSQLQuery("DELETE FROM users").executeUpdate();
        session.getTransaction().commit();

        session.close();

    }
}
