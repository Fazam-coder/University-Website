package university.listener;

import university.dao.LessonScoreDao;
import university.dao.UserDao;
import university.dao.impl.LessonScoreDaoImpl;
import university.dao.impl.UserDaoImpl;
import university.services.LessonScoreService;
import university.services.UserService;
import university.services.impl.LessonScoreServiceImpl;
import university.services.impl.UserServiceImpl;

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
