package fazulzyanov.servlets;

import fazulzyanov.dto.UserDto;
import fazulzyanov.entity.Role;
import fazulzyanov.services.FileService;
import fazulzyanov.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet("/profile/edit")
@MultipartConfig(
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 10
)
public class EditProfileServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() {
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID не указан");
            return;
        }
        try {
            Integer userId = Integer.parseInt(idParam);
            UserDto user = userService.getById(userId);
            if (!user.getLogin().equals(req.getSession().getAttribute("user").toString())
                    && !(req.getSession().getAttribute("role") == Role.ADMIN)) {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Недостаточно прав");
            }
            req.setAttribute("user", user);
            req.setAttribute("contextPath", req.getContextPath());
            req.getRequestDispatcher("/edit_profile.ftl").forward(req, resp);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Неверный формат ID");
        } catch (IllegalArgumentException e) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Пользователь не найден");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userId = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        Part part = req.getPart("image");
        String imagePath = "";
        if (part != null && part.getSize() != 0) {
            imagePath = FileService.saveAndGetPathname(part);
        }
        String aboutInfo = req.getParameter("aboutInfo");
        userService.update(userId, name, imagePath, aboutInfo);
        resp.sendRedirect(req.getContextPath() + "/profile?id=" + userId);
    }
}
