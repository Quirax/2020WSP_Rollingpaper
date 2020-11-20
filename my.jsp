<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="com.quiraxical.rollingpaper.*" %>
<%
String[] custom_css = { "css/my.css" };
String[] preload_js = { "js/my.js" };
%>
<%@ include file="template/header.jsp" %>
<div class="view">
    <h1>킈락 님의 마이페이지</h1>
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
            <%
ArrayList<Rollingpaper> paper = (ArrayList<Rollingpaper>) request.getAttribute("rp");
for(Rollingpaper rp : paper) {
            %>
            <tr>
                <td><%= rp.getId() %></td>
                <td><%= rp.getTo() %></td>
                <td><%= rp.getTitle() %>
            <%
    if (rp.getIsClosed()) {
            %>
                <span class="closed">(닫힘)</span>
                </td>
                <td><a class="button print" href="printRP.do?id=<%= rp.getId() %>">인쇄하기</a>
                <td><a class="button delete" href="deleteRP.do?id=<%= rp.getId() %>">삭제하기</a>
            <%
    } else {
            %>
                </td>
                <td><a class="button open" href="rollingpaper.do?id=<%= rp.getId() %>">보러가기</a>
                <td><a class="button close" href="closeRP.do?id=<%= rp.getId() %>">마감하기</a>
            <%
    }
}
            %>
        </tbody>
    </table>
    <a id="modify" href="">회원정보 수정</a>
</div>
<%@ include file="template/footer.jsp" %>