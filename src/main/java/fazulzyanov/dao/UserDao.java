package fazulzyanov.dao;

import fazulzyanov.entity.User;

import java.util.List;

public interface UserDao {
    List<User> getAll();

    void save(User user);

    User getById(Integer id);

    User getByLogin(String login);

    String getGroup(Integer id);

    void update(Integer id, String name, String imagePath, String aboutInfo);

    void delete(String login);

    void updateRole(Integer id, String role);
}
