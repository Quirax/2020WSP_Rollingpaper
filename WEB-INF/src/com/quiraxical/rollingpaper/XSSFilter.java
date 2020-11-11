package com.quiraxical.rollingpaper;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class XSSFilter implements Filter {
    private FilterConfig filterConfig;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void destroy() {
        this.filterConfig = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        chain.doFilter(new XSSFilterRequestWrapper((HttpServletRequest) request), response);
    }
}
