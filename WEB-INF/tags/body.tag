<%@ tag language="java" pageEncoding="UTF-8" import="java.util.*" body-content="scriptless" %>
<%@ tag trimDirectiveWhitespaces="true" %>
<%@ attribute name="title" %>
<!doctype html>
<html>
    <head>
        <title>${title}</title>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="stylesheet" href="css/common.css" />
<%
ArrayList<String> custom_css = (ArrayList<String>) request.getAttribute("custom_css");
if(custom_css != null) {
    for(String css : custom_css) {
%>
        <link rel="stylesheet" href="<%= css %>" />
<%
    }
}
%>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"
            integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
            crossorigin="anonymous"></script>
<%
Boolean isEncryptRequired = (Boolean) request.getAttribute("isEncryptRequired");
if(isEncryptRequired != null && isEncryptRequired) {
%>
        <script type="text/javascript" src="js/sha256.js"></script>
        <script type="text/javascript" src="js/rsa.js"></script>
        <script type="text/javascript" src="js/jsbn.js"></script>
        <script type="text/javascript" src="js/prng4.js"></script>
        <script type="text/javascript" src="js/rng.js"></script>
        <script type="text/javascript" src="js/rsasupport.js"></script>
<%
}
%>
        <script type="text/javascript" src="js/common.js"></script>
<%
ArrayList<String> preload_js = (ArrayList<String>) request.getAttribute("preload_js");
if(preload_js != null) {
    for(String js : preload_js) {
%>
        <script type="text/javascript" src="<%=js%>"></script>
<%
    }
}
%>
    </head>
    <body>
        <jsp:doBody />
    </body>
</html>