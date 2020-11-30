<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="com.quiraxical.rollingpaper.*" %>
<%
DAO dao = DAO.getInstance();
String method = request.getParameter("method");
boolean isConn;
User user = null;

try {
    isConn = (boolean) session.getAttribute("isConn");
} catch(NullPointerException e) {
    isConn = false;
}

if (method != null) {
    if(method.equals("createuser")) {
        user = dao.createUser(request.getParameter("name"), request.getParameter("pwd"), request.getParameter("nick"));
    } else if(method.equals("finduser")) {
        user = dao.findUser(request.getParameter("name"), request.getParameter("pwd"));
    } else if(method.equals("modifyuser")) {
        user = new User();
        user.setName(request.getParameter("name"));
        user.setNick(request.getParameter("nick"));
        user = dao.updateUser(user, request.getParameter("pwd"));
    } else if(method.equals("deleteuser")) {
        user = new User();
        user.setName(request.getParameter("name"));
        dao.deleteUser(user, request.getParameter("pwd"));
    }
}
%>
<html>
<head>
<title>테스트</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"
    integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
    crossorigin="anonymous"></script>
<script type="text/javascript" src="js/sha256.js"></script>
<script type="text/javascript">
function createuser_onsubmit(e) {
    plain = $("#createuser input[name='pwd']").val();
    $("#createuser input[name='pwd']").val(sha256(plain));
}

function finduser_onsubmit() {
    plain = $("#finduser input[name='pwd']").val();
    $("#finduser input[name='pwd']").val(sha256(plain));
}

function modifyuser_onsubmit() {
    plain = $("#modifyuser input[name='pwd']").val();
    $("#modifyuser input[name='pwd']").val(sha256(plain));
}

function deleteuser_onsubmit() {
    plain = $("#deleteuser input[name='pwd']").val();
    $("#deleteuser input[name='pwd']").val(sha256(plain));
}
</script>
</head>
<body>
<h1>데이터베이스 테스트 (<%= method %>)</h1>
<fieldset>
    <legend>계정 생성</legend>
    <form action="" method="get" onsubmit="return createuser_onsubmit()" id="createuser">
        <input type="hidden" name="method" value="createuser" />
        name: <input type="text" name="name" /><br />
        pwd: <input type="text" name="pwd" /><br />
        nick: <input type="text" name="nick" /><br />
        <input type="submit" value="계정 생성하기" />
    </form>
</fieldset>
<fieldset>
    <legend>계정 조회</legend>
    <form action="" method="get" onsubmit="return finduser_onsubmit()" id="finduser">
        <input type="hidden" name="method" value="finduser" />
        name: <input type="text" name="name" /><br />
        pwd: <input type="text" name="pwd" /><br />
        <input type="submit" value="계정 조회하기" />
    </form>
</fieldset>
<fieldset>
    <legend>계정 정보 수정</legend>
    <form action="" method="get" onsubmit="return modifyuser_onsubmit()" id="modifyuser">
        <input type="hidden" name="method" value="modifyuser" />
        name: <input type="text" name="name" /><br />
        pwd: <input type="text" name="pwd" /><br />
        nick: <input type="text" name="nick" /><br />
        <input type="submit" value="계정 정보 수정하기" />
    </form>
</fieldset>
<fieldset>
    <legend>계정 삭제</legend>
    <form action="" method="get" onsubmit="return deleteuser_onsubmit()" id="deleteuser">
        <input type="hidden" name="method" value="deleteuser" />
        name: <input type="text" name="name" /><br />
        pwd: <input type="text" name="pwd" /><br />
        <input type="submit" value="계정 삭제하기" />
    </form>
</fieldset>
<hr />
<fieldset>
    <legend>롤링 페이퍼 목록 조회</legend>
    <form action="" method="get" onsubmit="return deleteuser_onsubmit()" id="deleteuser">
        <input type="hidden" name="method" value="deleteuser" />
        name: <input type="text" name="name" /><br />
        pwd: <input type="text" name="pwd" /><br />
        <input type="submit" value="계정 삭제하기" />
    </form>
</fieldset>
<hr />
<% if(user != null) { %>
<fieldset>
    <legend>사용자 정보 처리 결과</legend>
    <form action="" method="get">
        name: <input type="text" name="name" value="<%= user.getName() %>" /><br />
        nick: <input type="text" name="nick" value="<%= user.getNick() %>" /><br />
    </form>
<% }
} %>
</body>
</html>