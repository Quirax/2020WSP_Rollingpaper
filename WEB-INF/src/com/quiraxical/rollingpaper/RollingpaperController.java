package com.quiraxical.rollingpaper;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RollingpaperController extends Controller {
    private boolean toPrint = false;

    public RollingpaperController(boolean toPrint) {
        this.toPrint = toPrint;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(toPrint && (user == null || user.getName().equals(""))) {
            this.error(response, "login.jsp", "잘못된 접근입니다");
            return;
        }

        //TODO: use RSA Crypto

        Rollingpaper paper = (Rollingpaper) session.getAttribute("rp");
        DAO dao = DAO.getInstance();

        int id;

        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0;
        }
        
        if(paper == null || (id > 0 && paper.getId() != id)) {
            paper = dao.getRollingpaper(user, id, request.getParameter("pwd"));
        } else {
            paper = dao.refreshRollingpaper(user, paper);
        }

        if(paper == null) {
            this.error(response, "", "해당하는 롤링 페이퍼를 찾을 수 없습니다.\n"
                + "입력한 비밀번호가 잘못되었거나, 이미 마감된 롤링 페이퍼일 수 있습니다.\n"
                + "생성자에게 문의하시기 바랍니다.");
            return;
        }

        if(!toPrint && paper.getIsClosed()) {
            this.error(response, "", "이미 마감된 롤링 페이퍼입니다. 관리자에게 문의하세요.");
            return;
        }

        if(user == null) {
            user = new User();
            user.setName("");
            session.setAttribute("user", user);
        }

        session.setAttribute("rp", paper);

        if(toPrint && !paper.getIsClosed()) {
            response.sendRedirect("rollingpaper.do");
            return;
        }

        this.forward(request, response, (toPrint)?"print.jsp":"paper.jsp");
    }
}