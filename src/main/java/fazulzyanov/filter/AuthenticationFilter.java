package fazulzyanov.filter;

import javax.servlet.*;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthenticationFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        if (session == null
                && !req.getRequestURI().contains("login")
                && !req.getRequestURI().contains("sign_up")
                && !req.getRequestURI().contains("/js/")
                && !req.getRequestURI().contains("/css/")) {
            res.sendRedirect(req.getContextPath() + "/login");
            return;
        } else {
            chain.doFilter(req, res);
        }

    }
}
