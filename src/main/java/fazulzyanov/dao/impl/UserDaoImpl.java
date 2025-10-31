package fazulzyanov.dao.impl;

import fazulzyanov.dao.UserDao;
import fazulzyanov.entity.Role;
import fazulzyanov.entity.Student;
import fazulzyanov.entity.User;
import fazulzyanov.util.DatabaseConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<Student> getAllStudents() {
        String sql = "select * from users " +
                "inner join roles on users.role_id = roles.id " +
                "left join students on users.id = students.user_id " +
                "where role_name = 'student'";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<Student> students = new ArrayList<>();
            if (resultSet != null) {
                while (resultSet.next()) {
                    Student student = new Student(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("login"),
                            resultSet.getString("password"),
                            resultSet.getString("image_path"),
                            resultSet.getString("about_info"),
                            getRole(resultSet.getString("role_name"))
                    );
                    if (resultSet.getString("group_name") != null) {
                        student.setGroup(resultSet.getString("group_name"));
                    }
                    students.add(student);
                }
            }
            return students;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<Integer, String> getAllGroups() {
        String sql = "select * from students";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            Map<Integer, String> groups = new HashMap<>();
            if (resultSet != null) {
                while (resultSet.next()) {
                    groups.put(resultSet.getInt("user_id"), resultSet.getString("group_name"));
                }
            }
            return groups;
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
    public void addGroup(Integer id, String group) {
        String sql = "insert into students (user_id, group_name) values (?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, group);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateGroup(Integer id, String group) {
        String sql = "update students set group_name = ? where user_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, group);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteGroup(Integer id) {
        String sql = "delete from students where user_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getById(Integer id) throws IllegalArgumentException {
        String sql = "select * from users inner join roles on users.role_id = roles.id where users.id = ?";
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

    @Override
    public String getGroup(Integer id) {
        String sql = "select group_name from students where user_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                return resultSet.getString("group_name");
            } else {
                return "";
            }
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
    public void update(Integer id, String name, String imagePath, String aboutInfo) {
        String sql = "update users set name = ?, image_path = ?, about_info = ? where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, imagePath);
            preparedStatement.setString(3, aboutInfo);
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "delete from users where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateRole(Integer id, String role) {
        String sql = "update users set role_id = (select roles.id from roles where role_name = ?) " +
                "where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, role.toLowerCase());
            preparedStatement.setInt(2, id);
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
