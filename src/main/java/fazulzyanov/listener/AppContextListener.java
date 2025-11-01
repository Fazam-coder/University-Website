package fazulzyanov.listener;

import fazulzyanov.dao.LessonScoreDao;
import fazulzyanov.dao.UserDao;
import fazulzyanov.dao.impl.LessonScoreDaoImpl;
import fazulzyanov.dao.impl.UserDaoImpl;
import fazulzyanov.services.LessonScoreService;
import fazulzyanov.services.UserService;
import fazulzyanov.services.impl.LessonScoreServiceImpl;
import fazulzyanov.services.impl.UserServiceImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        UserDao userDao = new UserDaoImpl();
        UserService userService = new UserServiceImpl(userDao);
        sce.getServletContext().setAttribute("userService", userService);
        LessonScoreDao lessonScoreDao = new LessonScoreDaoImpl();
        LessonScoreService lessonScoreService = new LessonScoreServiceImpl(lessonScoreDao);
        sce.getServletContext().setAttribute("lessonScoreService", lessonScoreService);
    }
}
