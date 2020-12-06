<%@ tag language="java" pageEncoding="UTF-8" body-content="empty" import="java.util.*" %>
<%@ attribute name="href" required="true" %>
<%
ArrayList<String> js = (ArrayList<String>) request.getAttribute("preload_js");
js.add(href);
%>