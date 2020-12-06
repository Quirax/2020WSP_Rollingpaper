<%@ tag language="java" pageEncoding="UTF-8" body-content="empty" import="java.util.*" %>
<%@ attribute name="href" required="true" %>
<%
ArrayList<String> css = (ArrayList<String>) request.getAttribute("custom_css");
css.add(href);
%>