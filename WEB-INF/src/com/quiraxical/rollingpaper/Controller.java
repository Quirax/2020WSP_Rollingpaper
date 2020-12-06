package com.quiraxical.rollingpaper;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Controller {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {};
    
    /***
     * Usage: execute 메소드에서 다음 과정 진행 1. 파라미터 추출 2. 유효성 체크 3. VO 객체에 데이터 바인딩 4.
     * Service 객체의 데이터 바인딩 5. Output View 페이지로 이동
     * 
     * @throws IOException
     * @throws ServletException
     ***/

    protected void forward(HttpServletRequest request, HttpServletResponse response, String target) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher(target);
        rd.forward(request, response);
    }

    protected void error(HttpServletResponse response, String target, String msg) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        
        PrintWriter out = response.getWriter();
        out.println("<script type=\"text/javascript\">");
        out.println("alert(\"" + msg.replaceAll("\n", "\\\\n") + "\");");
        if (target.equals("")) out.println("history.back();");
        else out.println("window.location.href = \"" + target + "\";");
        out.println("</script>");
    }

    protected User getUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        return getUser(session, response);
    }

    protected User getUser(HttpSession session, HttpServletResponse response) throws Exception {
        User user = (User) session.getAttribute("user");
        confirmUser(user, response);
        return user;
    }

    protected void confirmUser(User user, HttpServletResponse response) throws Exception {
        if(!isValidUser(user)) {
            this.error(response, "login.jsp", "잘못된 접근입니다");
            throw new Exception("인가되지 않은 사용자 정보: " + user.getName());
        }
    }

    protected boolean isValidUser(User user) {
        return user != null && !user.getName().equals("");
    }
}