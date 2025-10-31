package fazulzyanov.dao;

import fazulzyanov.entity.Student;
import fazulzyanov.entity.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    List<User> getAll();

    List<Student> getAllStudents();

    Map<Integer, String> getAllGroups();

    void save(User user);

    void addGroup(Integer id, String group);

    User getById(Integer id);

    User getByLogin(String login);

    String getGroup(Integer id);

    void update(Integer id, String name, String imagePath, String aboutInfo);

    void updateGroup(Integer id, String group);

    void delete(Integer id);

    void deleteGroup(Integer id);

    void updateRole(Integer id, String role);
}
