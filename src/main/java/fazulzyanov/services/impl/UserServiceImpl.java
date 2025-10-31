package fazulzyanov.services.impl;

import fazulzyanov.dto.StudentDto;
import fazulzyanov.dto.UserDto;
import fazulzyanov.dao.UserDao;
import fazulzyanov.entity.Role;
import fazulzyanov.entity.User;
import fazulzyanov.services.UserService;
import fazulzyanov.util.PasswordUtil;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<UserDto> getAll() {
        return userDao.getAll().stream()
                .map(user -> new UserDto(user.getId(), user.getName(), user.getLogin(), user.getImagePath(),
                        user.getAboutInfo(), user.getRole()))
                .toList();
    }

    @Override
    public UserDto getById(Integer id) {
        User user = userDao.getById(id);
        if (user == null) {
            throw new IllegalArgumentException();
        }
        return new UserDto(user.getId(), user.getName(), user.getLogin(),
                user.getImagePath(), user.getAboutInfo(), user.getRole());
    }

    @Override
    public UserDto getByLogin(String login) {
        User user = userDao.getByLogin(login);
        if (user == null) {
            throw new IllegalArgumentException();
        }
        return new UserDto(user.getId(), user.getName(), user.getLogin(),
                user.getImagePath(), user.getAboutInfo(), user.getRole());
    }

    @Override
    public String getGroup(Integer id) {
        return userDao.getGroup(id);
    }

    @Override
    public void save(String name, String login, String password) {
        password = PasswordUtil.encrypt(password);
        // default value
        Integer id = 1;
        userDao.save(new User(id, name, login, password));
    }

    @Override
    public void saveGroups(Map<Integer, String> newGroups) {
        Map<Integer, String> oldGroups = userDao.getAllGroups();
        for (Map.Entry<Integer, String> entry : newGroups.entrySet()) {
            Integer id = entry.getKey();
            String group = entry.getValue();
            if (oldGroups.containsKey(id) && !oldGroups.get(id).equals(group)) {
                if (group == null || group.isEmpty()) {
                    userDao.deleteGroup(id);
                } else {
                    userDao.updateGroup(id, group);
                }
            } else if (!oldGroups.containsKey(id)) {
                userDao.addGroup(id, group);
            }
        }
    }

    @Override
    public void update(Integer id, String name, String imagePath, String aboutInfo) {
        userDao.update(id, name, imagePath, aboutInfo);
    }

    @Override
    public void updateRole(Integer id, Role role) {
        userDao.updateRole(id, role.name());
    }

    @Override
    public void delete(Integer id) {
        userDao.delete(id);
    }

    @Override
    public boolean verifyUser(String login, String password) {
        try {
            User user = userDao.getByLogin(login);
            return user.getPassword().equals(PasswordUtil.encrypt(password));
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public boolean existsLogin(String login) {
        try {
            userDao.getByLogin(login);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public List<StudentDto> getAllStudents() {
        return userDao.getAllStudents().stream()
                .map(s -> new StudentDto(s.getId(), s.getName(), s.getLogin(), s.getImagePath(),
                        s.getAboutInfo(), s.getRole(), s.getGroup()))
                .sorted(Comparator.comparing(StudentDto::getName))
                .toList();
    }

    @Override
    public List<UserDto> getAllTeachers() {
        return getAll().stream()
                .filter(u -> u.getRole() == Role.TEACHER)
                .sorted(Comparator.comparing(UserDto::getName))
                .toList();
    }

    @Override
    public Role getRole(String login) {
        return userDao.getByLogin(login).getRole();
    }
}
