package com.quiraxical.rollingpaper;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
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
        if (user == null || user.getName().equals("")) {
            this.error(response, "login.jsp", "잘못된 접근입니다");
            return;
        }

        Rollingpaper rp = new Rollingpaper();
        rp.setTitle(request.getParameter("title"));
        rp.setTo(request.getParameter("to"));

        // TODO: use RSA Cryption

        DAO dao = DAO.getInstance();

        try {
            dao.createRollingpaper(user, rp, request.getParameter("pwd"));
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
            this.error(response, "", "롤링 페이퍼를 만드는 도중 오류가 발생하였습니다. 관리자에게 문의하세요.");
            return;
        }

        response.sendRedirect("mypage.do");
    }
}
