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
        out.println("alert(\"" + msg + "\");");
        out.println("window.location.href = \"" + target + "\";");
        out.println("</script>");
    }
}