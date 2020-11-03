<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8" %>
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
            <tr>
                <td>5</td>
                <td>마뫄님</td>
                <td>마뫄님의 25번째 생일을 축하합니다</td>
                <td><a class="button open" href="">보러가기</a>
                <td><a class="button close" href="">마감하기</a>
            </tr>
            <tr>
                <td>4</td>
                <td>김철수</td>
                <td>철수야, 생일 축하해! <span class="closed">(닫힘)</span></td>
                <td><a class="button print" href="">인쇄하기</a>
                <td><a class="button delete" href="">삭제하기</a>
            </tr>
            <tr>
                <td>4</td>
                <td>김철수</td>
                <td>철수야, 생일 축하해! <span class="closed">(닫힘)</span></td>
                <td><a class="button print" href="">인쇄하기</a>
                <td><a class="button delete" href="">삭제하기</a>
            </tr>
        </tbody>
    </table>
    <a id="modify" href="">회원정보 수정</a>
</div>
<%@ include file="template/footer.jsp" %>