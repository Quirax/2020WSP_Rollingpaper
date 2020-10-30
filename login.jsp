<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8" %>
<%
    String[] custom_css = {
        //"https://fonts.googleapis.com/css2?family=Nanum+Pen+Script&display=swap",
        "css/login.css"
    };
    String[] preload_js = { "js/login.js" };
%>
<%@ include file="template/header.jsp" %>
<div class="view center slideFx">
    <h2>로그인</h2>
    <form id="login_form" action="" method="post">
        <table>
            <tr>
                <th>아이디</th>
                <td><input type="text" name="id" /></td>
            </tr>
            <tr>
                <th>비밀번호</th>
                <td><input type="password" name="pwd" /></td>
            </tr>
            <tr>
                <th>닉네임</th>
                <td><input type="text" name="nick" /></td>
            </tr>
            <tr>
                <td colspan="2">
                    <div id="buttons">
                        <a href="">가입하기</a>
                        <a href="">로그인</a>
                    </div>
                </td>
            </tr>
        </table>
    </form>
</div>
<%@ include file="template/footer.jsp" %>
