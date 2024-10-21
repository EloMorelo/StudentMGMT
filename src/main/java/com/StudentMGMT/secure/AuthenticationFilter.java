package com.StudentMGMT.secure;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.StudentMGMT.entities.User;


@WebFilter("/*")
public class AuthenticationFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        String requestURI = req.getRequestURI();

        if (requestURI.endsWith("/login") || 
            requestURI.endsWith("/register") || 
            requestURI.startsWith(req.getContextPath() + "/css/") || 
            requestURI.startsWith(req.getContextPath() + "/js/") || 
            requestURI.startsWith(req.getContextPath() + "/images/")) {
            chain.doFilter(request, response);
        } else {
            if (session != null && session.getAttribute("user") != null) {
                chain.doFilter(request, response); 
            } else {
                res.sendRedirect(req.getContextPath() + "/login");
            }
        }
    }
}
