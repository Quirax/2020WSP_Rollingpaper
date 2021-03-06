package com.quiraxical.rollingpaper;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CloseRPController extends Controller {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user;
        try {
            user = getUser(session, response);
        } catch (Exception e1) {
            return;
        }

        Rollingpaper paper = (Rollingpaper) session.getAttribute("rp");
        DAO dao = DAO.getInstance();

        int id;

        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0;
        }

        try {
            if (paper == null || (id > 0 && paper.getId() != id)) dao.closeRollingpaper(user, id);
            else dao.closeRollingpaper(user, paper);
        } catch (Exception e) {
            e.printStackTrace();
            this.error(response, "", "롤링페이퍼를 마감할 수 없습니다. 관리자에게 문의하세요.");
            return;
        }

        session.setAttribute("rp", null);

        response.sendRedirect("mypage.do");
    }
}
