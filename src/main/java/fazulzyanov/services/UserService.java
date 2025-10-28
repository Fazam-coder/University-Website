package fazulzyanov.services;

import fazulzyanov.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();

    void save(String name, String login, String password);

    boolean verifyUser(String login, String password);

    boolean existsLogin(String login);

    void update(String login, String name, String imagePath, String aboutInfo);

    void delete(String login);
}
