package university.filter;

import university.entity.Role;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/teacher/*")
public class TeacherFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        Role userRole = (Role) session.getAttribute("role");
        if (Role.TEACHER != userRole && Role.ADMIN != userRole) {
            res.sendError(HttpServletResponse.SC_FORBIDDEN, "Недостаточно прав"); // 403
        } else {
            chain.doFilter(req, res);
        }
    }
}
