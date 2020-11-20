<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="com.quiraxical.rollingpaper.*" %>
<%
String[] custom_css = { "css/print.css" };
String[] preload_js = { "js/print.js" };
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
    </ul>
</div>
<%@ include file="template/footer.jsp" %>