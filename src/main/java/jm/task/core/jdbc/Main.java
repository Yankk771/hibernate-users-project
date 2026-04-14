package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();

        userDao.saveUser("Ivan", "Ivanov", (byte) 25);
        System.out.println("User с именем Ivan добавлен в базу данных");

        userDao.saveUser("Petr", "Petrov", (byte) 30);
        System.out.println("User с именем Petr добавлен в базу данных");

        userDao.saveUser("Anna", "Sidorova", (byte) 22);
        System.out.println("User с именем Anna добавлен в базу данных");

        userDao.saveUser("Oleg", "Smirnov", (byte) 28);
        System.out.println("User с именем Oleg добавлен в базу данных");

        System.out.println(userDao.getAllUsers());

        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
