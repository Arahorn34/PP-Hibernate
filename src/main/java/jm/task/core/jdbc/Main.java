package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Арсен", "Кочерян", (byte) 33);
        userService.saveUser("Дмитрий", "Дмитриев", (byte) 20);
        userService.saveUser("Иван", "Иванов", (byte) 25);
        userService.saveUser("Петр", "Петров", (byte) 33);
        userService.getAllUsers().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
