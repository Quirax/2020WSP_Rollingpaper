package com.quiraxical.rollingpaper;

import java.io.*;
import java.util.HashMap;
import javax.servlet.*;
import javax.servlet.http.*;

public class FrontController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private HashMap<String, Controller> map = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        map = new HashMap<String, Controller>();

        /***
         * TODO: 액션과 컨트롤러 바인딩
         * usage: map.put("/action.do", new Controller());
         ***/
        map.put("/mypage.do", new MypageController());
        map.put("/createRP.do", new CreateRPController());
        map.put("/login.do", new LoginController());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getRequestURI();
        String ctx = request.getContextPath();
        String path = url.substring(ctx.length());  //요청 액션을 구함

        Controller controller = map.get(path);
        if (controller == null) throw new IOException("Unable to resolve requested action");
        controller.execute(request, response);  //액션에 해당하는 컨트롤러 실행
    }
}
