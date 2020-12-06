<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="com.quiraxical.rollingpaper.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="qr" tagdir="/WEB-INF/tags"%>
<qr:include>
    <qr:css href="css/paper.css" />
    <qr:script href="js/paper.js" />
</qr:include>
<qr:encryptRequired />
<jsp:useBean scope="session" id="rp" type="com.quiraxical.rollingpaper.Rollingpaper" />
<qr:body>
<div class="view">
    <h1>
        <span>To. ${rp.to}님</span><br/>
        ${rp.title}
    </h1>
    <ul>
        <c:forEach var="content" items="${rp.content}">
            <li data-id="${content.id}">
                <c:set var="LF" value="\n" />
                <div>${content.text.replaceAll(LF, "<br />")}</div>
                <div>- ${content.from}</div>
            </li>
        </c:forEach>
        <li id="add">추가하기</li>
    </ul>
    <div id="admin_menu">
        <a class="button" id="pwdChange">비밀번호 변경</a>
        <a class="button" id="closePaper">마감하기</a>
    </div>
    <div class="form_wrapper" id="content_form">
        <form action="writeContent.do" method="post">
            <div><textarea name="text" required></textarea></div>
            <div class="info">From: <input name="from" type="text" required autocomplete="new-password" /></div>
            <div class="info">비밀번호: <input name="pwd" type="password" required autocomplete="new-password" /></div>
            <div class="info">
                <a class="button submit_form">추가</a>
                <a class="button close_form">취소</a>
            </div>
        </form>
    </div>
    <div class="form_wrapper" id="content_view">
        <form>
            <div id="content">내용</div>
            <div id="from">누구로부터</div>
            <div class="info">
                <a id="delete_content" class="button">삭제</a>
                <a class="button close_form">닫기</a>
            </div>
        </form>
    </div>
    <div class="form_wrapper" id="pwd_form">
        <form action="" method="post">
            <input name="id" type="hidden" />
            <div class="info">비밀번호 입력: <input name="pwd" type="password" required autocomplete="new-password" /></div>
            <div class="info">
                <a class="button submit_form">입력</a>
                <a class="button close_form">취소</a>
            </div>
        </form>
    </div>
    <input type="hidden" id="rsa_modulus" value="${modulus}" />
    <input type="hidden" id="rsa_exp" value="${exp}" />
</div>
</qr:body>