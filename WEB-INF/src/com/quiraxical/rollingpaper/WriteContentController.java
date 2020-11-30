package com.quiraxical.rollingpaper;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class WriteContentController extends Controller {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            this.error(response, "login.jsp", "잘못된 접근입니다");
            return;
        }

        RollingpaperContent content = new RollingpaperContent();
        content.setFrom(request.getParameter("from"));
        content.setText(request.getParameter("text"));

        Rollingpaper paper = (Rollingpaper) session.getAttribute("rp");
        if (paper == null) {
            this.error(response, "index.jsp", "잘못된 접근입니다");
            return;
        }

        DAO dao = DAO.getInstance();
        // TODO; use RSA Crypto

        try {
            dao.createRollingpaperContent(paper, content, request.getParameter("pwd"));
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("rollingpaper.do");
    }
}
