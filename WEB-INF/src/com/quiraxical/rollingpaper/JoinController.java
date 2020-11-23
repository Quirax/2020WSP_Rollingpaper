package com.quiraxical.rollingpaper;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JoinController extends Controller {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAO dao = DAO.getInstance();
        // TODO: password는 복호화 거쳐서
        User user = dao.createUser(request.getParameter("name"), request.getParameter("pwd"), request.getParameter("nick"));
        if (user == null) {
            this.error(response, "joinPage.do", "회원가입에 실패하였습니다.\n이미 있는 계정일 수 있습니다. 입력값을 다시 확인해보세요.");
            return;
        }

        response.sendRedirect("login.jsp");
    }
}
