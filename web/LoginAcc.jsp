<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    response.setContentType("text/html;charset=UTF-8");
    request.setCharacterEncoding("utf-8");
%>
<%@include file="./include/Header_Customer.jsp" %>
<div class="col-xs-7 main">
    <form action="loginacc" method="POST">
        <div class="form-group">
            <label for="accNo" class="text-info">Số tài khoản:</label><br>
            <input type="text" name="accNo" id="accNo" class="form-control" value="${accNo}" required readonly>
        </div>
        <div class="form-group">
            <label for="password" class="text-info">Mật khẩu:</label><br>
            <input type="password" name="password" id="password" class="form-control" required>
        </div>
        <div class="form-group">
            <br>
            <input type="submit" name="submit" class="btn btn-info btn-md" value="Đăng nhập">
        </div>
    </form>

</div>
<%@include file="./include/Footer_Customer.jsp" %>