package com.quiraxical.rollingpaper;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteContentController extends Controller {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        int id;

        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            this.error(response, "index.jsp", "잘못된 접근입니다");
            return;
        }

        RSA rsa = RSA.getInstance();
        DAO dao = DAO.getInstance();
        Rollingpaper rp;
        
        try {
            rp = dao.deleteRollingpaperContent(user, (Rollingpaper) session.getAttribute("rp"), id,
                rsa.decrypt(request.getParameter("pwd"), request));
        } catch (Exception e) {
            e.printStackTrace();
            this.error(response, "rollingpaper.do", "삭제를 진행할 수 없습니다. 비밀번호가 잘못되었을 수 있습니다.\n만약 삭제를 원하는 경우 생성자에게 문의하시기 바랍니다.");
            rsa.destory(request);
            return;
        }

        rsa.destory(request);

        session.setAttribute("rp", rp);

        response.sendRedirect("rollingpaper.do");
    }
}
