<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8" %>
<%
    String[] custom_css = {
        "css/login.css"
    };
    String[] preload_js = { "js/login.js" };
    request.setAttribute("isEncryptRequired", (Boolean) true);
%>
<%@ include file="template/header.jsp" %>
<%
    int mode;
    try {
        mode = (int) request.getAttribute("mode");
    } catch (NullPointerException e) {
        mode = 0;
    }

    String title = "", action = "", submitText = "", nonSubmitHref = "index.jsp", nonSubmitText = "돌아가기";
    switch(mode) {
        case 0:
            title = "로그인";
            action = "login.do";
            submitText = "로그인";
            nonSubmitHref = "joinPage.do";
            nonSubmitText = "회원가입";
            break;
        case 1:
            title = "회원가입";
            action = "join.do";
            submitText = "회원가입";
            break;
        case 2:
            title = "회원정보 수정";
            action = "modify.do";
            submitText = "수정하기";
            break;
        case 3:
            title = "롤링 페이퍼 열기";
            action = "rollingpaper.do";
            submitText = "열기";
            break;
        case 4:
            title = "회원 탈퇴";
            action = "part.do";
            submitText = "탈퇴하기";
            break;
    }
%>
<div class="view center slideFx">
    <h2><%= title %></h2>
    <input type="hidden" id="rsa_modulus" value="${modulus}" />
    <input type="hidden" id="rsa_exp" value="${exp}" />
    <form id="login_form" action="<%= action %>" method="post">
        <% if (mode == 3) { %>
        <p>롤링 페이퍼를 보려면 미리 공유된 비밀번호를 입력하십시오.</p>
        <input type="hidden" name="id" value="<%= request.getParameter("id") %>" />
        <% } %>
        <table>
            <% if (mode == 0 || mode == 1) { %>
            <tr>
                <th>아이디</th>
                <td><input type="text" maxlength="250" name="name" required /></td>
            </tr>
            <% } %>
            <tr>
                <th>비밀번호</th>
                <td><input type="password" maxlength="250" name="pwd" required /></td>
            </tr>
            <% if (mode == 1 || mode == 2) { %>
            <tr>
                <th>닉네임</th>
                <td><input type="text" maxlength="30" name="nick" required /></td>
            </tr>
            <% } %>
            <tr>
                <td colspan="2">
                    <div id="buttons">
                        <a class="button" href="<%= nonSubmitHref %>"><%= nonSubmitText %></a>
                        <a class="button submit" href="javascript:;"><%= submitText %></a>
                    </div>
                </td>
            </tr>
            <% if (mode == 2) { %>
            <tr>
                <td colspan="2"><a id="modify" href="partPage.do">탈퇴하기</a></td>
            </tr>
            <% } %>
        </table>
    </form>
</div>
<%@ include file="template/footer.jsp" %>
