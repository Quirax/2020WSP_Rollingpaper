package com.quiraxical.rollingpaper;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public interface Controller {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    /***
     * Usage: execute 메소드에서 다음 과정 진행
     *     1. 파라미터 추출
     *     2. 유효성 체크
     *     3. VO 객체에 데이터 바인딩
     *     4. Service 객체의 데이터 바인딩
     *     5. Output View 페이지로 이동
     ***/
}