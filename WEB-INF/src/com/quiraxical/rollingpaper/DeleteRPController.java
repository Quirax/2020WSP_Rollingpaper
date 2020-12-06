package com.quiraxical.rollingpaper;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteRPController extends Controller {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user;
        try {
            user = getUser(session, response);
        } catch (Exception e1) {
            return;
        }

        DAO dao = DAO.getInstance();
        boolean result = false;
        int id;

        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0;
        }

        try {
            result = dao.deleteRollingpaper(user, id);
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }

        if(!result) {
            this.error(response, "", "롤링페이퍼를 삭제할 수 없습니다. 관리자에게 문의하세요.");
            return;
        }

        response.sendRedirect("mypage.do");
    }    
}
