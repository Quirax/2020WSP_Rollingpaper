package com.quiraxical.rollingpaper;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class LoginController extends Controller {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAO dao = DAO.getInstance();
        RSA rsa = RSA.getInstance();
        User user;

        try {
            user = dao.findUser(request.getParameter("name"), rsa.decrypt(request.getParameter("pwd"), request));
        } catch (Exception e) {
            e.printStackTrace();
            user = null;
        }

        if (user == null) {
            this.error(response, "login.jsp", "아이디 또는 비밀번호가 잘못되었습니다.");
            return;
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        rsa.destory(request);

        response.sendRedirect("mypage.do");
    }
}
