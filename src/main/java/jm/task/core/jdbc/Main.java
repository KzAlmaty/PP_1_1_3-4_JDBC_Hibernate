package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Mukagali", "Orazbakov", (byte) 26);
        userService.saveUser("Test", "Testov", (byte) 30);
        userService.saveUser("Test2", "Testov2", (byte) 50);

        userService.removeUserById(1);

        List<User> listUsers = userService.getAllUsers();

        System.out.println(listUsers.size());
        for (User u : listUsers) {
            System.out.println(u);
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();

    }


}
