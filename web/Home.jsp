<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    response.setContentType("text/html;charset=UTF-8");
    request.setCharacterEncoding("utf-8");
%>
<%@include file="./include/Header_Customer.jsp" %>
<div class="col-xs-7 main">
    <div class="form-group">
        <label for="fullName" class="text-info">Họ và tên:</label><br>
        <input type="text" name="fullName" id="fullName" class="form-control" value="${cust.fullName}" required readonly>
    </div>
    <div class="form-group">
        <label for="id" class="text-info">CMND/CCCD:</label><br>
        <input type="text" name="id" id="id" class="form-control" value="${cust.id}" required readonly>
    </div>
    <div class="form-group">
        <label for="tel" class="text-info">CMND/CCCD:</label><br>
        <input type="text" name="tel" id="tel" class="form-control" value="${cust.tel}" required readonly>
    </div>
    <div class="form-group">
        <label for="address" class="text-info">Địa chỉ:</label><br>
        <input type="text" name="address" id="address" class="form-control" value="${cust.address}" required readonly>
    </div>
    <div class="form-group">
        <label for="email" class="text-info">Email:</label><br>
        <input type="text" name="email" id="email" class="form-control" value="${cust.email}" required readonly>
    </div>
</div>
<%@include file="./include/Footer_Customer.jsp" %>

