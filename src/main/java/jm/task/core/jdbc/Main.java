package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;



public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Ivan", "Ivanov", (byte) 25);
        System.out.println("User с именем Ivan добавлен в базу данных");

        userService.saveUser("Petr", "Petrov", (byte) 30);
        System.out.println("User с именем Petr добавлен в базу данных");

        userService.saveUser("Anna", "Sidorova", (byte) 22);
        System.out.println("User с именем Anna добавлен в базу данных");

        userService.saveUser("Oleg", "Smirnov", (byte) 28);
        System.out.println("User с именем Oleg добавлен в базу данных");

        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
