package fazulzyanov.services.impl;

import fazulzyanov.dto.UserDto;
import fazulzyanov.dao.UserDao;
import fazulzyanov.dao.impl.UserDaoImpl;
import fazulzyanov.entity.User;
import fazulzyanov.services.UserService;
import fazulzyanov.util.PasswordUtil;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDaoImpl();

    @Override
    public List<UserDto> getAll() {
        return userDao.getAll().stream()
                .map(user -> new UserDto(user.getName(), user.getLogin())).toList();
    }

    @Override
    public void save(String name, String lastname, String login, String password) {
        password = PasswordUtil.encrypt(password);
        // default value
        Integer id = 1;
        userDao.save(new User(id, name, lastname, login, password));
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
}
