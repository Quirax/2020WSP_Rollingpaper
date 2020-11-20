<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="com.quiraxical.rollingpaper.*" %>
<%
String[] custom_css = { "css/paper.css" };
String[] preload_js = { "js/paper.js" };
%>
<%@ include file="template/header.jsp" %>
<%
Rollingpaper paper = (Rollingpaper) session.getAttribute("rp");
%>
<div class="view">
    <h1>
        <span>To. <%= paper.getTo() %>님</span><br/>
        <%= paper.getTitle() %>
    </h1>
    <ul>
<%
ArrayList<RollingpaperContent> content = paper.getContent();
for (RollingpaperContent cnt : content) {
%>
        <li data-id="<%= cnt.getId() %>">
            <div><%= cnt.getText().replaceAll("\\\n", "<br />") %></div>
            <div>- <%= cnt.getFrom() %></div>
        </li>
<%
}
%>
        <li id="add">추가하기</li>
    </ul>
    <div id="admin_menu">
        <a class="button" id="pwdChange">비밀번호 변경</a>
        <a class="button" id="closePaper">롤링 페이퍼 닫기</a>
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
</div>
<%@ include file="template/footer.jsp" %>