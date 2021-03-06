package com.quiraxical.rollingpaper;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginPageController extends Controller {
    private int mode = 0;

    public LoginPageController(int mode) {
        this.mode = mode;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch(mode) {
            case 2:
            case 4:
                try {
                    getUser(request, response);
                } catch (Exception e) {
                    return;
                }
                break;
        }

        request.setAttribute("mode", mode);
        
        this.forward(request, response, "login.jsp");
    }
}
