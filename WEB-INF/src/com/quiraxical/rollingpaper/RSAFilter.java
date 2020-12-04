package com.quiraxical.rollingpaper;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

public class RSAFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        chain.doFilter(new RSAFilterRequestWrapper((HttpServletRequest) request), response);
    }

    public void destroy() {
    }
}
