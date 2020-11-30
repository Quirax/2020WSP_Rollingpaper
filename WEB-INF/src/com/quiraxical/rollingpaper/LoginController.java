package com.quiraxical.rollingpaper;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class LoginController extends Controller {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAO dao = DAO.getInstance();
        // TODO: password는 복호화 거쳐서
        User user;

        try {
            user = dao.findUser(request.getParameter("name"), request.getParameter("pwd"));
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
            user = null;
        }

        if (user == null) {
            this.error(response, "login.jsp", "아이디 또는 비밀번호가 잘못되었습니다.");
            return;
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        response.sendRedirect("mypage.do");
    }
}
