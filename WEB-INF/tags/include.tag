<%@ tag language="java" pageEncoding="UTF-8" body-content="scriptless" import="java.util.*" %>
<%@ tag trimDirectiveWhitespaces="true" %>
<%
ArrayList<String> css = new ArrayList<String>();
ArrayList<String> js = new ArrayList<String>();
request.setAttribute("custom_css", css);
request.setAttribute("preload_js", js);
%>
<jsp:doBody var="bodyText" />