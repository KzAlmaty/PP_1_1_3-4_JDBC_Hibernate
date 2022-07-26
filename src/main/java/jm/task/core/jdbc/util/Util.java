package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД



    private static final String Url = "jdbc:mysql://localhost:3306/mukashema";
    private static final String UserName = "root";
    private static final String Password = "mukagali";

    private static Connection connection;

    public static Connection getConnection() {

        try {

            connection = DriverManager.getConnection(Url, UserName, Password);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
