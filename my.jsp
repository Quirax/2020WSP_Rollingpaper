<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="com.quiraxical.rollingpaper.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="qr" tagdir="/WEB-INF/tags" %>
<qr:include>
    <qr:css href="css/my.css" />
    <qr:script href="js/my.js" />
</qr:include>
<jsp:useBean scope="session" id="user" type="com.quiraxical.rollingpaper.User" />
<qr:body>
<div class="view">
    <h1>${user.nick} 님의 마이페이지</h1>
    <h2>내가 연 롤링 페이퍼 목록</h2>
    <table id="list">
        <thead>
            <tr>
                <th>번호</th>
                <th>대상</th>
                <th>롤링 페이퍼 이름</th>
                <th colspan="2">작업</th>
            </tr>
            <tr>
                <td colspan="5"><a class="button create" href="create.jsp">새 롤링 페이퍼 만들기</a></td>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="rp" items="${rpl}">
                <tr>
                    <td>${rp.id}</td>
                    <td>${rp.to}</td>
                    <td>${rp.title}
                        <c:choose>
                            <c:when test="${rp.isClosed}">
                                <span class="closed">(닫힘)</span>
                    </td>
                    <td><a class="button print" href="printRP.do?id=${rp.id}">인쇄하기</a>
                    <td><a class="button delete" href="deleteRP.do?id=${rp.id}">삭제하기</a>
                            </c:when>
                            <c:otherwise>
                    </td>
                    <td><a class="button open" href="rollingpaper.do?id=${rp.id}">보러가기</a>
                    <td><a class="button close" href="closeRP.do?id=${rp.id}">마감하기</a>
                            </c:otherwise>
                        </c:choose>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a id="modify" href="modifyPage.do">회원정보 수정</a>
</div>
</qr:body>