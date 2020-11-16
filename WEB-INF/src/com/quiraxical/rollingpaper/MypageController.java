package com.quiraxical.rollingpaper;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MypageController extends Controller {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null) {
            this.error(response, "login.jsp", "잘못된 접근입니다");
            return;
        }

        DAO dao = DAO.getInstance();
        ArrayList<Rollingpaper> paper = dao.getRollingpaperLists(user);
        request.setAttribute("rp", paper);

        this.forward(request, response, "my.jsp");
    }
}
