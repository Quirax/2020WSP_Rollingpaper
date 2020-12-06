<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="qr" tagdir="/WEB-INF/tags" %>
<qr:include>
    <qr:css href="css/login.css" />
    <qr:script href="js/login.js" />
</qr:include>
<qr:encryptRequired />
<c:if test="${mode == null}">
    <c:set var="mode" value="0" />
</c:if>
<c:set var="title" value="" />
<c:set var="action" value="" />
<c:set var="submitText" value="" />
<c:set var="nonSubmitHref" value="index.jsp" />
<c:set var="nonSubmitText" value="돌아가기" />
<c:choose>
    <c:when test="${mode == 0}">
        <c:set var="title" value="로그인" />
        <c:set var="action" value="login.do" />
        <c:set var="submitText" value="로그인" />
        <c:set var="nonSubmitHref" value="joinPage.do" />
        <c:set var="nonSubmitText" value="회원가입" />
    </c:when>
    <c:when test="${mode == 1}">
        <c:set var="title" value="회원가입" />
        <c:set var="action" value="join.do" />
        <c:set var="submitText" value="회원가입" />
        <c:set var="nonSubmitHref" value="login.jsp" />
    </c:when>
    <c:when test="${mode == 2}">
        <c:set var="title" value="회원정보 수정" />
        <c:set var="action" value="modify.do" />
        <c:set var="submitText" value="수정하기" />
    </c:when>
    <c:when test="${mode == 3}">
        <c:set var="title" value="롤링 페이퍼 열기" />
        <c:set var="action" value="rollingpaper.do" />
        <c:set var="submitText" value="열기" />
    </c:when>
    <c:when test="${mode == 4}">
        <c:set var="title" value="회원탈퇴" />
        <c:set var="action" value="part.do" />
        <c:set var="submitText" value="탈퇴하기" />
    </c:when>
</c:choose>
<qr:body>
<div class="view center slideFx">
    <h2>${title}</h2>
    <input type="hidden" id="rsa_modulus" value="${modulus}" />
    <input type="hidden" id="rsa_exp" value="${exp}" />
    <form id="login_form" action="${action}" method="post">
        <c:if test="${mode == 3}">
            <p>롤링 페이퍼를 보려면 미리 공유된 비밀번호를 입력하십시오.</p>
            <input type="hidden" name="id" value="${param.id}" />
        </c:if>
        <table>
            <c:if test="${mode == 0 || mode == 1}">
                <tr>
                    <th>아이디</th>
                    <td><input type="text" maxlength="250" name="name" required /></td>
                </tr>
            </c:if>
            <tr>
                <th>비밀번호</th>
                <td><input type="password" maxlength="250" name="pwd" required /></td>
            </tr>
            <c:if test="${mode == 1 || mode == 2}">
                <tr>
                    <th>닉네임</th>
                    <td><input type="text" maxlength="30" name="nick" required /></td>
                </tr>
            </c:if>
            <tr>
                <td colspan="2">
                    <div id="buttons">
                        <a class="button" href="${nonSubmitHref}">${nonSubmitText}</a>
                        <a class="button submit" href="javascript:;">${submitText}</a>
                    </div>
                </td>
            </tr>
            <c:if test="${mode == 2}">
                <tr>
                    <td colspan="2"><a id="modify" href="partPage.do">탈퇴하기</a></td>
                </tr>
            </c:if>
        </table>
    </form>
</div>
</qr:body>