package fazulzyanov.services;

import fazulzyanov.dto.UserDto;
import fazulzyanov.entity.Role;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();

    UserDto getById(Integer id);

    UserDto getByLogin(String login);

    String getGroup(Integer id);

    void save(String name, String login, String password);

    boolean verifyUser(String login, String password);

    boolean existsLogin(String login);

    void update(Integer id, String name, String imagePath, String aboutInfo);

    void updateRole(Integer id, Role role);

    void delete(String login);

    List<UserDto> getAllStudents();

    List<UserDto> getAllTeachers();

    Role getRole(String login);
}
