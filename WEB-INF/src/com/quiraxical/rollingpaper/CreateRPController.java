package com.quiraxical.rollingpaper;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateRPController extends Controller {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null) {
            this.error(response, "login.jsp", "잘못된 접근입니다");
            return;
        }
        
        Rollingpaper rp = new Rollingpaper();
        rp.setTitle(request.getParameter("title"));
        rp.setTo(request.getParameter("to"));

        //TODO: use RSA Cryption

        DAO dao = DAO.getInstance();
        dao.createRollingpaper(user, rp, request.getParameter("pwd"));

        response.sendRedirect("mypage.do");
    }
}
