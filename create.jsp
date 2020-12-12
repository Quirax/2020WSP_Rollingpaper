<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8" %>
<%@ taglib prefix="qr" tagdir="/WEB-INF/tags"%>
<qr:include>
    <qr:css href="css/create.css" />
    <qr:script href="js/create.js" />
</qr:include>
<qr:encryptRequired />
<qr:body title="롤링 페이퍼 만들기">
<div class="view center">
    <input type="hidden" id="rsa_modulus" value="${modulus}" />
    <input type="hidden" id="rsa_exp" value="${exp}" />
    <form action="createRP.do" method="post" autocomplete="off">
        <div>
            <h2><input type="text" name="to" maxlength="30" placeholder="이름" autocomplete="off" required />을(를) 위한</h2>
            <h1><input type="text" name="title" maxlength="250" placeholder="롤링 페이퍼 이름" autocomplete="off" required /></h1>
            <input type="password" name="pwd" maxlength="250" placeholder="비밀번호" autocomplete="new-password" required />를 아는 사람들만 들어올 수 있어요
        </div>
        <a class="button" id="submit" href="javascript:;">롤링 페이퍼 만들기</a>
    </form>
</div>
</qr:body>