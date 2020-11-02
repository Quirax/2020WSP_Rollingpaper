<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8" %>
<%
    String[] custom_css = {
        "https://fonts.googleapis.com/css2?family=Nanum+Pen+Script&display=swap",
        "css/index.css"
    };
    String[] preload_js = { "js/index.js" };
%>
<%@ include file="template/header.jsp" %>
<div class="view center slideFx" id="main">
    <h1>롤링 페이퍼</h1>
    <div id="description">
        먼 곳으로 떠나는 동료에게<br />
        특별한 날을 맞이한 친구에게<br />
        모두의 마음을 모아 선물해주세요
    </div>
    <div class="justify" id="buttons">
        <div>
            <a href="login.jsp" id="login">롤링 페이퍼<br />만들러 가기</a><br/>
            <span>로그인 및 회원가입 필요</span>
        </div>
        <div>
            <a href="" id="find">롤링 페이퍼<br />찾아가기</a>
        </div>
    </div>
</div>
<%@ include file="template/footer.jsp" %>
