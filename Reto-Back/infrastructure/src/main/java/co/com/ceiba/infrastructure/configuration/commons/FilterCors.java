package co.com.ceiba.infrastructure.configuration.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class FilterCors implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilterCors.class);

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;

        if (request.getHeader("Origin") != null ){
            response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        }
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("X-Frame-Options", "SAMEORIGIN");

        if (!("OPTIONS".equalsIgnoreCase(request.getMethod()))) {
            try {
                chain.doFilter(req, res);
            } catch(Exception e) {
                LOGGER.error("Ocurrió un error en el filtro de Cors", e);
            }
        } else {
            response.setHeader("Access-Control-Allow-Methods", "POST,GET,DELETE,PUT");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "authorization, content-type, access-control-request-headers," +
                    "access-control-request-method, accept,origin, authorization, x-requested-with, x-content-type-options, x-xss-protection, use-cache");
            response.setStatus(HttpServletResponse.SC_OK);
        }

    }

}
