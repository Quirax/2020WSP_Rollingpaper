package com.quiraxical.rollingpaper;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteRPController extends Controller {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        // TODO: use user
        // if(user == null || user.getName().equals("")) {
        //     this.error(response, "login.jsp", "잘못된 접근입니다");
        //     return;
        // }

        // TODO: use RSA crypto

        DAO dao = DAO.getInstance();

        int id;

        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0;
        }

        if(!dao.deleteRollingpaper(user, id)) {
            this.error(response, "", "롤링페이퍼를 삭제할 수 없습니다. 관리자에게 문의하세요.");
            return;
        }

        response.sendRedirect("mypage.do");
    }    
}
