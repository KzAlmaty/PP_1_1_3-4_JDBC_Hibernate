package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static final String create = "CREATE TABLE IF NOT EXISTS users(" +
            "id LONG, " +
            "name VARCHAR(55)," +
            "lastName VARCHAR(55)," +
            "age INT)";

    private static final String saveUser = "INSERT INTO users VALUES(1, ?, ?, ?)";

    private static final String dropUser = "DROP TABLE IF EXISTS users";

    private static final String remove = "DELETE FROM users WHERE id = ?";

    private static final String getAllUsers = "SELECT * FROM users";

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {

        try (PreparedStatement pS = Util.getConnection().prepareStatement(create)){
            pS.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {

        try (PreparedStatement pS = Util.getConnection().prepareStatement(dropUser)){
            pS.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {

        try (PreparedStatement pS = Util.getConnection().prepareStatement(saveUser)){

            pS.setString(1, name);
            pS.setString(2, lastName);
            pS.setByte(3, age);

            pS.executeUpdate();

            System.out.println("User: " + name + " saved to DB!");

        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    public void removeUserById(long id) {

        try (PreparedStatement pS = Util.getConnection().prepareStatement(remove)){

            pS.setLong(1, id);
            pS.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {

        List<User> list = new ArrayList<>();

        try (PreparedStatement pS = Util.getConnection().prepareStatement(getAllUsers)){

            ResultSet rS = pS.executeQuery();

            while (rS.next()) {
                User user = new User();
                user.setId(rS.getLong("id"));
                user.setName(rS.getString("name"));
                user.setLastName(rS.getString("lastName"));
                user.setAge(rS.getByte("age"));
                list.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;

    }

    public void cleanUsersTable() {

        String cleanUsersTable = "TRUNCATE TABLE users";

        try (PreparedStatement pS = Util.getConnection().prepareStatement(cleanUsersTable)){

            pS.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
