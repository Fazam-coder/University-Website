package fazulzyanov.dao;

import fazulzyanov.entity.User;

import java.util.List;

public interface UserDao {
    List<User> getAll();

    void save(User user);

    User getById(Integer id);

    User getByLogin(String login);

    void update(String login, String name, String imagePath, String aboutInfo);

    void delete(String login);
}
