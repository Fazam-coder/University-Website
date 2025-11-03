package university.services;

import university.dto.StudentDto;
import university.dto.UserDto;
import university.entity.Role;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<UserDto> getAll();

    UserDto getById(Integer id);

    UserDto getByLogin(String login);

    String getGroup(Integer id);

    void save(String name, String login, String password);

    void saveGroups(Map<Integer, String> groups);

    boolean verifyUser(String login, String password);

    boolean existsLogin(String login);

    boolean existsGroup(String group);

    boolean existsTeacher(String teacher);

    void update(Integer id, String name, String imagePath, String aboutInfo);

    void updateRole(Integer id, Role role);

    void delete(Integer id);

    List<StudentDto> getAllStudents();

    List<UserDto> getAllTeachers();

    Role getRole(String login);
}
