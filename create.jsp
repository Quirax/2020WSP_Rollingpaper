<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8" %>
<%
String[] custom_css = { "css/create.css" };
String[] preload_js = { "js/create.js" };
%>
<%@ include file="template/header.jsp" %>
<div class="view center">
    <form action="" method="post" autocomplete="off">
        <div>
            <h2><input type="text" name="recv" maxlength="30" placeholder="이름" autocomplete="off" required />을(를) 위한</h2>
            <h1><input type="text" name="title" maxlength="250" placeholder="롤링 페이퍼 이름" autocomplete="off" required /></h1>
            <input type="password" name="pwd" maxlength="250" placeholder="비밀번호" autocomplete="new-password" required />를 아는 사람들만 들어올 수 있어요
        </div>
        <a class="button" id="submit" href="javascript:;">롤링 페이퍼 만들기</a>
    </form>
</div>
<%@ include file="template/footer.jsp" %>