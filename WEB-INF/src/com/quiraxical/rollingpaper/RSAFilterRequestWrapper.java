package com.quiraxical.rollingpaper;

import javax.servlet.http.*;

public class RSAFilterRequestWrapper extends HttpServletRequestWrapper {
    public RSAFilterRequestWrapper(HttpServletRequest request) {
        super(request);

        RSA rsa = RSA.getInstance();
        rsa.init(request);
    }
}