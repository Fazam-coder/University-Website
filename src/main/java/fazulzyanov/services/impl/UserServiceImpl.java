package fazulzyanov.services.impl;

import fazulzyanov.dto.UserDto;
import fazulzyanov.dao.UserDao;
import fazulzyanov.entity.Role;
import fazulzyanov.entity.User;
import fazulzyanov.services.UserService;
import fazulzyanov.util.PasswordUtil;

import java.util.Comparator;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<UserDto> getAll() {
        return userDao.getAll().stream()
                .map(user -> new UserDto(user.getName(), user.getLogin(), user.getImagePath(),
                        user.getAboutInfo(), user.getRole()))
                .toList();
    }

    @Override
    public void save(String name, String login, String password) {
        password = PasswordUtil.encrypt(password);
        // default value
        Integer id = 1;
        userDao.save(new User(id, name, login, password));
    }

    @Override
    public void update(String login, String name, String imagePath, String aboutInfo) {
        userDao.update(login, name, imagePath, aboutInfo);
    }

    @Override
    public void delete(String login) {
        userDao.delete(login);
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
    public List<UserDto> getAllStudents() {
        return getAll().stream()
                .filter(u -> u.getRole() == Role.STUDENT)
                .sorted(Comparator.comparing(UserDto::getName))
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
