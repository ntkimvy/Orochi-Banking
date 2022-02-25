<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    response.setContentType("text/html;charset=UTF-8");
    request.setCharacterEncoding("utf-8");
%>
<%@include file="./include/Header_Account.jsp" %>
<div class="col-xs-7 main">
    <form action="transfer" method="POST">
        <div class="form-group">
            <label for="toAcc" class="text-info">Số tài khoản người nhận:</label><br>
            <input type="number" name="toAcc" id="toAcc" class="form-control" value="${toAcc}" readonly required>
        </div>
        <div class="form-group">
            <label for="toName" class="text-info">Tên người nhận:</label><br>
            <input type="text" name="toName" id="toName" class="form-control" value="${toName}" readonly required>
        </div>
        <div class="form-group">
            <label for="amount" class="text-info">Số tiền:</label><br>
            <input type="number" name="amount" id="amount" class="form-control" value="${amount}" readonly required>
        </div>
        <div class="form-group">
            <label for="note" class="text-info">Lời nhắn:</label><br>
            <input type="text" name="note" id="note" class="form-control" value="${note}" readonly required>
        </div>
        <div class="form-group">
            <br>
             <input type="button" value="Trở lại" class="btn btn-info btn-md" onclick="javascript:history.go(-1);" />
            <input type="submit" name="submit" class="btn btn-info btn-md" value="Gửi">
        </div>
    </form>
</div>
<%@include file="./include/Footer_Customer.jsp" %>

