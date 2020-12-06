<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8" %>
<%@ taglib prefix="qr" tagdir="/WEB-INF/tags"%>
<qr:include>
    <qr:css href="https://fonts.googleapis.com/css2?family=Nanum+Pen+Script&display=swap" />
    <qr:css href="css/index.css" />
    <qr:script href="js/index.js" />
</qr:include>
<qr:body>
<div class="view center slideFx" id="main">
    <h1>롤링 페이퍼</h1>
    <div id="description">
        먼 곳으로 떠나는 동료에게<br />
        특별한 날을 맞이한 친구에게<br />
        모두의 마음을 모아 선물해주세요
    </div>
    <div class="justify" id="buttons">
        <div>
            <a class="button" href="login.jsp" id="login">롤링 페이퍼<br />만들러 가기</a><br/>
            <span>로그인 및 회원가입 필요</span>
        </div>
    </div>
</div>
</qr:body>
