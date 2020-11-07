<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8" %>
<%
String[] custom_css = { "css/paper.css" };
String[] preload_js = { "js/paper.js" };
%>
<%@ include file="template/header.jsp" %>
<div class="view">
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
        <li id="add">추가하기</li>
    </ul>
    <div id="admin_menu">
        <a class="button" id="pwdChange">비밀번호 변경</a>
        <a class="button" id="closePaper">롤링 페이퍼 닫기</a>
    </div>
    <div class="form_wrapper" id="content_form">
        <form action="" method="post">
            <div><textarea name="content" required></textarea></div>
            <div class="info">by <input name="by" type="text" required autocomplete="new-password" /></div>
            <div class="info">비밀번호: <input name="pwd" type="password" required autocomplete="new-password" /></div>
            <div class="info">
                <a class="button submit_form">추가</a>
                <a class="button close_form">취소</a>
            </div>
        </form>
    </div>
    <div class="form_wrapper" id="content_view">
        <form action="" method="post">
            <div id="content">내용</div>
            <div class="info">by 쓴 사람</div>
            <div class="info">
                <a id="delete_content" class="button">삭제</a>
                <a class="button close_form">닫기</a>
            </div>
        </form>
    </div>
    <div class="form_wrapper" id="pwd_form">
        <form action="" method="post">
            <div class="info">비밀번호 입력: <input name="pwd" type="password" required autocomplete="new-password" /></div>
            <div class="info">
                <a class="button submit_form">입력</a>
                <a class="button close_form">취소</a>
            </div>
        </form>
    </div>
</div>
<%@ include file="template/footer.jsp" %>