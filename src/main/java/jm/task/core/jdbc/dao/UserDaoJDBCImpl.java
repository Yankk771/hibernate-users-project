package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
       try {
           Connection conn = Util.getConnection();
           Statement stmt = conn.createStatement();
           stmt.execute("CREATE TABLE IF NOT EXISTS users (" +
                   "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                   "name VARCHAR(255), " +
                   "lastName VARCHAR(255), " +
                   "age TINYINT" +
                   ")");
       } catch (SQLException e) {
           e.printStackTrace();
       }

    }

    public void dropUsersTable() {
        try {
            Connection conn = Util.getConnection();
            Statement stmt = conn.createStatement();

            stmt.execute("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            Connection conn = Util.getConnection();
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO users (name, lastName, age) VALUES ('"
                    + name + "', '"
                    + lastName + "', "
                    + age + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            Connection conn = Util.getConnection();
            Statement stmt = conn.createStatement();
            stmt.execute("DELETE  FROM users WHERE id = " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Connection conn = Util.getConnection();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT  * FROM users");


            while (rs.next()) {
                User user = new User();
                long id = rs.getLong("id");
                user.setId(id);
                String name = rs.getString("name");
                user.setName(name);
                String lastName = rs.getString("lastName");
                user.setLastName(lastName);
                byte age = rs.getByte("age");
                user.setAge(age);

                users.add(user);


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try {
            Connection conn = Util.getConnection();
            Statement stmt = conn.createStatement();

            stmt.execute("DELETE FROM users");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
