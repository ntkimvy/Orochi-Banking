<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    response.setContentType("text/html;charset=UTF-8");
    request.setCharacterEncoding("utf-8");
%>
<%@include file="./include/Header_Customer.jsp" %>
<div class="col-xs-7 main">
    <form action="createaccount" method="POST">
        <div class="form-group">
            <label for="password" class="text-info">Mật khẩu tài khoản mới:</label><br>
            <input type="password" name="password" id="password" class="form-control" required style="width: 50%">
        </div>
        <div class="form-group">
            <br>
            <input type="submit" name="submit" class="btn btn-info btn-md" value="Đồng ý">
        </div>
    </form>



</div>
<%@include file="./include/Footer_Customer.jsp" %>