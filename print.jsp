<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="com.quiraxical.rollingpaper.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="qr" tagdir="/WEB-INF/tags"%>
<qr:include>
    <qr:css href="css/print.css" />
</qr:include>
<jsp:useBean scope="session" id="rp" type="com.quiraxical.rollingpaper.Rollingpaper" />
<qr:body>
<div class="view">
    <h1>
        <span>To. ${rp.to}님</span><br/>
        ${rp.title}
    </h1>
    <ul>
        <c:forEach var="content" items="${rp.content}">
            <li data-id="${content.id}">
                <c:set var="LF" value="\n" />
                <div>${content.text.replaceAll(LF, "<br />")}</div>
                <div>- ${content.from}</div>
            </li>
        </c:forEach>
    </ul>
</div>
</qr:body>