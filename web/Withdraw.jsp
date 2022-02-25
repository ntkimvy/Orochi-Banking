<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    response.setContentType("text/html;charset=UTF-8");
    request.setCharacterEncoding("utf-8");
%>
<%@include file="./include/Header_Account.jsp" %>
<div class="col-xs-7 main">
    <div><b>Số tài khoản: ${acc.accNo}</b></div>
    <div><b>Số dư: ${acc.balance} VNĐ</b></div> 
    <table class="table" style="background-color: white">
        <thead>
            <tr>
                <th scope="col"><a href="transfer">Chuyển tiền</a></th>               
                <th scope="col"><a href="deposit">Nộp tiền</a></th>
                <th scope="col"><a style="color: limegreen" href="withdraw">Rút tiền</a></th>
                <th scope="col"><a href="history">Xem lịch sử</a></th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td></td>
                <td colspan="2">
                    <form action="withdraw" method="POST">
                        <div class="form-group">
                            <label for="amount" class="text-info">Số tiền:</label><br>
                            <input type="number" name="amount" id="amount" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <br>
                            <input type="submit" name="submit" class="btn btn-info btn-md" value="Rút">
                        </div>
                    </form>
                </td>
                <td></td>
            </tr>  
        </tbody>
    </table>
</div>
<%@include file="./include/Footer_Customer.jsp" %>

