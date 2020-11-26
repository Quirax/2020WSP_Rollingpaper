package com.quiraxical.rollingpaper;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PartController extends Controller {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null || user.getName().equals("")) {
            this.error(response, "login.jsp", "잘못된 접근입니다");
            return;
        }

        //use RSA Crypto
        DAO dao = DAO.getInstance();
        if(!dao.deleteUser(user, request.getParameter("pwd"))) {
            this.error(response, "mypage.do", "탈퇴 처리 도중 문제가 발생하였습니다.\n관리자에게 문의하세요.");
            return;
        }

        session.setAttribute("user", null);

        this.forward(request, response, "index.jsp");
    }
}
