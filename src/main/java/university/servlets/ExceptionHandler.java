package university.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/handle")
public class ExceptionHandler extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handle(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handle(req, resp);
    }

    private void handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Throwable throwable = (Throwable) req.getAttribute("javax.servlet.error.exception");
        Integer code = (Integer) req.getAttribute("javax.servlet.error.status_code");
        String uri = (String) req.getAttribute("javax.servlet.error.request_uri");

        req.setAttribute("statusCode", code);
        req.setAttribute("uri", uri == null ? "" : uri);
        req.setAttribute("contextPath", req.getContextPath());
        if (throwable != null) {
            req.setAttribute("message", throwable.getMessage());
        } else if (code == 404) {
            req.setAttribute("message", "Страница не найдена");
        } else {
            req.setAttribute("message", req.getAttribute("javax.servlet.error.message"));
        }

        req.getRequestDispatcher("/WEB-INF/view/exception.ftl").forward(req, resp);
    }
}
