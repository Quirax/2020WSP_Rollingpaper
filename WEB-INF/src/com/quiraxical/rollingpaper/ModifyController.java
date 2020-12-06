package com.quiraxical.rollingpaper;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ModifyController extends Controller {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user;
        try {
            user = getUser(session, response);
        } catch (Exception e1) {
            return;
        }

        String nick = request.getParameter("nick");
        if (nick != null)
            user.setNick(nick);
        DAO dao = DAO.getInstance();
        RSA rsa = RSA.getInstance();

        try {
            user = dao.updateUser(user, rsa.decrypt(request.getParameter("pwd"), request));
        } catch (Exception e) {
            e.printStackTrace();
            user = null;
        }
        
        if(user == null) {
            this.error(response, "modifyPage.do", "회원 정보를 저장하는 도중 문제가 발생했습니다.\n"
            + "계속해서 문제가 발생할 경우 관리자에게 문의해보세요.");
            return;
        }

        rsa.destory(request);

        session.setAttribute("user", user);

        this.forward(request, response, "my.jsp");
    }
}
