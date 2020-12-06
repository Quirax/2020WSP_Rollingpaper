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
        User user;
        try {
            user = getUser(session, response);
        } catch (Exception e1) {
            return;
        }

        RSA rsa = RSA.getInstance();
        DAO dao = DAO.getInstance();
        boolean result = false;

        try {
            result = dao.deleteUser(user, rsa.decrypt(request.getParameter("pwd"), request));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!result) {
            this.error(response, "mypage.do", "탈퇴 처리 도중 문제가 발생하였습니다.\n관리자에게 문의하세요.");
            return;
        }

        session.setAttribute("user", null);

        this.forward(request, response, "index.jsp");
    }
}
