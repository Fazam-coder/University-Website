package fazulzyanov.main;

import fazulzyanov.dao.UserDao;
import fazulzyanov.dao.impl.UserDaoImpl;
import fazulzyanov.services.UserService;
import fazulzyanov.services.impl.UserServiceImpl;

public class Main {
    private static final UserDao userDao = new UserDaoImpl();
    private static final UserService userService = new UserServiceImpl();

    public static UserDao getUserDao() {
        return userDao;
    }

    public static UserService getUserService() {
        return userService;
    }
}
