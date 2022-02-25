<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    response.setContentType("text/html;charset=UTF-8");
    request.setCharacterEncoding("utf-8");
%>
<%@include file="./include/Header_Account.jsp" %>
<div class="col-xs-7 main">
    <div><b>${mess}</b></div>
    <a href="${link}"><button class="btn btn-info btn-md">Quay lại</button></a>
</div>
<%@include file="./include/Footer_Customer.jsp" %>

