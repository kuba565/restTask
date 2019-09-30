package pl.kuba565.resttask.filter;

import com.auth0.jwt.JWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static pl.kuba565.resttask.util.StringUtil.CAR;

public class AuthFilter implements Filter {
    private static final Logger LOG = LoggerFactory.getLogger(AuthFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        LOG.info("Logging Request  {} : {}", request.getMethod(), request.getRequestURI());

        if (request.getRequestURI().equals(CAR)) {
            if (request.getParameter("token") == null) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication Failed - no token!");
            } else if (!JWT.decode(request.getParameter("token")).getIssuer().equals("admin")) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication Failed - only admin can use this endpoint!");
            }
        }

        //call next filter in the filter chain
        filterChain.doFilter(request, response);

        LOG.info("Logging Response :{}", response.getContentType());
    }
}
