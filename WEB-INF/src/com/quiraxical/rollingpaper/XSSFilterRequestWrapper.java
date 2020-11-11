package com.quiraxical.rollingpaper;

import javax.servlet.http.*;

public class XSSFilterRequestWrapper extends HttpServletRequestWrapper {
    public XSSFilterRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if(values == null) return null;

        for(int i = 0; i < values.length; i++) {
            values[i] = filterXSS(values[i]);
        }

        return values;
    }

    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        if(value == null) return null;
        return filterXSS(value);
    }

    @Override
    public String getHeader(String name) {
        String header = super.getHeader(name);
        if(header == null) return null;
        return filterXSS(header);
    }

    private String filterXSS(String str) {
        return str.replaceAll("<", "&lt;").replaceAll(">", "&gt;")
            .replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;")
            .replaceAll("'", "&#39;")
            //.replaceAll("eval\\((.*)\\)", "")
            .replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
            //.replaceAll("script", "");
    }
}
