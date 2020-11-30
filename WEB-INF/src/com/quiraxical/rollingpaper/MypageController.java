package com.quiraxical.rollingpaper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MypageController extends Controller {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null || user.getName().equals("")) {
            this.error(response, "login.jsp", "잘못된 접근입니다");
            return;
        }

        DAO dao = DAO.getInstance();

        ArrayList<Rollingpaper> paper;

        try {
            paper = dao.getRollingpaperLists(user);
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
            this.error(response, "", "페이지를 로드하는 도중 오류가 발생하였습니다. 관리자에게 문의하세요.");
            return;
        }

        session.setAttribute("rpl", paper);

        this.forward(request, response, "my.jsp");
    }
}
