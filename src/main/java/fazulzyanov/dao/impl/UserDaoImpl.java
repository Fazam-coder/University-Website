package fazulzyanov.dao.impl;

import fazulzyanov.dao.UserDao;
import fazulzyanov.entity.Role;
import fazulzyanov.entity.User;
import fazulzyanov.util.DatabaseConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private final Connection connection = DatabaseConnectionUtil.getConnection();

    @Override
    public List<User> getAll() {
        String sql = "select * from users inner join roles on users.role_id = roles.id";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<User> users = new ArrayList<>();
            if (resultSet != null) {
                while (resultSet.next()) {
                    users.add(
                            new User(
                                    resultSet.getInt("id"),
                                    resultSet.getString("name"),
                                    resultSet.getString("login"),
                                    resultSet.getString("password"),
                                    resultSet.getString("image_path"),
                                    resultSet.getString("about_info"),
                                    getRole(resultSet.getString("role_name"))
                            )
                    );
                }
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(User user) {
        String sql = "insert into users (name, login, password, image_path, about_info) values (?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getImagePath());
            preparedStatement.setString(5, user.getAboutInfo());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getById(Integer id) throws IllegalArgumentException {
        String sql = "select * from users inner join roles on users.role_id = roles.id where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            return getUser(preparedStatement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getByLogin(String login) throws IllegalArgumentException {
        String sql = "select * from users inner join roles on users.role_id = roles.id where login = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);
            return getUser(preparedStatement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private User getUser(PreparedStatement preparedStatement) throws SQLException, IllegalArgumentException {
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return new User(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    resultSet.getString("image_path"),
                    resultSet.getString("about_info"),
                    getRole(resultSet.getString("role_name"))
            );
        }
        throw new IllegalArgumentException();
    }

    @Override
    public void update(String login, String name, String imagePath, String aboutInfo) {
        String sql = "update users set name = ?, image_path = ?, about_info = ? where login = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, imagePath);
            preparedStatement.setString(3, aboutInfo);
            preparedStatement.setString(4, login);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String login) {
        String sql = "delete users where login = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateRole(String login, String role) {
        String sql = "update users set role_id = (select roles.id from roles where role_name = ?) " +
                "where login = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, role);
            preparedStatement.setString(2, login);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Role getRole(String role) {
        return switch (role) {
            case "admin" -> Role.ADMIN;
            case "teacher" -> Role.TEACHER;
            case "student" -> Role.STUDENT;
            default -> Role.USER;
        };
    }
}
