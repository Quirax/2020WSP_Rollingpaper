package com.quiraxical.rollingpaper;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class XSSFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        chain.doFilter(new XSSFilterRequestWrapper((HttpServletRequest) request), response);
    }

    public void destroy() {
    }
}
