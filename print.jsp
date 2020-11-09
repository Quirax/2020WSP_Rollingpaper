<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8" %>
<%
String[] custom_css = { "css/print.css" };
String[] preload_js = { "js/print.js" };
%>
<%@ include file="template/header.jsp" %>
<div>
    <h1>
        <span>To. 마뫄님</span><br/>
        마뫄님의 25번째 생일을 축하합니다.
    </h1>
    <ul>
<%
for (int i = 0; i < 25; i++) {
%>
        <li>
            <div>마뫄님, 생일축하해요!</div>
            <div>- 매니저</div>
        </li>
<%
}
%>
    </ul>
</div>
<%@ include file="template/footer.jsp" %>