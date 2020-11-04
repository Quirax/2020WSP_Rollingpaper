<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8" %>
<%
String[] custom_css = { "css/paper.css" };
String[] preload_js = {  };
%>
<%@ include file="template/header.jsp" %>
<div class="view">
    <h1>
        <span>To. 마뫄님</span><br/>
        마뫄님의 25번째 생일을 축하합니다.
    </h1>
    <ul>
<%
for (int i = 0; i < 29; i++) {
%>
        <li>
            <div>마뫄님, 생일축하해요!</div>
            <div>- 매니저</div>
        </li>
<%
}
%>
        <li id="create">추가하기</li>
    </ul>
    <div id="admin_menu">
        <a class="button" href="javascript:;">비밀번호 변경</a>
        <a class="button" href="javascript:;">롤링 페이퍼 닫기</a>
    </div>
</div>
<%@ include file="template/footer.jsp" %>