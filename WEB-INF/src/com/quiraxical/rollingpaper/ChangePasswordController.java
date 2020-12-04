package com.quiraxical.rollingpaper;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangePasswordController extends Controller {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null || user.getName().equals("")) {
            this.error(response, "login.jsp", "잘못된 접근입니다");
            return;
        }

        RSA rsa = RSA.getInstance();

        Rollingpaper paper = (Rollingpaper) session.getAttribute("rp");

        if (paper == null) {
            this.error(response, "index.jsp", "잘못된 접근입니다");
            return;
        }

        DAO dao = DAO.getInstance();

        try {
            paper = dao.changeRollingpaperPassword(user, paper, rsa.decrypt(request.getParameter("pwd"), request));
        } catch (Exception e) {
            e.printStackTrace();
            paper = null;
        }
        
        if(paper == null) {
            this.error(response, "", "비밀번호를 바꿀 수 없습니다. 관리자에게 문의하세요.");
            return;
        }

        rsa.destory(request);

        response.sendRedirect("rollingpaper.do");
    }
}
