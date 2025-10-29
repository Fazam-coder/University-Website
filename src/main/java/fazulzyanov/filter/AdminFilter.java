package fazulzyanov.filter;

import fazulzyanov.entity.Role;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/admin/*", filterName = "IsAdmin")
public class AdminFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        Role userRole = (Role) session.getAttribute("role");
        if (Role.ADMIN == userRole) {
            res.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403
        } else {
            chain.doFilter(req, res);
        }
    }
}
